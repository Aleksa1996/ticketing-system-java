package com.aleksa1996.ticketingsystem.infrastructure.persistence;

import com.aleksa1996.ticketingsystem.domain.Agent;
import com.aleksa1996.ticketingsystem.domain.UserRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class JpaAgentRepository implements UserRepository<Agent> {

    @Autowired
    @Lazy
    JpaAgentRepositoryInterface repository;

    public long count() {
        return repository.count();
    }

    public Agent save(Agent object) {
        return repository.save(object);
    }

    public Optional<Agent> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Agent> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
