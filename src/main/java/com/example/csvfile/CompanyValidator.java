package com.example.csvfile;

import java.util.Optional;

public class CompanyValidator implements  Validator{
    public Optional<LineValidation> validate(final Line line) {

        if ( lengTextCompany(line.getEmpresa())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "Maximo 50 caracteres ", "empresa"));
        }

        if ( ! isValid(line.getEmpresa())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "Debe contener solo letras, numeros y espacios ", "empresa"));
        }


        // Si llegamos aqui no hay error
        return Optional.empty();
    }

    public boolean lengTextCompany(final String value) {
        try {
            if (value.matches("[0-9a-zA-Z ]{50,}")) {
                return true;
            }
        } catch (Exception e) { }
        return false;
    }

    public boolean isValid(final String value) {
        try {
            if (value.matches("[0-9a-zA-Z ]{0,50}")) {
                return true;
            }
        } catch (Exception e) { }
        return false;
    }

}
