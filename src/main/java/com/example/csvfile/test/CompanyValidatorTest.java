package com.example.csvfile.test;

import com.example.csvfile.validators.CompanyValidator;
import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.data.Violation;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class CompanyValidatorTest {

    private static Line getLine(String company) {
        return new Line(1,null,company,null,null,null,null,null,null);
    }

    @Test
    void validate_whenNullCompany_receiveViolation() {

        Line line = Line.toLine(1, "");

        Optional<Violation> violation = new CompanyValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The company name is required.");
    }

    @Test
    void validate_whenSpacesCompany_receiveViolation() {

        Line line = getLine("      ");

        Optional<Violation> violation = new CompanyValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The company name is required.");
    }

    @Test
    void validate_whenCompanyLengthGreaterThan50_receiveViolation() {
        String company51 = IntStream.range(0, 51).mapToObj(i -> "a").collect(Collectors.joining());
        Line line = getLine(company51);

        Optional<Violation> violation = new CompanyValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The company name must be at least 5 characters long and no longer than 50.");

    }

    @Test
    void validate_whenInvalidValueCompany_receiveViolation() {

        Line line = getLine("+?_.");

        Optional<Violation> violation = new CompanyValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The company name must contain only letters, numbers and spaces.");
    }

    @Test
    void validate_whenValidInput_receiveEmptyOptional() {
        Line line = getLine("Coca Cola");

        Optional<Violation> violation = new CompanyValidator().validate(line);

        assertThat(violation).isEmpty();
    }
}
