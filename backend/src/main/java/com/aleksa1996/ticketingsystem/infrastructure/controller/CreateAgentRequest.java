package com.aleksa1996.ticketingsystem.infrastructure.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAgentRequest(@NotBlank @Size(min = 2, max = 255) String name,
        @NotBlank @Email @Size(max = 255) String email, @NotBlank @Size(min = 5, max = 20) String password) {

}
