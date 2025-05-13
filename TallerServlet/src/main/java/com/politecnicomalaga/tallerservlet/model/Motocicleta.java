package com.politecnicomalaga.tallerservlet.model;

public class Motocicleta extends Vehiculo {
    public Motocicleta(String matricula, int anioFabricacion, String descripcionModelo, Cliente propietario) {
        super(matricula, anioFabricacion, descripcionModelo, propietario);
    }

    @Override
    public double getPrecioHora() {
        return 10.0;
    }
}