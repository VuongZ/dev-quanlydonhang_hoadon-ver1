package com.example.demo.Controller;

import com.example.demo.Repository.InvoiceRepository;
import com.example.demo.Repository.InvoiceTemplateRepository;
import com.example.demo.entity.InvoiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class InvoiceTemplateController {

    @Autowired
    private InvoiceTemplateRepository templateRepo;

    @Autowired
    private InvoiceRepository invoiceRepo; // ✅ thêm dòng này

    @GetMapping
    public ResponseEntity<List<InvoiceTemplate>> getAll() {
        return ResponseEntity.ok(templateRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<InvoiceTemplate> create(@RequestBody InvoiceTemplate t) {
        return ResponseEntity.ok(templateRepo.save(t));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceTemplate> update(@PathVariable Integer id, @RequestBody InvoiceTemplate t) {
        t.setId(id);
        return ResponseEntity.ok(templateRepo.save(t));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (invoiceRepo.existsByTemplateId(id)) { // ✅ dùng instance
            return ResponseEntity.status(409).build();
        }
        templateRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}