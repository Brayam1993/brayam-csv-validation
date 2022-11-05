package com.example.csvfile;

import java.util.Optional;

public class FiscalAddressValidator implements Validator{

    public Optional<LineValidation> validate(final Line line){

        if(lengTextFiscalAddress(line.getDireccionFiscal())){
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(), "Debe contenen de 5 a 250 caracteres","Direccion fiscal"));

        }

        if(!isValid(line.getDireccionFiscal())){
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(),"Debe contener solo letras, numeros, espacios y - ","Direccion fiscal"));
        }

        return Optional.empty();
    }

    public boolean lengTextFiscalAddress(final String value) {
        try{
            if(((value.matches("^[0-9a-zA-Z- ]{0,4}$"))||(value.matches("^[0-9a-zA-Z- ]{251,}$")))){
                return true;
            }

        } catch (Exception e){ }
        return false;
    }

    public boolean isValid(final String value){
        try{
            if(value.matches("[0-9a-zA-Z- ]{5,250}")){
                return true;
            }
        } catch (Exception e) { }
        return false;
    }

}
