package com.aleksa1996.ticketingsystem.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksa1996.ticketingsystem.application.exception.AgentAlreadyExists;
import com.aleksa1996.ticketingsystem.application.exception.ConversationNotFound;
import com.aleksa1996.ticketingsystem.domain.Agent;
import com.aleksa1996.ticketingsystem.domain.Conversation;
import com.aleksa1996.ticketingsystem.domain.ConversationRepository;
import com.aleksa1996.ticketingsystem.domain.Customer;
import com.aleksa1996.ticketingsystem.domain.UserRepository;

@Service
public class TicketingSystemService {

    @Autowired
    private UserRepository<Customer> customerRepository;

    @Autowired
    private UserRepository<Agent> agentRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    public void createAgent(String name, String email) {
        Optional<Agent> agent = agentRepository.findByEmail(email);

        if (agent.isPresent()) {
            throw new AgentAlreadyExists("User with email: [%s] already exists.".formatted(email));
        }

        agentRepository.save(new Agent(UUID.randomUUID(), name, email));
    }

    public void openNewConversation(String name, String email, String subject, String message) {

        Optional<Customer> existingCustomer = customerRepository.findByEmail(email);

        Customer customer = existingCustomer.isPresent() ? existingCustomer.get()
                : customerRepository.save(new Customer(UUID.randomUUID(), name, email));

        Conversation conversation = Conversation.open(subject, customer, message);

        conversationRepository.save(conversation);
    }

    public void closeConversation(UUID id) {

        Conversation conversation = conversationRepository.findById(id).orElseThrow(
                () -> new ConversationNotFound("Conversation not found with id: [%s]".formatted(id.toString())));

        conversation.close();

        conversationRepository.save(conversation);
    }
}
