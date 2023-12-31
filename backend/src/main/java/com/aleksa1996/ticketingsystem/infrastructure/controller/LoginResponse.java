package com.aleksa1996.ticketingsystem.infrastructure.controller;

public record LoginResponse(
        String access_token, long expires_in, String token_type) {

}
