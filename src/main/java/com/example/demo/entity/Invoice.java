package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "tax_rate")
    private Float taxRate;

    @Column(name = "final_amount")
    private Double finalAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Float taxRate) {
        this.taxRate = taxRate;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public customers getCustomer() {
        return customer;
    }

    public void setCustomer(customers customer) {
        this.customer = customer;
    }

    public InvoiceTemplate getTemplate() {
        return template;
    }

    public void setTemplate(InvoiceTemplate template) {
        this.template = template;
    }

    public List<InvoiceDetail> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetail> details) {
        this.details = details;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private customers customer;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private InvoiceTemplate template;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetail> details;
}
