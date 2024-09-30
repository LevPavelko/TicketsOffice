package com.example.demo.service.ticket_service;

import com.example.demo.dto.TicketDTO;
import com.example.demo.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    void save(TicketDTO ticket);
    int[] saveTicketsList(List<TicketDTO> tickets);
    void update(TicketDTO ticket);
    List<TicketDTO> findAll();
    Optional<TicketDTO> findById(int id);
    void deleteById(int id);


}
