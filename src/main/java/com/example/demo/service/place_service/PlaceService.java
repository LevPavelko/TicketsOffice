package com.example.demo.service.place_service;

import com.example.demo.model.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {
    void save(Place place);
    void update(Place place);
    void delete(Place place);
    List<Place> findAll();
    Optional<Place> findById(int id);
    List<Place> findByName(String name);
}
