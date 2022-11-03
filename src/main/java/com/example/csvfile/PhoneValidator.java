package com.example.csvfile;

import java.util.Optional;

public class PhoneValidator implements Validator{

    public Optional<Validation> validate(final Text text){

        if( text.getTelefono().trim().isEmpty()){
            return Optional.empty();
        }

        if( lengTextTelephone(text.getTelefono())){
            return Optional.of(new Validation(text.getLineNumber(), text.getId(), "Obligatorio 9 numeros","Telefono"));
        }

        if(!isValid(text.getTelefono())){
            return Optional.of(new Validation(text.getLineNumber(), text.getId(),"Debe contener solo numeros","Telefono"));
        }

        return Optional.empty();
    }

    public boolean lengTextTelephone(final String value){
        try{
            if (((value.matches("^[0-9]{0,8}$"))||(value.matches("^[0-9]{10,}$")))) {
                return true;
            }
        } catch (Exception e) { }
        return false;
    }
    public boolean isValid(final String value){
        try{
            if(value.matches("[0-9]{9}")){
                return true;
            }
        } catch (Exception e) { }
        return false;
    }
}
