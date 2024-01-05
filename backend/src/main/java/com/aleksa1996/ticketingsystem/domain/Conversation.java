package com.aleksa1996.ticketingsystem.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Conversation extends Entity {

    private String subject;

    private Customer customer;

    private Agent assignedAgent;

    private Date createdOn;

    private Set<ConversationStatus> statuses;

    private Set<Message> messages;

    public Conversation() {
        super();
    }

    public Conversation(UUID id, String subject, Customer customer) {

        super(id);

        setSubject(subject);
        setCustomer(customer);
        setCreatedOn(new Date());
        setMessages(new LinkedHashSet<Message>(0));
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

    public void assignAgent(Agent agent) {
        setAssignedAgent(agent);
    }

    public String getDefaultAgent() {
        return "support@ticketing-system.com";
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    private void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Set<ConversationStatus> getStatuses() {
        return statuses;
    }

    private void setStatuses(Set<ConversationStatus> statuses) {
        this.statuses = statuses;
    }

    public ConversationStatus getCurrentStatus() {
        return statuses.stream().sorted((a, b) -> b.getOccurredOn().compareTo(a.getOccurredOn())).findFirst().get();
    }

    public Set<Message> getMessages() {
        return messages;
    }

    private void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Message getLastMessage() {
        return messages.stream().findFirst().get();
    }

    public void writeMessage(UUID userId, String content) {

        if (customer.getId().equals(userId)) {
            messages.add(new Message(customer.getId(), customer.getName(), content));
            return;
        }

        if (assignedAgent != null && assignedAgent.getId().equals(userId)) {
            messages.add(new Message(assignedAgent.getId(), assignedAgent.getName(), content));
            return;
        }

        throw new UserDoesNotBelongToConversation();
    }

    public static Conversation open(String subject, Customer customer, String message) {
        Conversation conversation = new Conversation(UUID.randomUUID(), subject, customer);

        conversation.statuses
                .add(new ConversationStatus(ConversationStatusState.OPENED, "Conversation has been opened"));
        conversation.writeMessage(customer.getId(), message);

        return conversation;
    }

    public void close() {

        statuses
                .add(new ConversationStatus(ConversationStatusState.CLOSED, "Conversation has been closed"));
    }
}
