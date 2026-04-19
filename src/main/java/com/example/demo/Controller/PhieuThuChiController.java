package com.example.demo.Controller;

import com.example.demo.DTO.PhieuThuChiRequest;
import com.example.demo.DTO.PhieuThuChiResponse;
import com.example.demo.Service.PhieuThuChiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phieu-thu-chi")
@RequiredArgsConstructor
public class PhieuThuChiController {

    private final PhieuThuChiService phieuThuChiService;

    @GetMapping
    public ResponseEntity<List<PhieuThuChiResponse>> getAll() {
        return ResponseEntity.ok(phieuThuChiService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhieuThuChiResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(phieuThuChiService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PhieuThuChiResponse> create(@RequestBody PhieuThuChiRequest request) {
        return ResponseEntity.ok(phieuThuChiService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhieuThuChiResponse> update(
            @PathVariable Integer id,
            @RequestBody PhieuThuChiRequest request) {
        return ResponseEntity.ok(phieuThuChiService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        phieuThuChiService.delete(id);
        return ResponseEntity.noContent().build();
    }
}