package com.aleksa1996.ticketingsystem.domain;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

public class ConversationHasNewMessage extends ApplicationEvent {

    private UUID id;

    public ConversationHasNewMessage(Object source, UUID id) {
        super(source);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
