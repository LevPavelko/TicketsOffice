package com.example.demo.utils;

import com.example.demo.exception.FileException;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ScheduledTask {

    private final TicketOfficeInit init;

    public ScheduledTask(TicketOfficeInit init) {
        this.init = init;
    }

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void scheduleTicketOfficeInitialization() throws FileException {
        log.info("Scheduled task triggered");
//        init.Initialization();
    }
}