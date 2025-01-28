package com.example.demo.controller;

import com.example.demo.dto.EventDTO;
import com.example.demo.service.event_service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class HomeContoller {
    @Autowired
    private EventServiceImpl eventService;


    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping("/search")
    public String search(Model model) {
        return "search";
    }

    @PostMapping("/searchForm")
    public String searchForm(Model model, String searchInput) {
        List<EventDTO> events = eventService.findByNameContaining(searchInput);
        List<EventDTO> sortedEvents = events.stream()
                .sorted(Comparator.comparing(EventDTO::getEvent_date))
                .collect(Collectors.toList());
        model.addAttribute("events", sortedEvents);
        model.addAttribute("searchInput", searchInput);

        return "search";
    }
}
