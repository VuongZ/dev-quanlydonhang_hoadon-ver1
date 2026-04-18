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

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody InvoiceDTO dto) {
        return ResponseEntity.ok(invoiceService.create(dto));
    }
}
