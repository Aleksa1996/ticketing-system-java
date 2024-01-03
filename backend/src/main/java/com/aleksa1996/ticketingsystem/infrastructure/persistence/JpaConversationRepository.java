package com.aleksa1996.ticketingsystem.infrastructure.persistence;

import com.aleksa1996.ticketingsystem.domain.Conversation;
import com.aleksa1996.ticketingsystem.domain.ConversationRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class JpaConversationRepository implements ConversationRepository {

    @Autowired
    @Lazy
    JpaConversationRepositoryInterface repository;

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Conversation save(Conversation object) {
        return repository.save(object);
    }

    @Override
    public Optional<Conversation> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Set<Conversation> query(int size, int page) {
        // Page starts from zero, wtf o.O
        return repository.findAll(PageRequest.of(page < 0 ? 0 : (page == 0 ? page : page - 1), size)).toSet();
    }

    @Override
    public Set<Conversation> queryByUserId(UUID userId, int size, int page) {
        return repository
                .findByAssignedAgentIdOrCustomerId(userId,
                        PageRequest.of(page < 0 ? 0 : (page == 0 ? page : page - 1), size))
                .toSet();
    }
}
