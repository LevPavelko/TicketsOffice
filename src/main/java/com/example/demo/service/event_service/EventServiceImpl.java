package com.example.demo.service.event_service;

import com.example.demo.dao.event.EventRepository;
import com.example.demo.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventServcice{
    @Autowired
    private EventRepository eventRepository;

    @Override
    public void save (Event event) {
        eventRepository.save(event);
    }

    @Override
    public void update (Event event) {
        eventRepository.save(event);
    }

    @Override
    public void delete (Event event) {
        eventRepository.delete(event);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findById(int id){
        return  eventRepository.findById(id);

    }
}
