package com.example.demo.convert;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvertToEntity {
    public Customer convertCustomerToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setUser(convertUserDTOToEntity(customerDTO.getUser()));
//        if (customerDTO.getTickets() != null) {
//            List<Ticket> tickets = customerDTO.getTickets().stream()
//                    .map(this::convertTicketDTOToEntity)
//                    .collect(Collectors.toList());
//            customer.setTickets(tickets);
//        }


        return customer;
    } //done

    public Ticket convertTicketDTOToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setCost(ticketDTO.getCost());
        ticket.setNumber(ticketDTO.getNumber());

        ticket.setEvent(convertEventDTOToEntity(ticketDTO.getEvent()));
        ticket.setStatus(ticketDTO.getStatus());
        if (ticketDTO.getCustomer() != null) {
            ticket.setCustomer(convertCustomerToEntity(ticketDTO.getCustomer()));
        }

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

        event.setPlace(convertPlaceDTOToEntity(eventDTO.getPlace()));

        return event;
    } //done

    public User convertUserDTOToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setRole(convertUserRoleDTOToEntity(userDTO.getRole()));
        return user;
    }

    public UserRole convertUserRoleDTOToEntity(UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();
        userRole.setId(userRoleDTO.getId());
        userRole.setName(userRoleDTO.getName());
        return userRole;
    }

    public Admin convertAdminDTOToEntity(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setId(adminDTO.getId());
        admin.setUser(convertUserDTOToEntity(adminDTO.getUser()));
        return admin;
    }
}
