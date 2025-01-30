package com.example.demo.controller;

import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dao.event.EventRepository;
import com.example.demo.dao.ticket.TicketRepository;
import com.example.demo.dto.EventDTO;
import com.example.demo.dto.PlaceDTO;
import com.example.demo.dto.TicketDTO;
import com.example.demo.dto.TicketPackDTO;
import com.example.demo.model.TicketStatus;
import com.example.demo.service.event_service.EventServiceImpl;
import com.example.demo.service.place_service.PlaceServiceImpl;
import com.example.demo.service.ticket_service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class EventController {
    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private PlaceServiceImpl placeServiceImpl;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    @RequestMapping("/events")
    public String events(Model model) {
        List<EventDTO> events = eventService.findAll();
        List<EventDTO> sortedEvents = events.stream()
                .sorted(Comparator.comparing(EventDTO::getEvent_date))
                .collect(Collectors.toList());

        model.addAttribute("events", sortedEvents);
        return "events";
    }

    @RequestMapping("/createEvent")
    public String createEvent(Model model) {
        model.addAttribute("event", new EventDTO());
        model.addAttribute("places", placeServiceImpl.findAll());
        return "createEvent";
    }

    @PostMapping("/createEventForm")
    public String createEventForm(Model model, EventDTO event, Integer placeId) {

        if(event.getEvent_date() == null || placeId == 0) {
            model.addAttribute("event", event);
            model.addAttribute("message", "Every fields have to be filled.");
            return "createEvent";


        }
        if (event.getEvent_date().isBefore(LocalDate.now())) {
            model.addAttribute("event", event);
            model.addAttribute("message", "The event date cannot be in the past");
            return "createEvent";
        }

        boolean isEventInThisDate = false;
        Optional<PlaceDTO> place = placeServiceImpl.findById(placeId);
        List<EventDTO> eventsByPlace = eventService.findByPlace(place.get());
        for(EventDTO eventDTO : eventsByPlace) {
            if(eventDTO.getEvent_date().equals(event.getEvent_date())) {
                isEventInThisDate = true;
                model.addAttribute("event", event);
                model.addAttribute("message", "There is already an event on this day");
                return "createEvent";
            }
        }

        event.setPlace(place.get());
        Integer eventId = eventService.save(event);
        Optional<EventDTO> eventDTO = eventService.findById(eventId);
        createTickets(event.getTickets(), eventDTO.get());

        return "redirect:/events";
    }




    public void createTickets(List<TicketPackDTO> tickets, EventDTO event) {

        int number = 1;
        for(TicketPackDTO ticket : tickets){
            for(int i = 0; i < ticket.getCount(); i++){
                TicketDTO ticketDTO = new TicketDTO();
                ticketDTO.setEvent(event);
                ticketDTO.setStatus(TicketStatus.FREE);
                ticketDTO.setNumber(number);
                ticketDTO.setCost(ticket.getCost());
                ticketServiceImpl.save(ticketDTO);
                number++;
            }

        }
    }
}
