package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private int phone;
    private UserRoleDTO role;

}
