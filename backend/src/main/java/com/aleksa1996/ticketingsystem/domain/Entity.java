package com.aleksa1996.ticketingsystem.domain;

import java.util.UUID;

public abstract class Entity {

    protected UUID id;

    public Entity() {
    }

    public Entity(UUID id) {
        setId(id);
    }

    public UUID getId() {
        return id;
    }

    protected void setId(UUID id) {
        this.id = id;
    }
}
