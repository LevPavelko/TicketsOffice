package com.example.demo.model;


import lombok.*;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;
@Entity
@Data
@EqualsAndHashCode(exclude = {"tickets"})
@ToString(exclude = {"tickets"})
@Table(name = "Events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;






}
