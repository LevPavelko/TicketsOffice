package com.example.demo.convert;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.EventDTO;
import com.example.demo.dto.PlaceDTO;
import com.example.demo.dto.TicketDTO;
import com.example.demo.model.Customer;
import com.example.demo.model.Event;
import com.example.demo.model.Place;
import com.example.demo.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvertToEntity {
    public Customer convertCustomerToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());

        if (customerDTO.getTickets() != null) {
            List<Ticket> tickets = customerDTO.getTickets().stream()
                    .map(this::convertTicketDTOToEntity)
                    .collect(Collectors.toList());
            customer.setTickets(tickets);
        }


        return customer;
    } //done


    public Ticket convertTicketDTOToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setCost(ticketDTO.getCost());
        ticket.setNumber(ticketDTO.getNumber());
        ticket.setStatus(ticketDTO.getStatus());
        ticket.setEvent(convertEventDTOToEntity(ticketDTO.getEvent()));
        ticket.setCustomer(convertCustomerToEntity(ticketDTO.getCustomer()));
        return ticket;
    } //done

    public Place convertPlaceDTOToEntity(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setId(placeDTO.getId());
        place.setName(placeDTO.getName());
        place.setAddress(placeDTO.getAddress());
        return place;
    } //done

    public Event convertEventDTOToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setEventDate(eventDTO.getEvent_date());
        if (eventDTO.getTickets() != null) {
            List<Ticket> tickets = eventDTO.getTickets().stream()
                    .map(this::convertTicketDTOToEntity)
                    .collect(Collectors.toList());
            event.setTickets(tickets);
        }
        event.setPlace(convertPlaceDTOToEntity(eventDTO.getPlace()));

        return event;
    } //done
}
