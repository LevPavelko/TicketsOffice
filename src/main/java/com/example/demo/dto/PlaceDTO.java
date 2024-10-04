package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class PlaceDTO {
    private int id;
    private String address;
    private String name;

    @Override
    public String toString() {
        return "PlaceDTO [id=" + id + ", address=" + address + ", name=" + name + "]";
    }
}
