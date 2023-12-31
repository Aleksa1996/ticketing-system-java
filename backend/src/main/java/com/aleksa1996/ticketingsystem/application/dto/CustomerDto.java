package com.aleksa1996.ticketingsystem.application.dto;

import java.util.UUID;

public record CustomerDto(UUID id, String name, String email) {
}
