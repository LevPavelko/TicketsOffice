package com.example.demo.service.customer_service;

import com.example.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void save(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
    Optional<Customer> findById(int id);
    List<Customer> findAll();

}
