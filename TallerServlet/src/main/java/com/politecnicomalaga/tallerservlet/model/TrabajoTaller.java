package com.politecnicomalaga.tallerservlet.model;

import java.time.LocalDate;

public class TrabajoTaller {
    private String codigo;
    private Vehiculo vehiculo;
    private String descripcionTrabajo;
    private double costePiezas;
    private double horasTrabajo;
    private Mecanico mecanico;
    private boolean finalizado;
    private boolean cobrado;
    private LocalDate fechaEntrada;

    public TrabajoTaller(LocalDate fechaEntrada, Vehiculo vehiculo, String descripcionTrabajo, double costePiezas,
                         double horasTrabajo, Mecanico mecanico) {
        this.fechaEntrada = fechaEntrada;
        this.vehiculo = vehiculo;
        this.descripcionTrabajo = descripcionTrabajo;
        this.costePiezas = costePiezas;
        this.horasTrabajo = horasTrabajo;
        this.mecanico = mecanico;
        this.finalizado = false;
        this.cobrado = false;
        this.codigo = generarCodigo(fechaEntrada, vehiculo.getMatricula());
    }

    private String generarCodigo(LocalDate fecha, String matricula) {
        return fecha.toString().replace("-", "") + matricula;
    }

    public double calcularCosteTotal() {
        double totalPiezas = (vehiculo instanceof Furgon)
                ? ((Furgon) vehiculo).aplicarSobrecostePiezas(costePiezas)
                : costePiezas;
        return totalPiezas + horasTrabajo * vehiculo.getPrecioHora();
    }

    // Getters y Setters
}

