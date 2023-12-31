package com.aleksa1996.ticketingsystem.infrastructure.validation;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Service
public class UUIDValidator implements ConstraintValidator<UUID, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        try {
            java.util.UUID.fromString(value);
        } catch (IllegalArgumentException ex) {
            return false;
        }

        return true;
    }
}
