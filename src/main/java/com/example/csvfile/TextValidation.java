package com.example.csvfile;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class TextValidation {
    private boolean valid;
    private Text line;
    private List<Validation> errors;

    public TextValidation(final Text line, final List<Validation> validations) {
        this.line = line;
        this.errors = validations;
        this.valid = validations.size()==0;
    }
}
