package com.example.csvfile;

import java.util.Optional;

public interface Validator {
    Optional<LineValidation> validate(final Line line);
}
