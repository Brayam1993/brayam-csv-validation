package com.example.csvfile;

import java.util.*;

public class RfcValidator implements Validator {

    List<String> listRfc = new ArrayList();
    public Optional<LineValidation> validate(final Line line) {

        listRfc.add(line.getRfc());

        if ( line.getRfc().trim().isEmpty() ){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "Obligatorio", "rfc"));
        }

        if ( lengTextRfc(line.getRfc())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "Obligatorio 13 caracteres ", "rfc"));
        }

        if ( ! isValid(line.getRfc())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "Debe contener solo letras, numeros y espacios ", "rfc"));
        }

        if ( isRepeated(line.getRfc())) {

                return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), " único no se puede repetir  ", "rfc"));

        }
        // Si llegamos aqui no hay error
        return Optional.empty();
    }

    public boolean lengTextRfc(final String value) {
        try {
            if (((value.matches("^[0-9a-zA-Z ]{0,12}$"))||(value.matches("^[0-9a-zA-Z ]{14,}$")))) {
                return true;
            }
        } catch (Exception e) { }
        return false;
    }

    public boolean isValid(final String value) {
        try {
            if (value.matches("[0-9a-zA-Z ]{13}")) {
                return true;
            }
        } catch (Exception e) { }
        return false;
    }

    public boolean isRepeated(final String value) {

        try {
            if ( Collections.frequency(listRfc, value) > 1 ){
                return true;
            }
        } catch (Exception e) { }
        return false;
    }
}
