package com.example.demo.service.ticket_service;

import com.example.demo.dao.ticket.TicketRepository;
import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void save (Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public int[] saveTicketsList(List<Ticket> tickets) {
        ticketRepository.saveAll(tickets);
        return new int[0];
    }

    @Override
    public void update (Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        ticketRepository.deleteById(id);
    }

}
