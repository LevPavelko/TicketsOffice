package com.example.demo.dao.customer;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
//    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.tickets")
//    List<Customer> findAllWithTickets();
//
//    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.tickets WHERE c.id = :id")
//    Optional<Customer> findByIdWithTickets(@Param("id") int id);

}

