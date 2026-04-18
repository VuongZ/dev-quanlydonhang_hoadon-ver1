package com.example.demo.Repository;

import com.example.demo.entity.customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerepository extends JpaRepository<customers, Integer> {}

