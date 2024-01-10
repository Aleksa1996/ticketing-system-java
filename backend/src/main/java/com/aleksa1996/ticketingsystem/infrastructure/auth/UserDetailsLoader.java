package com.aleksa1996.ticketingsystem.infrastructure.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aleksa1996.ticketingsystem.domain.Agent;
import com.aleksa1996.ticketingsystem.domain.UserRepository;

@Service
public class UserDetailsLoader implements UserDetailsService {

    @Autowired
    private UserRepository<Agent> agentRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Agent user = agentRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Username or email" + username + " not found!"));

        return new UserDetails(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
