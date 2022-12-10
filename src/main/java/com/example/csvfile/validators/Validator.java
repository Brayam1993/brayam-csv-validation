package com.example.csvfile.validators;

import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.data.Violation;

import java.util.Optional;

interface Validator {

    Optional<Violation> validate(final Line line);
    default boolean isEmpty(String value){
        return value == null || value.trim().isEmpty();
    }
    default Optional<Violation> getViolation(final String field, final String message){
        return Optional.of(new Violation(field, message));
    }
}
