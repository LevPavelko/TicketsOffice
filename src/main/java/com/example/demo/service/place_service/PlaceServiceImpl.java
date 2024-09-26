package com.example.demo.service.place_service;

import com.example.demo.dao.place.PlaceRepository;
import com.example.demo.model.Place;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public void save(Place place) {
        placeRepository.save(place);
    }

    @Override
    public void update(Place place) {
        placeRepository.save(place);
    }

    @Override
    public void delete(Place place) {
        placeRepository.delete(place);
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public Optional<Place> findById(int id) {
        return placeRepository.findById(id);
    }

    @Override
    public List<Place> findByName(String name) {
        return placeRepository.findByName(name);
    }


}
