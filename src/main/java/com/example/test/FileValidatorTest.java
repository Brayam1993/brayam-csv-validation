package com.example.test;

import com.example.csvfile.FileValidator;
import com.example.csvfile.Line;
import com.example.csvfile.LineValidation;
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
        FileValidator validator = new FileValidator("companysErrors.csv");

        List<Line> validRecords = validator.getValidRecords();

        assertThat(validRecords).hasSize(1);
    }

    @Test
    void getInvalidRecords() throws IOException {
        FileValidator validator = new FileValidator("companysErrors.csv");

        List<LineValidation> invalidRecords  = validator.getErrors();

        assertThat(invalidRecords).hasSize(1);
        //*
        assertThat(invalidRecords.get(0).getNumberLine().intValue()).isEqualTo(2);
    }
}