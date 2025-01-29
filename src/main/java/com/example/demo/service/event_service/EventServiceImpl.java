package com.example.demo.service.event_service;

import com.example.demo.convert.ConvertToDTO;
import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dao.event.EventRepository;
import com.example.demo.dao.ticket.TicketRepository;
import com.example.demo.dto.EventDTO;
import com.example.demo.dto.PlaceDTO;
import com.example.demo.dto.TicketDTO;
import com.example.demo.model.*;
import com.example.demo.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EventServiceImpl implements EventServcice{
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ConvertToDTO convertToDTO;
    @Autowired
    private ConvertToEntity convertToEntity;

    @Override
    public Integer save (EventDTO eventDTO) {
        Event event = convertToEntity.convertEventDTOToEntity(eventDTO);
        eventRepository.save(event);
        return event.getId();
    }

    @Override
    public void update (EventDTO eventDTO) {
        Event event = convertToEntity.convertEventDTOToEntity(eventDTO);
        eventRepository.save(event);
    }

    @Override
    public void delete (EventDTO eventDTO) {
        Event event = convertToEntity.convertEventDTOToEntity(eventDTO);
        eventRepository.delete(event);
    }

    @Override
    public List<EventDTO> findAll() {

        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventsDto = events.stream()
                .map(convertToDTO::convertEventToDTO)
                .collect(Collectors.toList());

        for (EventDTO event : eventsDto) {
            Ticket ticket = ticketRepository.findFirstByEventIdAndStatus(event.getId(), TicketStatus.FREE);
            if (ticket != null) {
                event.setTicketPrice(ticket.getCost());
            } else {
                event.setTicketPrice(0);
            }
        }
        return eventsDto;
    }

    @Override
    public Optional<EventDTO> findById(int id){
        Optional<Event> event = eventRepository.findById(id);
        return event.map(convertToDTO::convertEventToDTO);

    }

    @Override
    public EventDTO findByName(String name) {
        Event event = eventRepository.findByName(name);
        return convertToDTO.convertEventToDTO(event);
    }

    @Override
    public void deleteAll() {
        eventRepository.deleteAll();
    }

    @Override
    public List<EventDTO> findByNameContaining(String name) {

        List<Event> events = eventRepository.findByNameContainingIgnoreCase(name);
        List<EventDTO> eventsDto = events.stream()
                .map(convertToDTO::convertEventToDTO)
                .collect(Collectors.toList());
        for (EventDTO event : eventsDto) {
            Ticket ticket = ticketRepository.findFirstByEventIdAndStatus(event.getId(), TicketStatus.FREE);
            if (ticket != null) {
                event.setTicketPrice(ticket.getCost());
            } else {
                event.setTicketPrice(0);
            }
        }

        return eventsDto;
    }

    @Override
    public List<EventDTO> findByPlace(PlaceDTO placeDTO) {
        Place place = convertToEntity.convertPlaceDTOToEntity(placeDTO);
        List<Event> events = eventRepository.findByPlace(place);
        return events.stream()
                .map(convertToDTO::convertEventToDTO)
                .collect(Collectors.toList());
    }
}
