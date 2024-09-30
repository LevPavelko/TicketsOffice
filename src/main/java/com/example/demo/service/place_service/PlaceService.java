package com.example.demo.service.place_service;

import com.example.demo.dto.PlaceDTO;
import com.example.demo.model.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {
    void save(PlaceDTO place);
    void update(PlaceDTO place);
    void delete(PlaceDTO place);
    List<PlaceDTO> findAll();
    Optional<PlaceDTO> findById(int id);
    List<PlaceDTO> findByName(String name);
}
