package com.example.demo.Controller;

import com.example.demo.DTO.InvoiceDTO;
import com.example.demo.Service.InvoiceService;
import com.example.demo.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAll() {
        return ResponseEntity.ok(invoiceService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Invoice>> search(
            @RequestParam(required = false) String invoiceNumber,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) Integer customerId
    ) {
        return ResponseEntity.ok(invoiceService.search(invoiceNumber, fromDate, toDate, customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(invoiceService.getById(id));
    }

    @PostMapping                                                          // ← Chỉ giữ 1 cái
    public ResponseEntity<Invoice> create(@RequestBody InvoiceDTO dto) {
        return ResponseEntity.ok(invoiceService.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        invoiceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/preview-html")
    public ResponseEntity<String> previewHtml(@PathVariable Integer id) {
        return ResponseEntity.ok(invoiceService.generateHtml(id));
    }
}