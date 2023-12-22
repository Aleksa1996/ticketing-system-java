package com.aleksa1996.ticketingsystem.domain;

public class ConversationStatus implements Comparable<ConversationStatus> {

    private int id;

    private ConversationStatusState state;

    private String description;

    public ConversationStatus() {
    }

    public ConversationStatus(ConversationStatusState state, String description) {
        setState(state);
        setDescription(description);
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

    @Override
    public int compareTo(ConversationStatus status) {
        return Integer.compare(id, status.getId());
    }
}
