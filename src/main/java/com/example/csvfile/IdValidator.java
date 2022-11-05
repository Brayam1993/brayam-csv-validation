package com.example.csvfile;

import java.util.Optional;

class IdValidator implements Validator {
    public Optional<LineValidation> validate(final Line line) {

        if ( line.getId().trim().isEmpty()){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "Obligatorio", "id"));
        }

        if ( ! isValid(line.getId())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "Debe ser numérico, mayor a cero", "id"));
        }

        return Optional.empty();
    }
    public boolean isValid(final String value) {
        try {
            if (Integer.parseInt(value) > 0) {
                return true;
            }
        } catch (Exception e) { }
        return false;
    }
}
