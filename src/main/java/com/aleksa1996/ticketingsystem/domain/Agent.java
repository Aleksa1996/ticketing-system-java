package com.aleksa1996.ticketingsystem.domain;

import java.util.UUID;

public class Agent extends User {

    private String password;

    public Agent() {
        super();
    }

    public Agent(UUID id, String name, String email, String password) {
        super(id, name, email);
        setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
