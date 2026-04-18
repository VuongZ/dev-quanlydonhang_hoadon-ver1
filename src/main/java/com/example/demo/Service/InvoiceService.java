package com.example.demo.Service;

import com.example.demo.DTO.InvoiceDTO;
import com.example.demo.DTO.InvoiceDetailDTO;
import com.example.demo.Repository.InvoiceDetailRepository;
import com.example.demo.Repository.InvoiceRepository;
import com.example.demo.Repository.InvoiceTemplateRepository;
import com.example.demo.Repository.customerepository;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.InvoiceDetail;
import com.example.demo.entity.InvoiceTemplate;
import com.example.demo.entity.customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired private InvoiceRepository invoiceRepo;
    @Autowired private customerepository customerRepo;
    @Autowired private InvoiceTemplateRepository templateRepo;
    @Autowired private InvoiceDetailRepository detailRepo;

    public List<Invoice> getAll() { return invoiceRepo.findAll(); }

    public Invoice getById(Integer id) {
        return invoiceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));
    }

    public List<Invoice> search(String invoiceNumber, String fromDate, String toDate, Integer customerId) {
        return invoiceRepo.findAll().stream()
                .filter(inv -> invoiceNumber == null || inv.getInvoiceNumber().contains(invoiceNumber))
                .filter(inv -> fromDate == null || !inv.getInvoiceDate().isBefore(LocalDate.parse(fromDate)))
                .filter(inv -> toDate == null || !inv.getInvoiceDate().isAfter(LocalDate.parse(toDate)))
                .filter(inv ->
                        customerId == null ||
                                (inv.getCustomer() != null &&
                                        inv.getCustomer().getId() == customerId)
                )
                .collect(Collectors.toList());
    }

    public Invoice create(InvoiceDTO dto) {
        Invoice inv = new Invoice();
        inv.setInvoiceNumber(dto.getInvoiceNumber());
        inv.setInvoiceDate(dto.getInvoiceDate());
        inv.setTaxRate(dto.getTaxRate());

        customers customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy customer"));
        InvoiceTemplate template = templateRepo.findById(dto.getTemplateId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy template"));

        inv.setCustomer(customer);
        inv.setTemplate(template);

        double total = dto.getDetails().stream()
                .mapToDouble(d -> d.getQuantity() * d.getUnitPrice()).sum();
        inv.setTotalAmount(total);
        inv.setFinalAmount(total * (1 + dto.getTaxRate() / 100));

        Invoice saved = invoiceRepo.save(inv);

        for (InvoiceDetailDTO d : dto.getDetails()) {
            InvoiceDetail detail = new InvoiceDetail();
            detail.setProductName(d.getProductName());
            detail.setQuantity(d.getQuantity());
            detail.setUnitPrice(d.getUnitPrice());
            detail.setSubtotal((double)(d.getQuantity() * d.getUnitPrice()));
            detail.setInvoice(saved);
            detailRepo.save(detail);
        }
        return saved;
    }

    public void delete(Integer id) { invoiceRepo.deleteById(id); }

    // Điền dữ liệu vào template HTML
    public String generateHtml(Integer id) {
        Invoice inv = getById(id);
        String html = inv.getTemplate().getContentHtml();

        StringBuilder rows = new StringBuilder();
        for (InvoiceDetail d : inv.getDetails()) {
            rows.append("<tr>")
                    .append("<td>").append(d.getProductName()).append("</td>")
                    .append("<td>").append(d.getQuantity()).append("</td>")
                    .append("<td>").append(String.format("%,.0f", (double)d.getUnitPrice())).append("</td>")
                    .append("<td>").append(String.format("%,.0f", d.getSubtotal())).append("</td>")
                    .append("</tr>");
        }

        html = html.replace("{{invoiceNumber}}", inv.getInvoiceNumber())
                .replace("{{invoiceDate}}", inv.getInvoiceDate().toString())
                .replace("{{customerName}}", inv.getCustomer().getName())
                .replace("{{customerAddress}}", inv.getCustomer().getAddress())
                .replace("{{customerTaxCode}}", inv.getCustomer().getTaxcode())
                .replace("{{totalAmount}}", String.format("%,.0f", inv.getTotalAmount()))
                .replace("{{taxRate}}", inv.getTaxRate() + "%")
                .replace("{{finalAmount}}", String.format("%,.0f", inv.getFinalAmount()))
                .replace("{{details}}", rows.toString());
        return html;
    }
}