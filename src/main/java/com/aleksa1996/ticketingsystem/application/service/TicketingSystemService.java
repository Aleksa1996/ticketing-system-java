package com.aleksa1996.ticketingsystem.application.service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksa1996.ticketingsystem.application.dto.AgentDto;
import com.aleksa1996.ticketingsystem.application.dto.AgentDtoMapper;
import com.aleksa1996.ticketingsystem.application.dto.ConversationDto;
import com.aleksa1996.ticketingsystem.application.dto.ConversationDtoMapper;
import com.aleksa1996.ticketingsystem.application.dto.ConversationMessageDto;
import com.aleksa1996.ticketingsystem.application.dto.ConversationMessageDtoMapper;
import com.aleksa1996.ticketingsystem.application.exception.AgentAlreadyExists;
import com.aleksa1996.ticketingsystem.application.exception.ConversationNotFound;
import com.aleksa1996.ticketingsystem.domain.Agent;
import com.aleksa1996.ticketingsystem.domain.Conversation;
import com.aleksa1996.ticketingsystem.domain.ConversationRepository;
import com.aleksa1996.ticketingsystem.domain.Customer;
import com.aleksa1996.ticketingsystem.domain.UserDoesNotBelongToConversation;
import com.aleksa1996.ticketingsystem.domain.UserRepository;

@Service
public class TicketingSystemService {

    @Autowired
    private UserRepository<Customer> customerRepository;

    @Autowired
    private UserRepository<Agent> agentRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private AgentDtoMapper agentDtoMapper;

    @Autowired
    private ConversationDtoMapper conversationDtoMapper;

    @Autowired
    private ConversationMessageDtoMapper conversationMessageDtoMapper;

    public AgentDto createAgent(String name, String email, String password) {
        Optional<Agent> agent = agentRepository.findByEmail(email);

        if (agent.isPresent()) {
            throw new AgentAlreadyExists("Agent with email: [%s] already exists.".formatted(email));
        }

        Agent newAgent = new Agent(UUID.randomUUID(), name, email, password);
        agentRepository.save(newAgent);

        return agentDtoMapper.item(newAgent);
    }

    public ConversationDto openNewConversation(String name, String email, String subject,
            String message) {

        Optional<Customer> existingCustomer = customerRepository.findByEmail(email);

        Customer customer = existingCustomer.isPresent() ? existingCustomer.get()
                : customerRepository.save(new Customer(UUID.randomUUID(), name, email));

        Conversation conversation = Conversation.open(subject, customer, message);

        conversationRepository.save(conversation);

        return conversationDtoMapper.item(conversation);
    }

    public ConversationDto conversations(String name, String email, String subject, String message) {

        Optional<Customer> existingCustomer = customerRepository.findByEmail(email);

        Customer customer = existingCustomer.isPresent() ? existingCustomer.get()
                : customerRepository.save(new Customer(UUID.randomUUID(), name, email));

        Conversation conversation = Conversation.open(subject, customer, message);

        conversationRepository.save(conversation);

        return conversationDtoMapper.item(conversation);
    }

    public ConversationDto conversation(UUID id) {

        Conversation conversation = conversationRepository.findById(id).orElseThrow(
                () -> new ConversationNotFound("Conversation not found with id: [%s]".formatted(id.toString())));

        return conversationDtoMapper.item(conversation);
    }

    public Set<ConversationDto> conversations(int size, int page) {

        return conversationDtoMapper.collection(conversationRepository.query(size, page));
    }

    public ConversationDto assignAgentToConversation(UUID id, UUID agentId) {

        Conversation conversation = conversationRepository.findById(id).orElseThrow(
                () -> new ConversationNotFound("Conversation not found with id: [%s]".formatted(id.toString())));

        Agent agent = agentRepository.findById(agentId).orElseThrow(
                () -> new ConversationNotFound("Agent not found with id: [%s]".formatted(agentId.toString())));

        conversation.assignAgent(agent);

        conversationRepository.save(conversation);

        return conversationDtoMapper.item(conversation);
    }

    public void writeMessage(UUID id, UUID userId, String content) {

        Conversation conversation = conversationRepository.findById(id).orElseThrow(
                () -> new ConversationNotFound("Conversation not found with id: [%s]".formatted(id.toString())));

        try {
            conversation.writeMessage(userId, content);
        } catch (UserDoesNotBelongToConversation ex) {
            throw new com.aleksa1996.ticketingsystem.application.exception.UserDoesNotBelongToConversation(
                    "User does not belong to conversation");
        }

        conversationRepository.save(conversation);
    }

    public Set<ConversationMessageDto> conversationMessages(UUID id, int size, int page) {

        Conversation conversation = conversationRepository.findById(id).orElseThrow(
                () -> new ConversationNotFound("Conversation not found with id: [%s]".formatted(id.toString())));

        return conversationMessageDtoMapper.collection(
                conversation
                        .getMessages()
                        .stream()
                        .skip(((page - 1) < 0 ? 0 : (page - 1)) * size)
                        .limit(size)
                        .collect(Collectors.toCollection( LinkedHashSet::new )));
    }

    public void closeConversation(UUID id) {

        Conversation conversation = conversationRepository.findById(id).orElseThrow(
                () -> new ConversationNotFound("Conversation not found with id: [%s]".formatted(id.toString())));

        conversation.close();

        conversationRepository.save(conversation);
    }
}