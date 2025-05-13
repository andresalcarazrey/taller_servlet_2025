package com.politecnicomalaga.tallerservlet.model;

import com.politecnicomalaga.tallerservlet.data.Mapeable;

import java.util.HashMap;
import java.util.Map;

public abstract class Persona implements Mapeable {
    protected String dni;
    protected String nombre;
    protected String apellido1;
    protected String apellido2;
    protected String email;
    protected String telefono;

    public Persona(String dni, String nombre, String apellido1, String apellido2, String email, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.telefono = telefono;
    }


    @Override
    public Map<String,String> toMap() {
        Map<String,String> data = new HashMap<>();
        data.put("dni",dni);
        data.put("nombre",nombre);
        data.put("apellido1",apellido1);
        data.put("apellido2",apellido2);
        data.put("email",email);
        data.put("telefono",telefono);

        return data;
    }

    // Getters y Setters
}
