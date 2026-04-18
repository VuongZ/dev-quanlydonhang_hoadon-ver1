package com.example.demo.Service;

import com.example.demo.DTO.InvoiceDTO;
import com.example.demo.DTO.InvoiceDetailDTO;
import com.example.demo.Repository.InvoiceDetailRepository;
import com.example.demo.Repository.InvoiceRepository;
import com.example.demo.Repository.InvoiceTemplateRepository;
import com.example.demo.Repository.customerepository;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.InvoiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepo;
    @Autowired private customerepository customerRepo;
    @Autowired private InvoiceTemplateRepository templateRepo;
    @Autowired private InvoiceDetailRepository detailRepo;

    public List<Invoice> getAll() { return invoiceRepo.findAll(); }

    public Invoice create(InvoiceDTO dto) {
        Invoice inv = new Invoice();
        inv.setInvoiceNumber(dto.getInvoiceNumber());
        inv.setInvoiceDate(dto.getInvoiceDate());
        inv.setTaxRate(dto.getTaxRate());
        inv.setTotalAmount(dto.getTotalAmount());
        inv.setFinalAmount(dto.getTotalAmount() * (1 + dto.getTaxRate() / 100));

        inv.setCustomer(customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy customer")));
        inv.setTemplate(templateRepo.findById(dto.getTemplateId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy template")));

        Invoice saved = invoiceRepo.save(inv);

        // Lưu chi tiết hóa đơn
        for (InvoiceDetailDTO d : dto.getDetails()) {
            InvoiceDetail detail = new InvoiceDetail();
            detail.setProductName(d.getProductName());
            detail.setQuantity(d.getQuantity());
            detail.setUnitPrice(d.getUnitPrice());
            detail.setSubtotal((double) (d.getQuantity() * d.getUnitPrice()));
            detail.setInvoice(saved);
            detailRepo.save(detail);
        }
        return saved;
    }
}