package com.aleksa1996.ticketingsystem.application.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AgentAlreadyExists extends RuntimeException {
    public AgentAlreadyExists(String message) {
        super(message);
    }
}
