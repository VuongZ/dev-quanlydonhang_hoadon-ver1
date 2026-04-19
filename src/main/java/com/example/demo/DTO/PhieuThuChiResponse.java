package com.example.demo.DTO;

import com.example.demo.enums.PhieuMethod;
import com.example.demo.enums.PhieuStatus;
import com.example.demo.enums.PhieuType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PhieuThuChiResponse {
    private Integer id;
    private String name;
    private LocalDate dateCreate;
    private PhieuType type;
    private BigDecimal price;
    private PhieuStatus status;
    private PhieuMethod method;
    private Integer customerId;

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getInvoicesId() {
        return invoicesId;
    }

    public void setInvoicesId(Integer invoicesId) {
        this.invoicesId = invoicesId;
    }

    private String customerName;
    private Integer invoicesId;
}