package com.example.demo.service.special_service;

import com.example.demo.convert.ConvertToDTO;
import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dao.event.EventRepository;
import com.example.demo.dao.place.PlaceRepository;
import com.example.demo.dao.ticket.TicketRepository;
import com.example.demo.dto.*;
import com.example.demo.model.Event;
import com.example.demo.model.Place;
import com.example.demo.model.TicketStatus;
import com.example.demo.service.event_service.EventServiceImpl;
import com.example.demo.service.place_service.PlaceServiceImpl;
import com.example.demo.service.ticket_service.TicketServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class SpecialServiceImpl implements SpecialService {

    @Autowired
    private PlaceServiceImpl placeServiceImpl;
    @Autowired
    private EventServiceImpl eventServiceImpl;
    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    public void createEvent(EventCreationDTO eventCreationDTO) {
        PlaceDTO placeDTO = placeServiceImpl.findByNameAndAddress(eventCreationDTO.place.getName(), eventCreationDTO.place.getAddress());
        PlaceDTO place = new PlaceDTO();
        PlaceDTO createdPlace;
        if(placeDTO == null){

            place.setName(eventCreationDTO.place.getName());
            place.setAddress(eventCreationDTO.place.getAddress());
            placeServiceImpl.save(place);

            createdPlace = placeServiceImpl.findByNameAndAddress(place.getName(), place.getAddress());
        }
        else{
            createdPlace = placeDTO;
        }

        EventDTO event = new EventDTO();
        event.setEvent_date(eventCreationDTO.getEvent_date());
        event.setName(eventCreationDTO.getName());

        event.setPlace(createdPlace);
        eventServiceImpl.save(event);

        EventDTO createdEvent = eventServiceImpl.findByName(event.getName());

        for(TicketPackDTO ticket : eventCreationDTO.getTickets()) {
            int count = ticket.getCount();
            int price = ticket.getCost();
            int number = 1;
            for(int i = 0; i < count; i++){
                TicketDTO ticketDTO = new TicketDTO();
                ticketDTO.setCost(price);
                ticketDTO.setNumber(number++);
                ticketDTO.setStatus(TicketStatus.FREE);
                ticketDTO.setEvent(createdEvent);
                ticketServiceImpl.save(ticketDTO);

            }
        }


    }


}
