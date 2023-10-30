package com.aleksa1996.ticketingsystem.domain;

import java.util.UUID;

public class Agent extends User {

    public Agent() {
        super();
    }

    public Agent(UUID id, String name, String email) {
        super(id, name, email);
    }
}
