package com.example.csvfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmailValidator implements  Validator{

    List<String> listEmail = new ArrayList();

    public Optional<Validation> validate(final Text text) {

        listEmail.add(text.getEmail());

        if(text.getEmail().trim().isEmpty()){

            return Optional.of(new Validation(text.getLineNumber(), text.getId(),"Obligatorio","email"));
        }

        if ( ! isValid(text.getEmail())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new Validation(text.getLineNumber(), text.getId(), "debe contener \"@\" y acabar con \".com\", \".com.mx\" ", "email"));
        }

        if ( isRepeated(text.getEmail())){
                return Optional.of(new Validation(text.getLineNumber(), text.getId()," único no se puede repetir  ","email"));
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
