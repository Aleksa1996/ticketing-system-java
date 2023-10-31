package com.aleksa1996.ticketingsystem.infrastructure.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aleksa1996.ticketingsystem.domain.Conversation;

public interface JpaConversationRepositoryInterface extends JpaRepository<Conversation, UUID> {
}
