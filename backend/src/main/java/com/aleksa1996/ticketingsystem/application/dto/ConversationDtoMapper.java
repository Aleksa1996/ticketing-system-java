package com.aleksa1996.ticketingsystem.application.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleksa1996.ticketingsystem.domain.Conversation;

@Service
public class ConversationDtoMapper implements DtoMapper<Conversation, ConversationDto> {

    @Autowired
    private AgentDtoMapper agentDtoMapper;

    @Autowired
    private CustomerDtoMapper customerDtoMapper;

    @Autowired
    private ConversationMessageDtoMapper conversationMessageDtoMapper;

    @Override
    public ConversationDto item(Conversation item) {

        Set<ConversationStatusDto> statuses = new HashSet<ConversationStatusDto>();

        item.getStatuses().forEach((status) -> {
            statuses.add(new ConversationStatusDto(status.getDescription(), status.getState().name(),
                    status.getOccurredOn()));
        });

        return new ConversationDto(
                item.getId(),
                item.getSubject(),
                customerDtoMapper.item(item.getCustomer()),
                (item.getAssignedAgent() == null) ? null : agentDtoMapper.item(item.getAssignedAgent()),
                item.getCreatedOn(),
                (item.getCurrentStatus() == null) ? null
                        : new ConversationStatusDto(item.getCurrentStatus().getDescription(),
                                item.getCurrentStatus().getState().name(), item.getCurrentStatus().getOccurredOn()),
                (item.getLastMessage() == null) ? null : conversationMessageDtoMapper.item(item.getLastMessage()),
                statuses);
    }

    @Override
    public Set<ConversationDto> collection(Set<Conversation> collection) {
        Set<ConversationDto> conversations = new HashSet<ConversationDto>();

        collection.forEach(conversation -> conversations.add(item(conversation)));

        return conversations;
    }
}
