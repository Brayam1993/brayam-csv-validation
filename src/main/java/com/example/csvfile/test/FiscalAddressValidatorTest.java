package com.example.csvfile.test;

import com.example.csvfile.validators.FiscalAddressValidator;
import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.data.Violation;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class FiscalAddressValidatorTest {

    private static Line getLine(String fiscalAddress) {
        return new Line(1,null,null,null,null,null,null,null,fiscalAddress);
    }

    @Test
    void validate_whenValidFiscalAddress_receiveEmptyOptional() {

        // The fiscal address is not required
        Line line = getLine("Huizache 35");

        Optional<Violation> violation = new FiscalAddressValidator().validate(line);

        assertThat(violation).isEmpty();
    }

    @Test
    void validate_whenEmptyFiscalAddress_receiveEmptyOptional() {

        // The fiscal address is not required
        Line line = getLine("");

        Optional<Violation> violation = new FiscalAddressValidator().validate(line);

        assertThat(violation).isEmpty();
    }

    @Test
    void validate_whenInvalidFiscalAddress_receiveViolation() {

        Line line = getLine("-!invalid-fiscalAddress-");

        Optional<Violation> violation = new FiscalAddressValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The fiscal address must contain only letters, numbers and spaces.");
    }

    @Test
    void validate_whenLengthLessThan5FiscalAddress_receiveViolation() {

        Line line = getLine("abc");

        Optional<Violation> violation = new FiscalAddressValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The fiscal address must be at least 5 characters long and no longer than 250.");
    }

    @Test
    void validate_whenLengthGreaterThan250FiscalAddress_receiveViolation() {

        String fiscalAddress251 = IntStream.range(0, 251).mapToObj(i -> "a").collect(Collectors.joining());
        Line line = getLine(fiscalAddress251);

        Optional<Violation> violation = new FiscalAddressValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The fiscal address must be at least 5 characters long and no longer than 250.");
    }

}
