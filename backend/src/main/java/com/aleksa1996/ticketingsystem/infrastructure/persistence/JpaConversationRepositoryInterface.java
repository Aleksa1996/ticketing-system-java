package com.aleksa1996.ticketingsystem.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aleksa1996.ticketingsystem.domain.Conversation;

public interface JpaConversationRepositoryInterface extends JpaRepository<Conversation, UUID> {

    @Query("SELECT c FROM Conversation c WHERE c.assignedAgent.id = :userId OR c.customer.id = :userId")
    Page<Conversation> findByAssignedAgentIdOrCustomerId(@Param("userId") UUID userId, Pageable pageable);
}
