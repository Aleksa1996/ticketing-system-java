package com.aleksa1996.ticketingsystem.application.dto;

interface DtoMapper<From, To> {

    public To item(From item);

    public To collection(From collection);
}
