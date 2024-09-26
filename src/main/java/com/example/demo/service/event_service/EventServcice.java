package com.example.demo.service.event_service;

import com.example.demo.model.Customer;
import com.example.demo.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventServcice {
    void save(Event event);
    void update(Event event);
    void delete(Event event);
    Optional<Event> findById(int id);
    List<Event> findAll();
}
