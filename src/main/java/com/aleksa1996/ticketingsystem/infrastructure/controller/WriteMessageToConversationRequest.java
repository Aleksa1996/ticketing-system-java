package com.aleksa1996.ticketingsystem.infrastructure.controller;

import com.aleksa1996.ticketingsystem.infrastructure.validation.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WriteMessageToConversationRequest(
                @NotBlank @UUID String userId,
                @NotBlank @Size(min = 2, max = 500) String content) {
}
