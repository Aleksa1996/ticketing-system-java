package com.aleksa1996.ticketingsystem.domain;

import java.util.Set;
import java.util.UUID;

public interface ConversationRepository extends Repository<Conversation> {

    public Set<Conversation> queryByUserId(UUID userId, int size, int page);
}