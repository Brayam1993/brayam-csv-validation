package com.example.csvfile;

import com.example.csvfile.validators.FileValidator;
import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.LineValidation;
import com.example.csvfile.validators.data.ValidationResult;

import java.io.IOException;
import java.util.List;

public class Main {

    private static void printResults(List<Line> validRecords, List<ValidationResult> invalidRecords) {
        System.out.println("ValidRecords: ");
        validRecords.forEach(record -> System.out.println("\t" + record));
        System.out.println();

        System.out.println("InvalidRecords: ");
        invalidRecords.forEach(record -> {
            System.out.println("\t Line data: " + record.line());
            record.violations().forEach(violation -> {
                System.out.println("\t\t Violation: " + violation);
            });
            System.out.println();
        });
    }

    public static void main(String[] args) throws IOException {

        // Create a new validator to handle the details
        final FileValidator validator = new FileValidator("src/main/resources/filename.csv");

        // Extract correct records
        final List<Line> validRecords = validator.getValidRecords();

        // Extract invalid records with all errors and line number
        final List<ValidationResult> invalidRecords = validator.getInvalidRecords();

        // Print the results to the console
        printResults(validRecords,invalidRecords);

    }
}
