package com.aleksa1996.ticketingsystem.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.aleksa1996.ticketingsystem.application.dto.AgentDto;
import com.aleksa1996.ticketingsystem.application.service.TicketingSystemService;

import jakarta.validation.Valid;

@RestController
public class TicketingSystemController {

    @Autowired
    private TicketingSystemService ticketingSystemService;

    @PostMapping("/create-agent")
    public ResponseEntity<AgentDto> createAgent(@Valid @RequestBody CreateAgentRequest request) {

        AgentDto agent = ticketingSystemService.createAgent(request.name(), request.email());

        return ResponseEntity.ok(agent);
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
