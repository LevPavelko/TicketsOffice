package com.example.demo.service.customer_service;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void save(CustomerDTO customer);
    void update(CustomerDTO customer);
    void delete(CustomerDTO customer);
    Optional<CustomerDTO> findById(int id);
    List<CustomerDTO> findAll();

}
