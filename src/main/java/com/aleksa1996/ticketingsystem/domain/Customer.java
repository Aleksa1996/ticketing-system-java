package com.aleksa1996.ticketingsystem.domain;

import java.util.UUID;

public class Customer extends User {

    public Customer() {
        super();
    }

    public Customer(UUID id, String name, String email) {
        super(id, name, email);
    }
}
