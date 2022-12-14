package com.example.csvfile.validators;

import com.example.csvfile.validators.data.Line;
import com.example.csvfile.validators.data.ValidationResult;
import com.example.csvfile.validators.data.Violation;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.IntStream;

public class FileValidator {

    private final List<ValidationResult> validationResults;

    public FileValidator(String path) throws IOException {
        // Parse the entire file and transform it into the list of lines
        final List<Line> lines = readFile(path);

        // Get the list of all validators
        final List<Validator> validators = getValidatorsList(lines);

        // Validate the complete file and return get the ValidationResults
        validationResults = lines.stream()
                .map(line -> validate(line, validators))
                .toList();

    }

    private List<Validator> getValidatorsList(final List<Line> lines) {

        return List.of(
                new IdValidator(),
                new CompanyValidator(),
                new ContactValidator(),
                new RfcValidator(lines),
                new EmailValidator(lines),
                new PhoneValidator(),
                new ForeignValidator(),
                new FiscalAddressValidator()
        );
    }

    public List<Line> getValidRecords(){
        return validationResults.stream()
                .filter(ValidationResult::isValid)
                .map(ValidationResult::line)
                .toList();
    }

    public List<ValidationResult> getInvalidRecords(){
        return validationResults.stream()
                .filter(ValidationResult::isInvalid)
                .toList();
    }

    private static ValidationResult validate(final Line line, final List<Validator> validators) {

        final List<Violation> violations = validators.stream()
                .flatMap(validator -> validator.validate(line).stream())
                .toList();

        return new ValidationResult(line, violations);
    }

    @SneakyThrows
    private static List<Line> readFile(final String path) {

        final List<String> lines = Files.readAllLines(Path.of(path))
                .stream()
                .skip(1)
                .toList();

        return IntStream.range(0, lines.size())
                .mapToObj(index -> Line.toLine(index + 2, lines.get(index)))
                .toList();
    }
}