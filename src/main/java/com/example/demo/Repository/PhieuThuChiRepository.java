package com.example.demo.Repository;

import com.example.demo.entity.PhieuThuChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuThuChiRepository extends JpaRepository<PhieuThuChi, Integer> {
}