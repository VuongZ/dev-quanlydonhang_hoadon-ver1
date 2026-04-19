package com.example.demo.DTO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PhieuThuChiRequest {
    private String name;
    private LocalDate dateCreate;
    private String type;    // ← String thay vì PhieuType
    private BigDecimal price;
    private String status;  // ← String thay vì PhieuStatus
    private String method;  // ← String thay vì PhieuMethod
    private Integer customerId;
    private Integer invoicesId;
}