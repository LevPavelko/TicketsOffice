package com.example.demo.service.user_service;

import com.example.demo.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    Integer save(UserDTO userDTO);
    Optional<UserDTO> findById(Integer id);
}
