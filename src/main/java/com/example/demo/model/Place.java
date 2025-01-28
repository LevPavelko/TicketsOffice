package com.example.demo.model;


import lombok.*;
import javax.persistence.*;
@Entity
@Data
@EqualsAndHashCode
@ToString
@Table(name = "Placeses")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;
}
