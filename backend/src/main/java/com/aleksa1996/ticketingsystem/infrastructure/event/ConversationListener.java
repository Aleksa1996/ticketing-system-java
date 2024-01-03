package com.aleksa1996.ticketingsystem.infrastructure.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.aleksa1996.ticketingsystem.infrastructure.websocket.ConversationHasNewMessage;

@Component
public class ConversationListener {

    @Autowired
    private SimpMessagingTemplate websocket;

    @Async
    @EventListener
    @TransactionalEventListener
    public void handleUserRegistrationEvent(com.aleksa1996.ticketingsystem.domain.ConversationHasNewMessage event) {
        websocket.convertAndSend("/topic/conversations/" + event.getId().toString() + "/messages",
                new ConversationHasNewMessage(event.getId()));
    }
}
