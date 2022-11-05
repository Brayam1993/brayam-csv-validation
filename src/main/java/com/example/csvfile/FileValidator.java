package com.example.csvfile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileValidator {
    private String fileName;

    public FileValidator(String fileName) throws IOException {
        this.fileName = fileName;
    }

    public List<LineValidation> getErrors() throws IOException {

        return getListValidation().stream()
                .filter(textValidation -> textValidation.isValid() == false)
                .flatMap(textValidation -> textValidation.getErrors().stream())
                .collect(Collectors.toList());
    }

    public List<Line> getValidRecords() throws IOException {

        return getListValidation().stream()
                .filter(textValidation -> textValidation.isValid())
                .map(textValidation -> textValidation.getLine())
                .collect(Collectors.toList());
    }

    private List<LineValidation> validateText(List<Validator> validators, Line line) {

        return validators.stream()
                .flatMap(validator -> validator.validate(line).stream())
                .collect(Collectors.toList());
    }

    private List<TextValidation> getListValidation() throws IOException {

        Path path = Path.of("src", "main", "resources", this.fileName);

        List<String> fileLines = Files.lines(path)
                .skip(1)
                .collect(Collectors.toList());


        List<Line> lineList = IntStream.range(0, fileLines.size())
                .mapToObj(i -> new Line(fileLines.get(i), i + 2))
                .collect(Collectors.toList());

        List<Validator> validators = List.of(
                new IdValidator(),
                new CompanyValidator(),
                new RfcValidator(),
                new EmailValidator(),
                new PhoneValidator(),
                new ForeignValidator(),
                new ContactValidator(),
                new FiscalAddressValidator()
        );

        return lineList.stream()
                .map(line -> new TextValidation(line, validateText(validators, line)))
                .collect(Collectors.toList());
    }
}