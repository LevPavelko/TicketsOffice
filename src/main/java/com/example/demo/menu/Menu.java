package com.example.demo.menu;

import com.example.demo.dao.ticket.TicketRepository;
import com.example.demo.dto.*;
import com.example.demo.model.TicketStatus;
import com.example.demo.service.customer_service.CustomerService;
import com.example.demo.service.customer_service.CustomerServiceImpl;
import com.example.demo.service.event_service.EventServiceImpl;
import com.example.demo.service.special_service.SpecialService;
import com.example.demo.service.ticket_service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class Menu {
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private TicketServiceImpl ticketServiceImpl;
    @Autowired
    private EventServiceImpl eventServiceImpl;
    @Autowired
    private SpecialService specialService;


    public void start(){

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\nMenu");
            System.out.println("1. Show customers");
            System.out.println("2. Show tickets");
            System.out.println("3. Show events");
            System.out.println("4. Show places");
            System.out.println("5. Create customer");
            System.out.println("6. Create ONE ticket");
            System.out.println("7. Delete ticket");
            System.out.println("8. Delete customer");
            System.out.println("9. Update ticket");
            System.out.println("10. Create event");
            System.out.println("0. Exit");

            switch (scanner.nextInt()) {
                case 1:
                    customerService.findAll().forEach(customer -> {
                        System.out.println("Customer:");
                        System.out.println(customer.getName());
                        System.out.println(customer.getEmail());
                        if (customer.getTickets() != null && !customer.getTickets().isEmpty()) {
                            System.out.println("Tickets of current customer:");
                            customer.getTickets().forEach(ticket -> System.out.println(ticket));
                        } else {
                            System.out.println("No tickets found.");
                        }
                        System.out.println("\n");

                    });
                    break;
                case 2:
                    ticketServiceImpl.findAll().forEach(ticket -> {
                        System.out.println("Ticket:");
                        System.out.println(ticket.toString());
                    });
                case 3:
                    eventServiceImpl.findAll().forEach(event -> {
                        System.out.println("Event:");
                        System.out.println(event.getName());
                        System.out.println(event.getPlace());
                        System.out.println(event.getTickets());
                        System.out.println(event.getEvent_date());

                        System.out.println("\n");

                    });
                    break;
                case 5:
                    CustomerDTO customerDTO = new CustomerDTO();
                    System.out.println("Enter a name of customer: ");
                    customerDTO.setName(scanner.next());
                    System.out.println("Enter a email of customer: ");
                    customerDTO.setEmail(scanner.next());
                    System.out.println("Enter a phone: "); //validation here
                    String phone = scanner.next();


                    if (isValidPhoneNumber(phone)) {
                        customerDTO.setPhone(Integer.parseInt(phone));
                        customerService.save(customerDTO);
                        break;
                    } else {
                        System.out.println("Invalid phone number. Please try again.");
                        break;
                    }

                case 6:
                    TicketDTO ticketDTO = new TicketDTO();
                    System.out.println("Enter the price of ticket: ");
                    ticketDTO.setCost(scanner.nextInt());
                    System.out.println("Enter the place: ");
                    ticketDTO.setNumber(scanner.nextInt());
                    System.out.println("Enter the event: ");
                    String eventName = scanner.next();
                    EventDTO event = eventServiceImpl.findByName(eventName);

                    if (event != null) {
                        ticketDTO.setEvent(event);
                        ticketDTO.setStatus(TicketStatus.FREE);
                        ticketServiceImpl.save(ticketDTO);
                    } else {
                        System.out.println("Event not found.");
                        break;
                    }
                case 7:
                   ticketServiceImpl.findAll().forEach(ticket -> {
                        System.out.println("Ticket:");
                        System.out.println(ticket.toString());
                    }) ;
                    System.out.println("Enter id of ticket to delete: ");
                    int id = scanner.nextInt();
                    ticketServiceImpl.deleteById(id);
                    System.out.println("Ticket deleted.");
                    break;
                case 8:
                    customerService.findAll().forEach(customer -> {
                        System.out.println(customer.toString());
                    });
                    System.out.println("Enter id of customer to delete: ");
                    int customerId = scanner.nextInt();
                    customerService.findById(customerId).ifPresent(customer -> {
                        customerService.delete(customer);
                    });
                    System.out.println("Customer deleted.");
                    break;
                case 9:
                    ticketServiceImpl.findAll().forEach(ticket -> {
                        System.out.println("Ticket:");
                        System.out.println(ticket.toString());
                    }) ;
                    System.out.println("Enter id of ticket to update: ");
                    int ticketId = scanner.nextInt();
                    System.out.println("Enter a new cost: ");
                    int newCost = scanner.nextInt();
                    System.out.println("Enter new number: ");
                    int newNumber = scanner.nextInt();
                    System.out.println("Enter new status (FREE or SOLD): ");
                    String newStatus = scanner.next();

                    customerService.findAll().forEach(customer -> {
                        System.out.println(customer.toString());
                    });
                    System.out.println("Enter id of customer to update: ");
                    customerId = scanner.nextInt();

                    System.out.println("Enter new event: ");
                    String newEvent = scanner.next();
                    EventDTO eventDTO = eventServiceImpl.findByName(newEvent);

                    if (eventDTO != null) {
                        TicketDTO updateTicketDTO = new TicketDTO();
                        updateTicketDTO.setId(ticketId);
                        updateTicketDTO.setCost(newCost);
                        updateTicketDTO.setNumber(newNumber);
                        if(newStatus.equals("FREE")){
                            updateTicketDTO.setStatus(TicketStatus.FREE);
                        }
                        else if(newStatus.equals("SOLD")){
                            updateTicketDTO.setStatus(TicketStatus.SOLD);
                        }
                        customerService.findByIdWithTickets(customerId).ifPresent(customer -> {
                            updateTicketDTO.setCustomer(customer);
                        });

                        updateTicketDTO.setEvent(eventDTO);
                        ticketServiceImpl.update(updateTicketDTO);

                    } else {
                        System.out.println("Event not found.");
                        break;
                    }
                case 10:
                    EventCreationDTO eventCreationDTO = new EventCreationDTO();

                    eventCreationDTO.setName("00000");
                    eventCreationDTO.setEvent_date(Date.valueOf("2024-06-05"));
                    PlaceDTO placeDTO = new PlaceDTO();
                    placeDTO.setName("hzhz");
                    placeDTO.setAddress("Gasse 2");

                    eventCreationDTO.setPlace(placeDTO);

                    List<TicketPackDTO> list = new ArrayList<>();
                    TicketPackDTO ticketPackDTO = new TicketPackDTO(30,2);
                    TicketPackDTO ticketPackDTO2 = new TicketPackDTO(105,3);

                    list.add(ticketPackDTO);
                    list.add(ticketPackDTO2);
                    eventCreationDTO.setTickets(list);
                    specialService.createEvent(eventCreationDTO);
                    break;



                case 0:
                    System.exit(0);


            }

        }

    }

    public static boolean isValidPhoneNumber(String phoneNumber) {

        return phoneNumber.matches("\\d{10}");
    }

}
