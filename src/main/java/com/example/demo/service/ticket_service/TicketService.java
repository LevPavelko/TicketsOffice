package com.example.demo.service.ticket_service;

import com.example.demo.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    void save(Ticket ticket);
    int[] saveTicketsList(List<Ticket> tickets);
    void update(Ticket ticket);
    List<Ticket> findAll();
    Optional<Ticket> findById(int id);
    void deleteById(int id);


}
