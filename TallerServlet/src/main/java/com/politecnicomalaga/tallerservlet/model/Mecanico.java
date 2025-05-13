package com.politecnicomalaga.tallerservlet.model;

import java.util.HashMap;
import java.util.Map;

public class Mecanico extends Persona {
    private int anioIngreso;

    public Mecanico(String dni, String nombre, String apellido1,  String apellido2 ,String email, String telefono, int anioIngreso) {
        super(dni, nombre, apellido1, apellido2, email, telefono);
        this.anioIngreso = anioIngreso;
    }

    @Override
    public Map<String,String> toMap() {
        Map<String,String> data = super.toMap();

        data.put("fecha_ant",String.valueOf(anioIngreso));

        return data;
    }

    // Getters y Setters
}
