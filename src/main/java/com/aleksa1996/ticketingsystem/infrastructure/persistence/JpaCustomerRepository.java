package com.aleksa1996.ticketingsystem.infrastructure.persistence;

import com.aleksa1996.ticketingsystem.domain.Customer;
import com.aleksa1996.ticketingsystem.domain.UserRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class JpaCustomerRepository implements UserRepository<Customer> {

    @Autowired
    @Lazy
    JpaCustomerRepositoryInterface repository;

    public long count() {
        return repository.count();
    }

    public Customer save(Customer object) {
        return repository.save(object);
    }

    public Optional<Customer> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
