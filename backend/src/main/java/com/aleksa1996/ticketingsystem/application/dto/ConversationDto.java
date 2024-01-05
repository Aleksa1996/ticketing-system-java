package com.aleksa1996.ticketingsystem.application.dto;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record ConversationDto(UUID id, String subject, CustomerDto customer, AgentDto assignedAgent,
                Date createdOn, ConversationStatusDto currentStatus, ConversationMessageDto lastMessage,
                Set<ConversationStatusDto> statuses) {
}
