package com.aleksa1996.ticketingsystem.infrastructure.persistence;

import com.aleksa1996.ticketingsystem.domain.User;
import com.aleksa1996.ticketingsystem.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepository implements UserRepository {

    @Autowired
    @Lazy
    JpaUserRepositoryInterface repository;

    public long count() {
        return repository.count();
    }

    public User save(User user) {
        return repository.save(user);
    }
}
