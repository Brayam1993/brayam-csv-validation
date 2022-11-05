package com.example.csvfile;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class TextValidation {
    private boolean valid;
    private Line line;
    private List<LineValidation> errors;

    public TextValidation(final Line line, final List<LineValidation> LineValidations) {
        this.line = line;
        this.errors = LineValidations;
        this.valid = LineValidations.size()==0;
    }
}
