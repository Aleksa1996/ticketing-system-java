package com.aleksa1996.ticketingsystem.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Conversation extends Entity {

    private String subject;

    private Customer customer;

    private Agent assignedAgent;

    private Set<ConversationStatus> statuses;

    private Set<Message> messages;

    public Conversation() {
        super();
    }

    public Conversation(UUID id, String subject, Customer customer) {

        super(id);

        setSubject(subject);
        setCustomer(customer);
        setMessages(new HashSet<Message>(0));
        setStatuses(new HashSet<ConversationStatus>(0));
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

    public Set<ConversationStatus> getStatuses() {
        return statuses;
    }

    private void setStatuses(Set<ConversationStatus> statuses) {
        this.statuses = statuses;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    private void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public void writeMessage(String user, String content) {
        messages.add(new Message(user, content));
    }

    public static Conversation open(String subject, Customer customer, String message) {
        Conversation conversation = new Conversation(UUID.randomUUID(), subject, customer);

        conversation.statuses
                .add(new ConversationStatus(ConversationStatusState.OPENED, "Conversation has been opened"));
        conversation.writeMessage(customer.getName(), message);

        return conversation;
    }

    public void close() {

        statuses
                .add(new ConversationStatus(ConversationStatusState.CLOSED, "Conversation has been closed"));
    }
}
