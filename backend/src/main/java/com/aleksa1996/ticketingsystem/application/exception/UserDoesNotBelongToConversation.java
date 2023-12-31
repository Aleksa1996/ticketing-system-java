package com.aleksa1996.ticketingsystem.application.exception;

public class UserDoesNotBelongToConversation extends ApplicationException {
    public UserDoesNotBelongToConversation(String message) {
        super(message);
    }
}