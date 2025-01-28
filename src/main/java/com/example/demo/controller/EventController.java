package com.example.demo.controller;

import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dao.event.EventRepository;
import com.example.demo.dto.EventDTO;
import com.example.demo.service.event_service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EventController {
    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private ConvertToEntity convertToEntity;

    @RequestMapping("/events")
    public String events(Model model) {
        List<EventDTO> events = eventService.findAll();
        List<EventDTO> sortedEvents = events.stream()
                .sorted(Comparator.comparing(EventDTO::getEvent_date))
                .collect(Collectors.toList());

        model.addAttribute("events", sortedEvents);
        return "events";
    }
}
