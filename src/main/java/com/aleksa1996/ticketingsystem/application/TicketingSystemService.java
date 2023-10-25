package com.aleksa1996.ticketingsystem.application;

import com.aleksa1996.ticketingsystem.domain.Conversation;

public class TicketingSystemService {

    public void startNewConversation(String name, String email, String subject, String message) {

        // find user by email
        // if exists fetch it
        // if does not exists, create new
        // start new conversation
        // add first message
        Conversation newConversation = new Conversation(null, null, null);
    }
}
