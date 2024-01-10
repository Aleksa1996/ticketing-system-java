package com.aleksa1996.ticketingsystem.infrastructure.controller;

import java.util.UUID;

public record MeResponse(
        UUID id, String name, String email) {

}
