package com.example.csvfile;

import java.util.*;

public class RfcValidator implements Validator {

    List<String> listRfc = new ArrayList();
    public Optional<Validation> validate(final Text text) {

        listRfc.add(text.getRfc());

        if ( text.getRfc().trim().isEmpty() ){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new Validation(text.getLineNumber(), text.getId(), "Obligatorio", "rfc"));
        }

        if ( lengTextRfc(text.getRfc())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new Validation(text.getLineNumber(), text.getId(), "Obligatorio 13 caracteres ", "rfc"));
        }

        if ( ! isValid(text.getRfc())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new Validation(text.getLineNumber(), text.getId(), "Debe contener solo letras, numeros y espacios ", "rfc"));
        }

        if ( isRepeated(text.getRfc())) {

                return Optional.of(new Validation(text.getLineNumber(), text.getId(), " único no se puede repetir  ", "rfc"));

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
