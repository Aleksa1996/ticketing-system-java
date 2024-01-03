package com.aleksa1996.ticketingsystem.application.dto;

import java.util.Date;
import java.util.UUID;

public record ConversationMessageDto(int id, UUID userId, String user, String content, Date wroteOn) {
}
