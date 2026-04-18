package com.example.demo.Service;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.Repository.customerepository;
import com.example.demo.entity.customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private customerepository customerRepo;

    public List<customers> getAll() { return customerRepo.findAll(); }

    public customers getById(Integer id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy customer"));
    }

    public customers create(CustomerDTO dto) {
        customers c = new customers();
        c.setName(dto.getName());
        c.setTaxcode(dto.getTaxCode());
        c.setAddress(dto.getAddress());
        c.setPhone(dto.getPhone());
        return customerRepo.save(c);
    }

    public customers update(Integer id, CustomerDTO dto) {
        customers c = getById(id);
        c.setName(dto.getName());
        c.setAddress(dto.getAddress());
        c.setPhone(dto.getPhone());
        return customerRepo.save(c);
    }

    public void delete(Integer id) { customerRepo.deleteById(id); }
}