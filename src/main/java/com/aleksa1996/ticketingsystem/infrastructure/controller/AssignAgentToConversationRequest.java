package com.aleksa1996.ticketingsystem.infrastructure.controller;

import com.aleksa1996.ticketingsystem.infrastructure.validation.UUID;

import jakarta.validation.constraints.NotBlank;

public record AssignAgentToConversationRequest(@NotBlank @UUID String agentId) {

}
