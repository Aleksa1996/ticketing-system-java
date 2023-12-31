package com.aleksa1996.ticketingsystem.domain;

import java.util.Optional;

public interface UserRepository<T> extends Repository<T> {
    public Optional<T> findByEmail(String email);
}
