package com.aleksa1996.ticketingsystem.infrastructure.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aleksa1996.ticketingsystem.application.dto.AgentDto;
import com.aleksa1996.ticketingsystem.application.dto.ConversationDto;
import com.aleksa1996.ticketingsystem.application.dto.ConversationMessageDto;
import com.aleksa1996.ticketingsystem.application.service.TicketingSystemService;

import com.aleksa1996.ticketingsystem.infrastructure.validation.UUID;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1")
public class TicketingSystemController {

    @Autowired
    private TicketingSystemService ticketingSystemService;

    @PostMapping("/agents")
    public ResponseEntity<AgentDto> createAgent(@Valid @RequestBody CreateAgentRequest request) {

        AgentDto agent = ticketingSystemService.createAgent(request.name(), request.email(), request.password());

        return ResponseEntity.ok(agent);
    }

    @PostMapping("/conversations")
    public ResponseEntity<ConversationDto> openNewConversation(@Valid @RequestBody OpenNewConversationRequest request) {

        ConversationDto conversation = ticketingSystemService.openNewConversation(
                request.name(),
                request.email(),
                request.subject(),
                request.message());

        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/conversations/{id}")
    public ResponseEntity<ConversationDto> conversation(@PathVariable(required = true) @Valid @UUID String id) {

        return ResponseEntity.ok(ticketingSystemService.conversation(java.util.UUID.fromString(id)));
    }

    @GetMapping("/conversations")
    public ResponseEntity<Set<ConversationDto>> conversations(
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "userId", required = false, defaultValue = "") String userId) {

        if (userId.isEmpty()) {
            return ResponseEntity.ok(ticketingSystemService.conversations(size, page));
        }

        return ResponseEntity.ok(ticketingSystemService.conversations(size, page, java.util.UUID.fromString(userId)));
    }

    @PostMapping("/conversations/{id}/assign-agent")
    public ResponseEntity<ConversationDto> assignAgentToConversation(
            @PathVariable(required = true) @Valid @UUID String id,
            @Valid @RequestBody AssignAgentToConversationRequest request) {

        ConversationDto conversation = ticketingSystemService.assignAgentToConversation(
                java.util.UUID.fromString(id),
                java.util.UUID.fromString(request.agentId()));

        return ResponseEntity.ok(conversation);
    }

    @PostMapping("/conversations/{id}/messages")
    public ResponseEntity<Void> writeMessageToConversation(
            @PathVariable(required = true) @Valid @UUID String id,
            @Valid @RequestBody WriteMessageToConversationRequest request) {

        ticketingSystemService.writeMessage(
                java.util.UUID.fromString(id),
                java.util.UUID.fromString(request.userId()),
                request.content());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/conversations/{id}/messages")
    public ResponseEntity<Set<ConversationMessageDto>> conversationMessages(
            @PathVariable(required = true) @Valid @UUID String id,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page) {

        return ResponseEntity
                .ok(ticketingSystemService.conversationMessages(java.util.UUID.fromString(id), size, page));
    }

    @PostMapping("/conversations/{id}/close")
    public ResponseEntity<Void> closeConversation(
            @PathVariable(required = true) @Valid @UUID String id) {

        ticketingSystemService.closeConversation(java.util.UUID.fromString(id));

        return ResponseEntity.noContent().build();
    }

    @MessageMapping("/conversations/{id}/send")
    @SendTo("/conversations/{id}/topic")
    public void sendMessageToConversation() {

        // ticketingSystemService.openNewConversation("", "", "", "");
    }
}
