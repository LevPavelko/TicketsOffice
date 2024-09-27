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
public class ConvertToDTO {
    public CustomerDTO convertCustomerToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());

        if (customer.getTickets() != null) {
            List<TicketDTO> tickets = customer.getTickets().stream()
                    .map(this::convertTicketToDTO)
                    .collect(Collectors.toList());
            customerDTO.setTickets(tickets);
        }

        return customerDTO;
    }

    private TicketDTO convertTicketToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setCost(ticket.getCost());
        ticketDTO.setNumber(ticket.getNumber());
        ticketDTO.setStatus(ticket.getStatus());
        ticketDTO.setEvent(convertEventToDTO(ticket.getEvent()));
        ticketDTO.setCustomer(convertCustomerToDTO(ticket.getCustomer()));
        return ticketDTO;
    }

    private PlaceDTO convertPlaceToDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(place.getId());
        placeDTO.setName(place.getName());
        placeDTO.setAddress(place.getAddress());
        return placeDTO;
    }

    private EventDTO convertEventToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setEvent_date(event.getEventDate());

        if (event.getTickets() != null) {
            List<TicketDTO> tickets = event.getTickets().stream()
                    .map(this::convertTicketToDTO)
                    .collect(Collectors.toList());
            eventDTO.setTickets(tickets);
        }

        eventDTO.setPlace(convertPlaceToDTO(event.getPlace()));
        return eventDTO;
    }
}
