package com.example.demo.utils;

import com.example.demo.exception.FileException;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Log4j2
@Component
@Service
public class TicketOfficeInit {
    private final TicketOfficeDbInit ticketOfficeDbInit;

    public TicketOfficeInit(TicketOfficeDbInit ticketOfficeDbInit) {
        this.ticketOfficeDbInit = ticketOfficeDbInit;
    }

    public void Initialization() throws FileException {
        ticketOfficeDbInit.deleteAll();
        ticketOfficeDbInit.createPlace();
        ticketOfficeDbInit.createEvent();
    }
}


