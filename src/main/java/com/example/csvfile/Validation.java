package com.example.csvfile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Validation {
    private Integer numberLine;
    private String id;
    private String error;
    private String field;

    @Override
    public String toString() {
        return "{" +
                "field='" + field + '\'' +
               //"id='" + id + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}