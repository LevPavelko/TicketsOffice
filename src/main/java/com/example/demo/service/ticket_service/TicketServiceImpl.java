package com.example.demo.service.ticket_service;

import com.example.demo.convert.ConvertToDTO;
import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dao.ticket.TicketRepository;
import com.example.demo.dto.TicketDTO;
import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private ConvertToDTO convertToDTO;


    @Override
    public void save (TicketDTO ticketDTO) {
        Ticket ticket = convertToEntity.convertTicketDTOToEntity(ticketDTO);
        ticketRepository.save(ticket);
    }

    @Override
    public int[] saveTicketsList(List<TicketDTO> ticketsDTO) {
        List<Ticket> tickets = ticketsDTO.stream()
                .map(convertToEntity::convertTicketDTOToEntity)
                .collect(Collectors.toList());
        List<Ticket> savedTickets = ticketRepository.saveAll(tickets);
        int[] ticketIds = savedTickets.stream()
                .mapToInt(Ticket::getId)
                .toArray();

        return ticketIds;
    }

    @Override
    public void update (TicketDTO ticketDTO) {
        Ticket ticket = convertToEntity.convertTicketDTOToEntity(ticketDTO);
        ticketRepository.save(ticket);
    }

    @Override
    public Optional<TicketDTO> findById(int id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.map(convertToDTO::convertTicketToDTOWithoutEvent);
        //сомнительный момент вызова convertTicketToDTOWithoutEvent
        //возможно сделать другой метод типо convertTicketToDTO с ивентом кароче
    }

    @Override
    public List<TicketDTO> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        //тоже такой момент сомнительный момент,
        // наверное сделать метод как в customer типо findAllWithEvent
        return tickets.stream()
                .map(convertToDTO::convertTicketToDTOWithoutEvent) // тоже самое что писал выше в методе findById
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        ticketRepository.deleteById(id);
    }

}
