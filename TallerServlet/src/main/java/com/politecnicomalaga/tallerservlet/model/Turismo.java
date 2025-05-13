package com.politecnicomalaga.tallerservlet.model;

public class Turismo extends Vehiculo {
    public Turismo(String matricula, int anioFabricacion, String descripcionModelo, Cliente propietario) {
        super(matricula, anioFabricacion, descripcionModelo, propietario);
    }

    @Override
    public double getPrecioHora() {
        return 20.0;
    }
}