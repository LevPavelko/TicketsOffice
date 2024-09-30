package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDTO {
    private int id;
    private String name;
    private String email;
    private int phone;
    private List<TicketDTO> tickets;

    @Override
    public String toString() {
        return "CustomerDTO: [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
    }
}
