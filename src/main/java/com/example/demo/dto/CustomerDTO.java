package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CustomerDTO {
    private int id;
   private UserDTO user;
    private UserRoleDTO role;
    private List<TicketDTO> tickets;

}
