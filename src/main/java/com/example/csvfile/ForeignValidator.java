package com.example.csvfile;

import java.util.Optional;

public class ForeignValidator implements Validator{

    public Optional<Validation> validate(final Text text){

        if ( text.getExtranjeras().trim().isEmpty()){

            return Optional.of(new Validation(text.getLineNumber(), text.getId(),"Obligatorio","Extranjeras"));
        }

        if( ! isValid(text.getExtranjeras())){
            return Optional.of(new Validation(text.getLineNumber(),text.getId(),"Debe contener \"1\", \"true\", \"verdadero\" - falsos: \"0\", \"false\", \"falso\"","Extranjeras"));
        }

        return Optional.empty();
    }

    public boolean isValid(final String value){
        try {
            if (value.matches("(true|[1]{1}|verdadero|false|[0]{1}|falso)")){
                return true;
            }
        } catch (Exception e) { }
        return false;
    }
}
