package com.example.demo.dao.event;

import com.example.demo.model.Customer;
import com.example.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Integer> {
}
