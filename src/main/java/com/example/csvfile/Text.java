package com.example.csvfile;

public class Text {

    private final int lineNumber;
    private final String id;
    private final String empresa;
    private final String rfc;
    private final String email;
    private final String telefono;
    private String extranjeras;
    private String contacto;

    public Text(int lineNumber, String id, String empresa, String rfc, String email, String telefono, String extranjeras, String contacto) {
        this.lineNumber = lineNumber;
        this.id = id;
        this.empresa = empresa;
        this.rfc = rfc;
        this.email = email;
        this.telefono = telefono;
        this.extranjeras = extranjeras;
        this.contacto = contacto;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getId() {
        return id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getRfc() {
        return rfc;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getExtranjeras() {
        return extranjeras;
    }
    public String getContacto() {
        return contacto;
    }

    @Override
    public String toString() {
        return "Text{" +
                "id='" + id + '\'' +
                ", empresa='" + empresa + '\'' +
                ", rfc='" + rfc + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", extranjeras='" + extranjeras + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }

    public Text(final String line, int lineNumber) {
        final String[] fields = line.split(",");
        this.lineNumber= lineNumber ;
        this.id = fields[0];
        this.empresa = fields[1];
        this.rfc = fields[2];
        this.email = fields[3];
        this.telefono = fields[4];
        this.extranjeras = fields[5];
        try{
            this.contacto = fields[6];
        }catch(Exception e){
            this.contacto = " ";
        }


    }

}