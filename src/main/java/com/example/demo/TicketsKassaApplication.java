package com.example.demo;

import com.example.demo.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TicketsKassaApplication {

	@Autowired
	private Menu menu;

	public static void main(String[] args) {
		SpringApplication.run(TicketsKassaApplication.class, args);

	}

	@EventListener(ApplicationReadyEvent.class)
	public void start() {
		menu.start();
	}
}
