package com.aleksa1996.ticketingsystem.domain;

public interface UserRepository {

    public long count();

    public User save(User user);
}
