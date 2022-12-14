package com.example.csvfile.validators;

import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.data.Violation;

import java.util.Optional;

public class IdValidator implements Validator {

    private Optional<Violation> getViolation(final String message){
        return getViolation("id", message);
    }

    @Override
    public Optional<Violation> validate(Line line) {

        final String value = line.id();

        if ( isEmpty(value)){
            return getViolation("The id is required.");
        }

        if ( ! isValid(value)) {
            return getViolation("The id must be a number greater than 0.");
        }

        return Optional.empty();
    }

    private boolean isValid(String value){
        try{
            return Integer.parseInt(value) > 0;
        } catch (NumberFormatException ignore) {}
        return false;
    }
}
