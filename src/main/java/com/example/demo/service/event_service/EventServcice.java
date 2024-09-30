package com.example.demo.service.event_service;

import com.example.demo.dto.EventDTO;
import com.example.demo.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventServcice {
    void save(EventDTO event);
    void update(EventDTO event);
    void delete(EventDTO event);
    Optional<EventDTO> findById(int id);
    EventDTO findByName(String name);
    List<EventDTO> findAll();
}
