package com.example.demo.dao.customer;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.tickets")
    List<Customer> findAllWithTickets();
}

