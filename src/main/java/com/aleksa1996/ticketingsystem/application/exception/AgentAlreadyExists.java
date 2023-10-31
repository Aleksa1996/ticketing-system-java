package com.aleksa1996.ticketingsystem.application.exception;

public class AgentAlreadyExists extends RuntimeException {
    public AgentAlreadyExists(String message) {
        super(message);
    }
}
