package com.aleksa1996.ticketingsystem.domain;

import java.util.HashSet;
import java.util.Set;

public class Conversation extends Entity {

    private String subject;

    private Customer customer;

    private Agent assignedAgent;

    private ConversationStatus status;

    private Set<Message> messages;

    public Conversation(Id id, String subject, Customer customer) {

        super(id);

        setSubject(subject);
        setCustomer(customer);
        setMessages(new HashSet<Message>(0));
    }

    public String getSubject() {
        return subject;
    }

    private void setSubject(String subject) {
        this.subject = subject;
    }

    public Customer getCustomer() {
        return customer;
    }

    private void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Agent getAssignedAgent() {
        return assignedAgent;
    }

    private void setAssignedAgent(Agent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    public ConversationStatus getStatus() {
        return status;
    }

    private void setStatus(ConversationStatus status) {
        this.status = status;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    private void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public void writeMessage(Id id, User user, String content) {
        messages.add(new Message(id, user, content));
    }
}
