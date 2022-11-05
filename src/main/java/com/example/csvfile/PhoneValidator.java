package com.example.csvfile;

import java.util.Optional;

public class PhoneValidator implements Validator{

    public Optional<LineValidation> validate(final Line line){

        if( line.getTelefono().trim().isEmpty()){
            return Optional.empty();
        }

        if( lengTextTelephone(line.getTelefono())){
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "Obligatorio 9 numeros","Telefono"));
        }

        if(!isValid(line.getTelefono())){
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(),"Debe contener solo numeros","Telefono"));
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
