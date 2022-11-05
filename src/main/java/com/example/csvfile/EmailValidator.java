package com.example.csvfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmailValidator implements  Validator{

    List<String> listEmail = new ArrayList();

    public Optional<LineValidation> validate(final Line line) {

        listEmail.add(line.getEmail());

        if(line.getEmail().trim().isEmpty()){

            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(),"Obligatorio","email"));
        }

        if ( ! isValid(line.getEmail())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "debe contener \"@\" y acabar con \".com\", \".com.mx\" ", "email"));
        }

        if ( isRepeated(line.getEmail())){
                return Optional.of(new LineValidation(line.getLineNumber(), line.getId()," único no se puede repetir  ","email"));
        }

        // Si llegamos aqui no hay error
        return Optional.empty();
    }
    public boolean isValid(final String value) {

        try {
            if (value.matches("^[_A-Za-z0-9-]+@[a-z]+\\.(com\\.mx|com)$")) {
                return true;
            }
        } catch (Exception e) { }
        return false;
    }

    public boolean isRepeated(final String value){
        try{
            if (Collections.frequency(listEmail, value) > 1){
                return true;
            }
        } catch (Exception e) { }
        return false;
    }
}
