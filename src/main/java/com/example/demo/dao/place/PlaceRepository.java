package com.example.demo.dao.place;


import com.example.demo.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Integer> {
    List<Place> findByName(String name);
}
