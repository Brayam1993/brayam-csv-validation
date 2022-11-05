package com.example.csvfile;

import java.util.Optional;

public class ForeignValidator implements Validator{

    public Optional<LineValidation> validate(final Line line){

        if ( line.getExtranjeras().trim().isEmpty()){

            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(),"Obligatorio","Extranjeras"));
        }

        if( ! isValid(line.getExtranjeras())){
            return Optional.of(new LineValidation(line.getLineNumber(), line.getId(),"Debe contener \"1\", \"true\", \"verdadero\" - falsos: \"0\", \"false\", \"falso\"","Extranjeras"));
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
