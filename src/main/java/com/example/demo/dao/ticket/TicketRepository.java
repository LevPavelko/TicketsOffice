package com.example.demo.dao.ticket;

import com.example.demo.model.Customer;
import com.example.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
