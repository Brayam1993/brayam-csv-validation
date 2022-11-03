package com.example.csvfile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmailValidator implements  Validator{
    private final List<String> allEmails;

    private static boolean doOnce=false;
    public EmailValidator(List<String> listEmail) { this.allEmails = listEmail; }

    public Optional<Validation> validate(final Text text) {

        if(text.getEmail().trim().isEmpty()){

            return Optional.of(new Validation(text.getLineNumber(), text.getId(),"Obligatorio","email"));
        }

        if ( ! isValid(text.getEmail())){
            // Campo invalido, devolvemos la validacion
            return Optional.of(new Validation(text.getLineNumber(), text.getId(), "debe contener \"@\" y acabar con \".com\", \".com.mx\" ", "email"));
        }

        if ( isRepeated(text.getEmail())){
            if(doOnce){
                return Optional.of(new Validation(text.getLineNumber(), text.getId()," único no se puede repetir  ","email"));
            } else {
                doOnce = true;
                return  Optional.empty();
            }
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
            if (Collections.frequency(allEmails, value) > 1){
                return true;
            }
        } catch (Exception e) { }
        return false;
    }
}
