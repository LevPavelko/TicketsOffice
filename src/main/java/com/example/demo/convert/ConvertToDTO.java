package com.example.demo.convert;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvertToDTO {
    public CustomerDTO convertCustomerToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setUser(convertUserToDTO(customer.getUser()));
        try{
            if (!customer.getTickets().isEmpty()) {
                List<TicketDTO> tickets = customer.getTickets().stream()
                        .map(this::convertTicketToDTO)
                        .collect(Collectors.toList());
                customerDTO.setTickets(tickets);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


        return customerDTO;
    }

    public TicketDTO convertTicketToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setCost(ticket.getCost());
        ticketDTO.setNumber(ticket.getNumber());
        ticketDTO.setStatus(ticket.getStatus());
        // System.out.println(ticket.getEvent());
        ticketDTO.setEvent(convertEventToDTO(ticket.getEvent()));
        //ticketDTO.setCustomer(convertCustomerToDTO(ticket.getCustomer()));
        return ticketDTO;
    }

//    private TicketDTO convertTicketToDTO(Ticket ticket) {
//        TicketDTO ticketDTO = new TicketDTO();
//        ticketDTO.setId(ticket.getId());
//        ticketDTO.setCost(ticket.getCost());
//        ticketDTO.setNumber(ticket.getNumber());
//        ticketDTO.setStatus(ticket.getStatus());
//        return ticketDTO;
//    }

    public PlaceDTO convertPlaceToDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(place.getId());
        placeDTO.setName(place.getName());
        placeDTO.setAddress(place.getAddress());
        return placeDTO;
    }

    public EventDTO convertEventToDTO(Event event) {

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setEvent_date(event.getEventDate());

        eventDTO.setPlace(convertPlaceToDTO(event.getPlace()));
        return eventDTO;
    }

    public UserRoleDTO convertUserRoleToDTO(UserRole userRole) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setId(userRole.getId());
        userRoleDTO.setName(userRole.getName());
        return userRoleDTO;
    }

    public UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(convertUserRoleToDTO(user.getRole()));
        return userDTO;
    }

    public AdminDTO convertAdminToDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setUser(convertUserToDTO(admin.getUser()));
        return adminDTO;
    }
}
