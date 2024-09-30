package com.example.demo.dto;

import com.example.demo.model.Place;
import com.example.demo.model.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class EventDTO {
    private int id;
    private Date event_date;
    private String name;
    private List<TicketDTO> tickets;
    private PlaceDTO place;
}
