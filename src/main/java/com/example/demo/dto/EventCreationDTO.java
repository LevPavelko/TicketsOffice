package com.example.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class EventCreationDTO {
    private int id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date event_date;



    List<TicketPackDTO> tickets = new ArrayList<>();

    public PlaceDTO place;

}
