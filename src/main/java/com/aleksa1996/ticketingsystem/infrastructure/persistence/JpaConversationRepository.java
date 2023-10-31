package com.aleksa1996.ticketingsystem.infrastructure.persistence;

import com.aleksa1996.ticketingsystem.domain.Conversation;
import com.aleksa1996.ticketingsystem.domain.ConversationRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class JpaConversationRepository implements ConversationRepository {

    @Autowired
    @Lazy
    JpaConversationRepositoryInterface repository;

    public long count() {
        return repository.count();
    }

    public Conversation save(Conversation object) {
        return repository.save(object);
    }

    public Optional<Conversation> findById(UUID id) {
        return repository.findById(id);
    }
}
