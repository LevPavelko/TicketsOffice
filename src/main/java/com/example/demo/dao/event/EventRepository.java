package com.example.demo.dao.event;

import com.example.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {
    @Query("SELECT e FROM Event e WHERE e.name = :name ")
    Event findByName(String name);
}
