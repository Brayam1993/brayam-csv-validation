package com.example.csvfile;

import java.util.Optional;

public class ContactValidator implements  Validator{

    public Optional<LineValidation> validate(final Line line){

        if(lengTextContact(line.getContacto())){
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(),"Debe contener de 5 a 50 caracteres", "Contacto"));
        }

        if(!isValid(line.getContacto())){
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "Debe contener solo letras, numeros y espacios ", "Contacto"));
        }

        return Optional.empty();
    }

    public boolean lengTextContact(final String value) {
        try{
            if(((value.matches("^[0-9a-zA-Z ]{0,4}$"))||(value.matches("^[0-9a-zA-Z ]{51,}$")))){
                return true;
            }

        } catch (Exception e) { }
        return false;
    }

    public boolean isValid(final String value){
        try{
            if (value.matches("[0-9a-zA-Z ]{5,50}")) {
                return true;
            }
        } catch (Exception e) { }
        return false;
    }
}
