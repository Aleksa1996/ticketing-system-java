package com.aleksa1996.ticketingsystem.infrastructure.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aleksa1996.ticketingsystem.infrastructure.auth.JwtUtil;
import com.aleksa1996.ticketingsystem.infrastructure.auth.UserDetails;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", userDetails.getId());
        claims.put("name", userDetails.getName());
        claims.put("scopes", userDetails.getRoles());

        String jwtToken = jwtUtil.issueToken(userDetails.getUsername(), claims, 30);

        return ResponseEntity.ok(new LoginResponse(jwtToken, 30 * 60, "Bearer"));
    }

    @GetMapping("/me")
    public ResponseEntity<MeResponse> me() throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity
                .ok(new MeResponse(userDetails.getId(), userDetails.getName(), userDetails.getUsername()));
    }
}
