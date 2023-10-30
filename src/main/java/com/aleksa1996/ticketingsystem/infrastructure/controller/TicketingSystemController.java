package com.aleksa1996.ticketingsystem.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleksa1996.ticketingsystem.application.TicketingSystemService;

@RestController
public class TicketingSystemController {

    @Autowired
    private TicketingSystemService ticketingSystemService;

    @GetMapping("/create-agent")
    public void createAgent() {

        ticketingSystemService.startNewConversation("", "", "", "");
    }
}
