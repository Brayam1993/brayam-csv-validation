package com.example.csvfile.test;

import com.example.csvfile.validators.PhoneValidator;
import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.data.Violation;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class PhoneValidatorTest {

    private static Line getLine(String phone) {
        return new Line(1,null,null,null,null,phone,null,null,null);
    }

    @Test
    void validate_whenNullPhone_receiveEmptyOptional() {

        final Line line = getLine(null);

        final Optional<Violation> violation = new PhoneValidator().validate(line);

        assertThat(violation).isEmpty();

    }

    @Test
    void validate_whenEmptyPhone_receiveEmptyOptional() {

        final Line line = getLine("");

        final Optional<Violation> violation = new PhoneValidator().validate(line);

        assertThat(violation).isEmpty();
    }

    @Test
    void validate_whenInvalidPhone_receiveViolation() {

        final Line line = getLine("invalid-phone");

        final Optional<Violation> violation = new PhoneValidator().validate(line);

        assertThat(violation).isPresent();
        assertThat(violation.get().message()).isEqualTo("The phone number must contains 9 numbers.");
        assertThat(violation.get().field()).isEqualTo("phone");
    }
}
