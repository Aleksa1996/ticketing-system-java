package com.aleksa1996.ticketingsystem.application.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import com.aleksa1996.ticketingsystem.domain.Message;

@Service
public class ConversationMessageDtoMapper implements DtoMapper<Message, ConversationMessageDto> {

    @Override
    public ConversationMessageDto item(Message item) {

        return new ConversationMessageDto(
                item.getId(),
                item.getUser(),
                item.getContent(),
                item.getWroteOn());
    }

    @Override
    public Set<ConversationMessageDto> collection(Set<Message> collection) {
        Set<ConversationMessageDto> conversationMessages = new LinkedHashSet<ConversationMessageDto>();

        collection.forEach(conversationMessage -> conversationMessages.add(item(conversationMessage)));

        return conversationMessages;
    }
}
