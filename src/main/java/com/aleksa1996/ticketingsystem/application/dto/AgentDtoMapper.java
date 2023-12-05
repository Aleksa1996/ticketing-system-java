package com.aleksa1996.ticketingsystem.application.dto;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import com.aleksa1996.ticketingsystem.domain.Agent;

@Service
public class AgentDtoMapper implements DtoMapper<Agent, AgentDto> {

    @Override
    public AgentDto item(Agent item) {
        return new AgentDto(item.getId(), item.getName(), item.getEmail());
    }

    @Override
    public AgentDto collection(Agent collection) {
        throw new NotImplementedException();
    }
}
