package com.aleksa1996.ticketingsystem.domain;

public abstract class Entity {

    protected Id id;

    public Entity(Id id) {
        setId(id);
    }

    public Id getId() {
        return id;
    }

    protected void setId(Id id) {
        this.id = id;
    }
}
