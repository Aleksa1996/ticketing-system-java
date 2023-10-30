package com.aleksa1996.ticketingsystem.application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksa1996.ticketingsystem.domain.Agent;
import com.aleksa1996.ticketingsystem.domain.UserRepository;

@Service
public class TicketingSystemService {

    @Autowired
    private UserRepository userRepository;

    public void startNewConversation(String name, String email, String subject, String message) {

        System.out.print(userRepository.count());

        userRepository.save(new Agent(UUID.randomUUID(), "Pera Peric", "pera@gmail.com"));

        System.out.print(userRepository.count());
        // find user by email
        // if exists fetch it
        // if does not exists, create new
        // start new conversation
        // add first message
        // Conversation newConversation = new Conversation(null, null, null);
    }
}
