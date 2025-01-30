package com.example.demo.dto;

import com.example.demo.model.TicketStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TicketDTO {
    private int id;
    private int cost;
    private int number;
    private CustomerDTO customer;
    private EventDTO event;
    private TicketStatus status;



}
