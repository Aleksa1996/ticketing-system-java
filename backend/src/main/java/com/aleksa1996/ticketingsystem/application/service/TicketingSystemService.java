package com.aleksa1996.ticketingsystem.application.service;

import java.security.SecureRandom;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.aleksa1996.ticketingsystem.application.dto.AgentDto;
import com.aleksa1996.ticketingsystem.application.dto.AgentDtoMapper;
import com.aleksa1996.ticketingsystem.application.dto.ConversationDto;
import com.aleksa1996.ticketingsystem.application.dto.ConversationDtoMapper;
import com.aleksa1996.ticketingsystem.application.dto.ConversationMessageDto;
import com.aleksa1996.ticketingsystem.application.dto.ConversationMessageDtoMapper;
import com.aleksa1996.ticketingsystem.application.exception.AgentAlreadyExists;
import com.aleksa1996.ticketingsystem.application.exception.ConversationNotFound;
import com.aleksa1996.ticketingsystem.application.exception.UsertNotExists;
import com.aleksa1996.ticketingsystem.domain.Agent;
import com.aleksa1996.ticketingsystem.domain.Conversation;
import com.aleksa1996.ticketingsystem.domain.ConversationHasNewMessage;
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

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public AgentDto createAgent(String name, String email, String password) {
        Optional<Agent> agent = agentRepository.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12, new SecureRandom());

        if (agent.isPresent()) {
            throw new AgentAlreadyExists("Agent with email: [%s] already exists.".formatted(email));
        }

        Agent newAgent = new Agent(UUID.randomUUID(), name, email, passwordEncoder.encode(password));
        agentRepository.save(newAgent);

        return agentDtoMapper.item(newAgent);
    }

    @Transactional
    public ConversationDto openNewConversation(String name, String email, String subject,
            String message) {

        Optional<Customer> existingCustomer = customerRepository.findByEmail(email);

        Customer customer = existingCustomer.isPresent() ? existingCustomer.get()
                : customerRepository.save(new Customer(UUID.randomUUID(), name, email));

        Conversation conversation = Conversation.open(subject, customer, message);

        Agent defaultAgent = agentRepository.findByEmail(conversation.getDefaultAgent()).orElseThrow(
                () -> new UsertNotExists("User not found"));

        conversation.assignAgent(defaultAgent);

        conversationRepository.save(conversation);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("[Ticketing System] - You successfully opened new ticket - " + subject);

        String body = "Thank you for submitting ticket! \n\nDetails: \n Name: " + name + " \n Email: " + email
                + " \n Subject: " + subject + " \n Message: " + message
                + "\n\n Click on this link to chat with our support: http://frontend.local/#/conversations?conversationId="
                + conversation.getId().toString() + "&userId=" + customer.getId().toString();
        mailMessage.setText(body);
        mailSender.send(mailMessage);

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

    public Set<ConversationDto> conversations(int size, int page, UUID userId) {

        return conversationDtoMapper.collection(conversationRepository.queryByUserId(userId, size, page));
    }

    @Transactional
    public ConversationDto assignAgentToConversation(UUID id, UUID agentId) {

        Conversation conversation = conversationRepository.findById(id).orElseThrow(
                () -> new ConversationNotFound("Conversation not found with id: [%s]".formatted(id.toString())));

        Agent agent = agentRepository.findById(agentId).orElseThrow(
                () -> new ConversationNotFound("Agent not found with id: [%s]".formatted(agentId.toString())));

        conversation.assignAgent(agent);

        conversationRepository.save(conversation);

        return conversationDtoMapper.item(conversation);
    }

    @Transactional
    public void writeMessage(UUID id, UUID userId, String content) {

        Conversation conversation = conversationRepository.findById(id).orElseThrow(
                () -> new ConversationNotFound("Conversation not found with id: [%s]".formatted(id.toString())));

        try {
            conversation.writeMessage(userId, content);
            eventPublisher.publishEvent(new ConversationHasNewMessage(this, conversation.getId()));
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
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
    }

    @Transactional
    public void closeConversation(UUID id) {

        Conversation conversation = conversationRepository.findById(id).orElseThrow(
                () -> new ConversationNotFound("Conversation not found with id: [%s]".formatted(id.toString())));

        conversation.close();

        conversationRepository.save(conversation);
    }
}
