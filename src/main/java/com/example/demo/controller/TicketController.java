package com.example.demo.controller;

import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.EventDTO;
import com.example.demo.dto.TicketDTO;
import com.example.demo.model.TicketStatus;
import com.example.demo.service.customer_service.CustomerServiceImpl;
import com.example.demo.service.event_service.EventServiceImpl;
import com.example.demo.service.ticket_service.TicketService;
import com.example.demo.service.ticket_service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class TicketController {
    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private ConvertToEntity convertToEntity;

    @RequestMapping("/buyTicket")
    public String buyTicket(@RequestParam("eventId") int eventId, HttpSession session, Model model) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if(customerId == null){
            return "redirect:/login";
        }
        Optional<EventDTO> event = eventService.findById(eventId);
        if (event == null) {

            return "500";
        }


        TicketDTO freeTicket = ticketService.findFreeTicketByEventId(eventId);
        if (freeTicket == null) {
            model.addAttribute("error", "No available tickets for this event");
            return "500";
        }

        Optional<CustomerDTO> customerDto = customerService.findById(customerId);

        freeTicket.setStatus(TicketStatus.SOLD);
        freeTicket.setCustomer(customerDto.get());
        ticketService.save(freeTicket);

        return "redirect:/myTickets";

    }

    @RequestMapping("/myTickets")
    public String myTickets(HttpSession session, Model model) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        List<TicketDTO> tickets = ticketService.findTicketsByCustomer(customerId);
        List<TicketDTO> sortedTickets = tickets.stream()
                .sorted(Comparator.comparing(ticket -> ticket.getEvent().getEvent_date()))
                .collect(Collectors.toList());
        model.addAttribute("tickets", sortedTickets);
        return "myTickets";
    }

}
