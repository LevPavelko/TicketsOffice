package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;


@Entity
@Getter
@Setter
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
