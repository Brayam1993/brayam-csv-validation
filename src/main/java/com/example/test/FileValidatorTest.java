package com.example.test;

import com.example.csvfile.validators.FileValidator;
import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.data.ValidationResult;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class FileValidatorTest {

    @Test
    void fileValidator_whenInvalidFilePath_throwsIOException(){
        assertThrows(IOException.class, () -> new FileValidator("unknown_file.csv"));
    }

    @Test
    void getValidRecords() throws IOException {
        FileValidator validator = new FileValidator("src/main/resources/companysErrors.csv");

        List<Line> validRecords = validator.getValidRecords();

        assertThat(validRecords).hasSize(1);
    }

    @Test
    void getInvalidRecords() throws IOException {
        FileValidator validator = new FileValidator("src/main/resources/companysErrors.csv");

        List<ValidationResult> invalidRecords = validator.getInvalidRecords();

        assertThat(invalidRecords).hasSize(1);
        assertThat(invalidRecords.get(0).line().number()).isEqualTo(2);
    }

    @Test
    void getInvalidRecords_duplicatedEmails() throws IOException {
        FileValidator validator = new FileValidator("src/main/resources/emails_duplicates.csv");

        List<ValidationResult> invalidRecords = validator.getInvalidRecords();

        assertThat(invalidRecords).hasSize(1);
        assertThat(invalidRecords.get(0).line().number()).isEqualTo(3);
    }

    @Test
    void getInvalidRecords_duplicatedRfcs() throws IOException {
        FileValidator validator = new FileValidator("src/main/resources/rfcs_duplicates.csv");

        List<ValidationResult> invalidRecords = validator.getInvalidRecords();

        assertThat(invalidRecords).hasSize(1);
        assertThat(invalidRecords.get(0).line().number()).isEqualTo(3);
    }
}