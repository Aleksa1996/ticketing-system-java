package com.aleksa1996.ticketingsystem.application.dto;

import java.util.Date;

public record ConversationMessageDto(int id, String user, String content, Date wroteOn) {
}
