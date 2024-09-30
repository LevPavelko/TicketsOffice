package com.example.demo.service.event_service;

import com.example.demo.convert.ConvertToDTO;
import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dao.event.EventRepository;
import com.example.demo.dto.EventDTO;
import com.example.demo.model.Customer;
import com.example.demo.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventServcice{
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ConvertToDTO convertToDTO;
    @Autowired
    private ConvertToEntity convertToEntity;

    @Override
    public void save (EventDTO eventDTO) {
        Event event = convertToEntity.convertEventDTOToEntity(eventDTO);
        eventRepository.save(event);
    }

    @Override
    public void update (EventDTO eventDTO) {
        Event event = convertToEntity.convertEventDTOToEntity(eventDTO);
        eventRepository.save(event);
        eventRepository.save(event);
    }

    @Override
    public void delete (EventDTO eventDTO) {
        Event event = convertToEntity.convertEventDTOToEntity(eventDTO);
        eventRepository.delete(event);
    }

    @Override
    public List<EventDTO> findAll() {
        List<Event> event = eventRepository.findAllWithTickets();
        return event.stream()
                .map(convertToDTO::convertEventToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EventDTO> findById(int id){
        Optional<Event> event = eventRepository.findById(id);
        return event.map(convertToDTO::convertEventToDTO);

    }

    @Override
    public EventDTO findByName(String name) {
        Event event = eventRepository.findByName(name);
        return convertToDTO.convertEventToDTO(event);
    }
}
