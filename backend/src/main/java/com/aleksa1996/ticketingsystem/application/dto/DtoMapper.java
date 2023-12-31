package com.aleksa1996.ticketingsystem.application.dto;

import java.util.Set;

interface DtoMapper<From, To> {

    public To item(From item);

    public Set<To> collection(Set<From> collection);
}
