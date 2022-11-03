package com.example.csvfile;

import java.util.Optional;

public interface Validator {
    Optional<Validation> validate(final Text text);
}
