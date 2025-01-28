package com.example.demo.utils;

import com.example.demo.convert.ConvertToDTO;
import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dto.EventDTO;
import com.example.demo.dto.PlaceDTO;
import com.example.demo.dto.TicketDTO;
import com.example.demo.exception.FileException;
import com.example.demo.model.TicketStatus;
import com.example.demo.service.event_service.EventServiceImpl;
import com.example.demo.service.place_service.PlaceServiceImpl;
import com.example.demo.service.ticket_service.TicketServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Log4j2
@Service
public class TicketOfficeDbInit {
    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private PlaceServiceImpl placeService;

    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private TicketServiceImpl ticketServiceImpl;
    @Autowired
    private PlaceServiceImpl placeServiceImpl;

    public static LocalDate generateRandomDate() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plus(1, ChronoUnit.YEARS);

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        long randomDays = ThreadLocalRandom.current().nextLong(0, daysBetween + 1);
        return startDate.plusDays(randomDays);
    }

    public void createEvent() throws FileException {
        String eventsFileName = PropertyFactory.getInstance().getProperty().getProperty("data.events");
        List<PlaceDTO> places = placeServiceImpl.findAll();
        PlaceDTO randomPlace = this.getRandomPlace(places);

        try (Stream<String> lineStream = Files.lines(Paths.get(eventsFileName))) {

            List<String> events = lineStream.collect(Collectors.toList());
            int randomEventIndex = ThreadLocalRandom.current().nextInt(events.size());

            EventDTO eventDTO = new EventDTO();
            eventDTO.setEvent_date(generateRandomDate());
            eventDTO.setPlace(randomPlace);
            eventDTO.setName(events.get(randomEventIndex));

            int eventId = eventService.save(eventDTO);
            this.createTicket(eventId);

            log.debug("Random place and tickets are created");
        } catch (IOException exception) {
            log.error("Error open source courses file");
            throw new FileException("Error with source courses file");
        }



    }

    public PlaceDTO getRandomPlace(List<PlaceDTO> places) {
        if (places == null || places.isEmpty()) {
            throw new IllegalArgumentException("The list is empty or null");
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(places.size());
        return places.get(randomIndex);
    }

    public void createTicket(int eventId){
        List<Integer> prices = new ArrayList<>();
        prices.add(50);
        prices.add(99);
        prices.add(120);
        prices.add(60);
        prices.add(35);
        int randomPrice = ThreadLocalRandom.current().nextInt(prices.size());

        int randomTicketCount = ThreadLocalRandom.current().nextInt(50, 100);
        long twoPartOfCount = randomTicketCount / 2;
        twoPartOfCount = Math.round(twoPartOfCount);

        Optional<EventDTO> eventDTOOptional = eventService.findById(eventId);

        int number = 1;
        for (int i = 0; i < randomTicketCount; i++) {

            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setStatus(TicketStatus.FREE);
            ticketDTO.setNumber(number);
            if(i <= twoPartOfCount){
                ticketDTO.setCost(prices.get(randomPrice));
            }
            else if(i <= twoPartOfCount * 2){
                ticketDTO.setCost(prices.get(randomPrice) * 2);
            }
            ticketDTO.setEvent(eventDTOOptional.get());
            ticketDTO.setCustomer(null);
            ticketServiceImpl.save(ticketDTO);
            number++;
        }

    }

    public void createPlace() throws FileException {
        PlaceDTO placeDTO = new PlaceDTO();
        String placesFileName = PropertyFactory.getInstance().getProperty().getProperty("data.places");
        int randomDigit = ThreadLocalRandom.current().nextInt(1, 30);
        try (Stream<String> lineStream = Files.lines(Paths.get(placesFileName))) {
            List<String> places = lineStream.collect(Collectors.toList());
            int ramdomPlaceIndex = ThreadLocalRandom.current().nextInt(places.size());

            placeDTO.setName(places.get(ramdomPlaceIndex));
            placeDTO.setAddress("Address " + randomDigit);

            placeService.save(placeDTO);
            log.debug("Random place are created");
        } catch (IOException exception) {
            log.error("Error open source courses file");
            throw new FileException("Error with source courses file");
        }


    }

    public void deleteAll()
    {
        placeServiceImpl.deleteAll();
        eventService.deleteAll();
        ticketServiceImpl.deleteAll();
    }

}
