package com.example.demo.entity;

import com.example.demo.enums.PhieuMethod;
import com.example.demo.enums.PhieuStatus;
import com.example.demo.enums.PhieuType;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "phieu_thu_chi")
public class PhieuThuChi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "date_create", nullable = false)
    private LocalDate dateCreate;

    @Column(name = "type", nullable = false)
    private PhieuType type;

    @Column(name = "price", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "status", nullable = false)
    private PhieuStatus status;

    @Column(name = "method", nullable = false)
    private PhieuMethod method;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private customers customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoices_id", nullable = false)
    private Invoice invoice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public PhieuType getType() {
        return type;
    }

    public void setType(PhieuType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PhieuStatus getStatus() {
        return status;
    }

    public void setStatus(PhieuStatus status) {
        this.status = status;
    }

    public PhieuMethod getMethod() {
        return method;
    }

    public void setMethod(PhieuMethod method) {
        this.method = method;
    }

    public customers getCustomer() {
        return customer;
    }

    public void setCustomer(customers customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
// ⚠️ KHÔNG có inner enum Type, Status, Method ở đây
}