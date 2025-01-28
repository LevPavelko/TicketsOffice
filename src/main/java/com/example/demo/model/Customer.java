package com.example.demo.model;


import lombok.*;

import java.util.List;
import javax.persistence.*;
@Entity
@Data
@EqualsAndHashCode(exclude = {"tickets"})
@ToString(exclude = {"tickets"})
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private int phone;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;


}
