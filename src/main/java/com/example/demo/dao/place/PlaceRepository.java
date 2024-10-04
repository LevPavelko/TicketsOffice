package com.example.demo.dao.place;


import com.example.demo.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place,Integer> {
    @Query("SELECT p FROM Place p WHERE p.name = :name AND p.address = :address")
    Place findByNameAndAddress(String name, String address);
}
