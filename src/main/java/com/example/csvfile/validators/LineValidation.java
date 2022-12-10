package com.example.csvfile.validators;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LineValidation {
    private Integer numberLine;
    private String id;
    private String error;
    private String field;

    @Override
    public String toString() {
        return "error {" +
                "numberLine='" + numberLine + '\'' +
                ", field='" + field + '\'' +
               //"id='" + id + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}