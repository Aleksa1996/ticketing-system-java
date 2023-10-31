package com.aleksa1996.ticketingsystem.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aleksa1996.ticketingsystem.application.TicketingSystemService;

@RestController
public class TicketingSystemController {

    @Autowired
    private TicketingSystemService ticketingSystemService;

    @GetMapping("/create-agent")
    public void createAgent() {

        ticketingSystemService.createAgent("Aleksa", "aleksa.j.1996@gmail.com");
        ticketingSystemService.openNewConversation("Aleksa", "aleksa.j.1996@gmail.com", "Pera", "Peric");
    }

    @GetMapping("/register-customer")
    public void registerCustomer() {

        ticketingSystemService.openNewConversation("", "", "", "");
    }

    @GetMapping("/open-new-conversation")
    public void openNewConversation() {

        ticketingSystemService.openNewConversation("", "", "", "");
    }

    @GetMapping("/close-conversation")
    public void closeConversation() {

        ticketingSystemService.openNewConversation("", "", "", "");
    }

    @MessageMapping("/conversations/{id}/send")
    @SendTo("/conversations/{id}/topic")
    public void sendMessageToConversation() {

        ticketingSystemService.openNewConversation("", "", "", "");
    }
}
