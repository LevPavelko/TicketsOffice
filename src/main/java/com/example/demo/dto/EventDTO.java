package com.example.demo.dto;

import com.example.demo.model.Place;
import com.example.demo.model.Ticket;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
public class EventDTO {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate event_date;
    private String name;
    private PlaceDTO place;
    private int ticketPrice;
    private int ticketCount;
}
