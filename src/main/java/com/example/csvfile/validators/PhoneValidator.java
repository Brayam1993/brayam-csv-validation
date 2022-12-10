package com.example.csvfile.validators;

import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.data.Violation;

import java.util.Optional;
import java.util.regex.Pattern;

public class PhoneValidator implements Validator {

    private static final Pattern pattern = Pattern.compile("^[0-9]{9}$");

    private Optional<Violation> getViolation(final String message) {
        return getViolation("phone", message);
    }

    @Override
    public Optional<Violation> validate(Line line) {

        final String value = line.phone();

        // The phone is not required so we just return empty
        if (isEmpty(value)) {
            return Optional.empty();
        }

        if( ! isValid(value)) {
            return getViolation("The phone number must contains 9 numbers.");
        }

        return Optional.empty();
    }

    private boolean isValid(final String value) {
        return pattern.matcher(value).matches();
    }
}
