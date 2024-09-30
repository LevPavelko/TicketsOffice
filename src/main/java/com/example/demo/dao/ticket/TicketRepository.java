package com.example.demo.dao.ticket;

import com.example.demo.model.Customer;
import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    @Query("SELECT t FROM Ticket t  WHERE t.event = :id ")
    List<Ticket> ticketsOfCurrentEvent(int id);
}
