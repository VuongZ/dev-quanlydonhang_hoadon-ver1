package com.example.demo.Repository;

import com.example.demo.entity.InvoiceTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceTemplateRepository extends JpaRepository<InvoiceTemplate, Integer> {
    Optional<InvoiceTemplate> findByIsDefaultTrue();
}