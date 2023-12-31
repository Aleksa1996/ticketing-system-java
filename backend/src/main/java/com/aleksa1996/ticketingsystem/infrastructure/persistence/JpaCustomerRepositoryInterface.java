package com.aleksa1996.ticketingsystem.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aleksa1996.ticketingsystem.domain.Customer;

public interface JpaCustomerRepositoryInterface extends JpaRepository<Customer, UUID> {

    public Optional<Customer> findByEmail(String email);

}
