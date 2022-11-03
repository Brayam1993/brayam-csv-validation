package com.example.csvfile;

import java.util.Optional;

class IdValidator implements Validator {
    public Optional<Validation> validate(final Text text) {

        if ( text.getId().trim().isEmpty()){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new Validation(text.getLineNumber(), text.getId(), "Obligatorio", "id"));
        }

        if ( ! isValid(text.getId())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new Validation(text.getLineNumber(), text.getId(), "Debe ser numérico, mayor a cero", "id"));
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
