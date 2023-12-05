package com.aleksa1996.ticketingsystem.infrastructure.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAgentRequest(@NotBlank @Size(min = 2, max = 200) String name, @NotBlank @Email String email) {

}
