package com.aleksa1996.ticketingsystem.domain;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface Repository<T> {
    public long count();

    public T save(T object);

    public Optional<T> findById(UUID id);

    public Set<T> query(int size, int page);
}
