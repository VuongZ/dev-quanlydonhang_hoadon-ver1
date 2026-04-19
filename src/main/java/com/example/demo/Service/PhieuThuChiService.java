package com.example.demo.Service;

import com.example.demo.DTO.PhieuThuChiRequest;
import com.example.demo.DTO.PhieuThuChiResponse;
import com.example.demo.Repository.InvoiceRepository;
import com.example.demo.Repository.PhieuThuChiRepository;
import com.example.demo.Repository.customerepository;
import com.example.demo.entity.Invoice;
import com.example.demo.entity.PhieuThuChi;
import com.example.demo.entity.customers;
import com.example.demo.enums.PhieuMethod;
import com.example.demo.enums.PhieuStatus;
import com.example.demo.enums.PhieuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhieuThuChiService {

    @Autowired private PhieuThuChiRepository phieuThuChiRepo;
    @Autowired private customerepository customerRepo;
    @Autowired private InvoiceRepository invoiceRepo;

    public List<PhieuThuChiResponse> getAll() {
        return phieuThuChiRepo.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PhieuThuChiResponse getById(Integer id) {
        PhieuThuChi entity = phieuThuChiRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu với id: " + id));
        return toResponse(entity);
    }

    public PhieuThuChiResponse create(PhieuThuChiRequest request) {
        PhieuThuChi entity = toEntity(request);
        return toResponse(phieuThuChiRepo.save(entity));
    }

    public PhieuThuChiResponse update(Integer id, PhieuThuChiRequest request) {
        PhieuThuChi entity = phieuThuChiRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiếu với id: " + id));

        entity.setName(request.getName());
        entity.setDateCreate(request.getDateCreate());
        entity.setPrice(request.getPrice());
        entity.setType(parseType(request.getType()));
        entity.setStatus(parseStatus(request.getStatus()));
        entity.setMethod(parseMethod(request.getMethod()));

        customers customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy customer id: " + request.getCustomerId()));
        entity.setCustomer(customer);

        Invoice invoice = invoiceRepo.findById(request.getInvoicesId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy invoice id: " + request.getInvoicesId()));
        entity.setInvoice(invoice);

        return toResponse(phieuThuChiRepo.save(entity));
    }

    public void delete(Integer id) {
        if (!phieuThuChiRepo.existsById(id)) {
            throw new RuntimeException("Không tìm thấy phiếu với id: " + id);
        }
        phieuThuChiRepo.deleteById(id);
    }

    // ---- Parse String → Enum ----
    private PhieuType parseType(String value) {
        for (PhieuType t : PhieuType.values()) {
            if (t.getValue().equals(value)) return t;
        }
        throw new RuntimeException("Loại phiếu không hợp lệ: " + value);
    }

    private PhieuStatus parseStatus(String value) {
        for (PhieuStatus s : PhieuStatus.values()) {
            if (s.getValue().equals(value)) return s;
        }
        throw new RuntimeException("Trạng thái không hợp lệ: " + value);
    }

    private PhieuMethod parseMethod(String value) {
        for (PhieuMethod m : PhieuMethod.values()) {
            if (m.getValue().equals(value)) return m;
        }
        throw new RuntimeException("Phương thức không hợp lệ: " + value);
    }

    // ---- Mapper ----
    private PhieuThuChiResponse toResponse(PhieuThuChi entity) {
        PhieuThuChiResponse res = new PhieuThuChiResponse();
        res.setId(entity.getId());
        res.setName(entity.getName());
        res.setDateCreate(entity.getDateCreate());
        res.setType(entity.getType());
        res.setPrice(entity.getPrice());
        res.setStatus(entity.getStatus());
        res.setMethod(entity.getMethod());
        res.setCustomerId(entity.getCustomer().getId());
        res.setCustomerName(entity.getCustomer().getName());
        res.setInvoicesId(entity.getInvoice().getId());
        return res;
    }

    private PhieuThuChi toEntity(PhieuThuChiRequest request) {
        PhieuThuChi entity = new PhieuThuChi();
        entity.setName(request.getName());
        entity.setDateCreate(request.getDateCreate());
        entity.setPrice(request.getPrice());
        entity.setType(parseType(request.getType()));
        entity.setStatus(parseStatus(request.getStatus()));
        entity.setMethod(parseMethod(request.getMethod()));

        customers customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy customer id: " + request.getCustomerId()));
        entity.setCustomer(customer);

        Invoice invoice = invoiceRepo.findById(request.getInvoicesId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy invoice id: " + request.getInvoicesId()));
        entity.setInvoice(invoice);

        return entity;
    }
}