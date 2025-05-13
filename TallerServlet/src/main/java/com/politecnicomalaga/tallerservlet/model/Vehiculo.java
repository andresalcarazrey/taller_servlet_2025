package com.politecnicomalaga.tallerservlet.model;

public abstract class Vehiculo {
    protected String matricula;
    protected int anioFabricacion;
    protected String descripcionModelo;
    protected Cliente propietario;

    public Vehiculo(String matricula, int anioFabricacion, String descripcionModelo, Cliente propietario) {
        this.matricula = matricula;
        this.anioFabricacion = anioFabricacion;
        this.descripcionModelo = descripcionModelo;
        this.propietario = propietario;
    }

    public abstract double getPrecioHora();

    // Getters y Setters

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public String getDescripcionModelo() {
        return descripcionModelo;
    }

    public void setDescripcionModelo(String descripcionModelo) {
        this.descripcionModelo = descripcionModelo;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public void setPropietario(Cliente propietario) {
        this.propietario = propietario;
    }
}