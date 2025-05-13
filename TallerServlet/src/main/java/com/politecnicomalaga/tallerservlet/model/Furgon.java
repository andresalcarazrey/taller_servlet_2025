package com.politecnicomalaga.tallerservlet.model;

public class Furgon extends Vehiculo {
    public Furgon(String matricula, int anioFabricacion, String descripcionModelo, Cliente propietario) {
        super(matricula, anioFabricacion, descripcionModelo, propietario);
    }

    @Override
    public double getPrecioHora() {
        return 25.0;
    }

    public double aplicarSobrecostePiezas(double costePiezas) {
        return costePiezas * 1.10;
    }
}