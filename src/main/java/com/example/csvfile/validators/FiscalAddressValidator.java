package com.example.csvfile.validators;

import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.data.Violation;

import java.util.Optional;
import java.util.regex.Pattern;

public class FiscalAddressValidator implements Validator {

    private static final Pattern pattern = Pattern.compile("^[a-z0-9- ]+$", Pattern.CASE_INSENSITIVE);

    private Optional<Violation> getViolation(final String message) {
        return getViolation("fiscal address", message);
    }

    @Override
    public Optional<Violation> validate(Line line) {

        // Get the fiscal address
        final String value = line.fiscalAddress();

        // If there is no value it's ok
        if (value == null || value.trim().isEmpty()) {
            return Optional.empty();
        }

        if ( ! matchPattern(value)) {
            return getViolation("The fiscal address must contain only letters, numbers and spaces.");
        }

        if (invalidLength(value)) {
            return getViolation("The fiscal address must be at least 5 characters long and no longer than 250.");
        }

        return Optional.empty();
    }

    /**
     * The fiscal address must have at least 5 characters and no more than 250.
     *
     * @param value {@link String} The value under test
     * @return True if invalid false otherwise
     */
    private boolean invalidLength(final String value) {
        return value.length() < 5 || value.length() > 250;
    }

    /**
     * Indicates fi the fiscal address contains only allowed characters
     *
     * @param value {@link String} The value under test
     * @return True if matches the pattern false otherwise
     */
    private boolean matchPattern(final String value) {
        return pattern.matcher(value).matches();
    }
}
