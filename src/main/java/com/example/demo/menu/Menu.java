package com.example.demo.menu;

import com.example.demo.service.customer_service.CustomerService;
import com.example.demo.service.customer_service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Menu {
    @Autowired
    private CustomerServiceImpl customerService;

    public void start(){
        System.out.println("Menu start");
        customerService.findAll().forEach(customer -> {
            System.out.println("Customer:");
            System.out.println(customer.getName());
            System.out.println(customer.getEmail());
            if (customer.getTickets() != null && !customer.getTickets().isEmpty()) {
                System.out.println("Tickets:");
                customer.getTickets().forEach(ticket -> System.out.println(ticket));
            } else {
                System.out.println("No tickets found.");
            }
            System.out.println("\n");

        });
    }


}
