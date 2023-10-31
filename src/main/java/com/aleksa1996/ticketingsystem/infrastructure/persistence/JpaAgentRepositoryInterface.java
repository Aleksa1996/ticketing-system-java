package com.aleksa1996.ticketingsystem.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aleksa1996.ticketingsystem.domain.Agent;

public interface JpaAgentRepositoryInterface extends JpaRepository<Agent, UUID> {

    public Optional<Agent> findByEmail(String email);

}
