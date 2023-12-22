package com.aleksa1996.ticketingsystem.application.dto;

import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import com.aleksa1996.ticketingsystem.domain.Customer;

@Service
public class CustomerDtoMapper implements DtoMapper<Customer, CustomerDto> {

    @Override
    public CustomerDto item(Customer item) {
        return new CustomerDto(item.getId(), item.getName(), item.getEmail());
    }

    @Override
    public Set<CustomerDto> collection( Set<Customer> collection) {
        throw new NotImplementedException();
    }
}
