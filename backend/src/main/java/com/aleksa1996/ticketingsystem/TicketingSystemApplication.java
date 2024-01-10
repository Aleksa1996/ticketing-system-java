package com.aleksa1996.ticketingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.aleksa1996.ticketingsystem.application.service.TicketingSystemService;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TicketingSystemApplication implements CommandLineRunner {

	@Autowired
	private TicketingSystemService ticketingSystemService;

	public static void main(String[] args) {
		SpringApplication.run(TicketingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) {

		ticketingSystemService.createAgent("Support", "support@ticketing-system.com", "support");
		ticketingSystemService.createAgent("System", "system@ticketing-system.com", "system");
		ticketingSystemService.createAgent("Aleksa Jovanovic", "aleksa.jovanovic@ticketing-system.com", "peraperic123");
	}
}
