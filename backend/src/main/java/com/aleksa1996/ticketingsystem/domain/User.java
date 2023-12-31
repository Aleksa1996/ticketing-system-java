package com.aleksa1996.ticketingsystem.domain;

import java.util.UUID;

public class User extends Entity {

    protected String name;

    protected String email;

    public User() {
        super();
    }

    public User(UUID id, String name, String email) {
        super(id);

        setName(name);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }
}
