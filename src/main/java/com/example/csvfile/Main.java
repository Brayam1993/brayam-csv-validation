package com.example.csvfile;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create a new validator instance
        final FileValidator validator = new FileValidator("filename.csv");

        // Get all invalid lines with all the errors to inform the user
        final List<LineValidation> validations = validator.getErrors();

        // Get all valid lines to store into a database
        final List<Line> validLines = validator.getValidRecords();
    }
}
