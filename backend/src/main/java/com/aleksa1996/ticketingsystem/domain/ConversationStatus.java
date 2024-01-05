package com.aleksa1996.ticketingsystem.domain;

import java.util.Date;

public class ConversationStatus implements Comparable<ConversationStatus> {

    private int id;

    private ConversationStatusState state;

    private String description;

    private Date occurredOn;

    public ConversationStatus() {
    }

    public ConversationStatus(ConversationStatusState state, String description) {
        setState(state);
        setDescription(description);
        setOccurredOn(new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ConversationStatusState getState() {
        return state;
    }

    private void setState(ConversationStatusState state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public Date getOccurredOn() {
        return occurredOn;
    }

    private void setOccurredOn(Date occurredOn) {
        this.occurredOn = occurredOn;
    }

    @Override
    public int compareTo(ConversationStatus status) {
        return Integer.compare(id, status.getId());
    }
}
