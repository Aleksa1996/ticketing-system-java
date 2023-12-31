package com.aleksa1996.ticketingsystem.infrastructure.controller;

import java.util.Map;

public record ErrorResponse(int status, String error, String message, Map<String, String> errors) {

}
