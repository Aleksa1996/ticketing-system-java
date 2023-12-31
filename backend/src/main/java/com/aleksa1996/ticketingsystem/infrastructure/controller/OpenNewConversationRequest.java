package com.aleksa1996.ticketingsystem.infrastructure.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OpenNewConversationRequest(
                @NotBlank @Size(min = 2, max = 255) String name,
                @NotBlank @Email @Size(max = 255) String email,
                @NotBlank @Size(min = 2, max = 50) String subject,
                @NotBlank @Size(min = 2, max = 500) String message) {

}
