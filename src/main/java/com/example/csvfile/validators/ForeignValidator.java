package com.example.csvfile.validators;

import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.data.Violation;

import java.util.Optional;
import java.util.regex.Pattern;

public class ForeignValidator implements Validator {

    private static final Pattern pattern = Pattern.compile("^(0|1|false|true|falso|verdadero)$",Pattern.CASE_INSENSITIVE);

    private Optional<Violation> getViolation(final String message) {
        return getViolation("foreing", message);
    }

    @Override
    public Optional<Violation> validate(Line line) {

        final String value = line.foreing();

        if(isEmpty(value)) {
            return getViolation("The foreing field is required.");
        }

        if( ! isValid(value)) {
            return getViolation("The foreign field can contain only: 0, false, 1 or true.");
        }

        return Optional.empty();
    }

    private boolean isValid(final String value) {
        return pattern.matcher(value).matches();
    }
}
