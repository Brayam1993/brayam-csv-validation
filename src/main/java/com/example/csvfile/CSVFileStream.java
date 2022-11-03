package com.example.csvfile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVFileStream  {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("src","main","resources","text.csv");

        List<String> fileLines = Files.lines(path)
                .skip(1)
                .collect(Collectors.toList());

        List<Text> textList = IntStream.range(0, fileLines.size())
                .mapToObj(i -> new Text(fileLines.get(i), i + 2))
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

        List<TextValidation> collect = textList.stream()
                .map(line -> new TextValidation(line, validateText(validators,line)))
                .collect(Collectors.toList());

        List<Text> validLines = getValidLines(collect);
        List<Validation> allErrors = getAllErrors(collect);

        Map<Integer,List<Validation>> errorsByLineNumber = allErrors.stream()
                .collect(Collectors.groupingBy(Validation::getNumberLine));
        System.out.println(errorsByLineNumber);

        System.out.println(validLines);

    }

    private static List<Validation> getAllErrors(List<TextValidation> collect) {
        return collect.stream()
                .filter(textValidation -> textValidation.isValid() == false)
                .flatMap(textValidation -> textValidation.getErrors().stream())
                .collect(Collectors.toList());
    }

    private static List<Text> getValidLines(List<TextValidation> collect) {
        return collect.stream()
                .filter(textValidation -> textValidation.isValid())
                .map(textValidation -> textValidation.getLine())
                .collect(Collectors.toList());
    }

    private static List<Validation> validateText(List<Validator> validators, Text text) {

        return validators.stream()
                .flatMap(validator -> validator.validate(text).stream())
                .collect(Collectors.toList());
    }
}