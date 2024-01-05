package com.aleksa1996.ticketingsystem.application.dto;

import java.util.Date;

public record ConversationStatusDto(String description, String state, Date occurredOn) {
}
