package com.jazbet.database.bean;

/**
 * Created by jazbet on 12/14/14.
 */

public class Capital {
    private long mes;
    private long anio;
    private double cantidad;

    public long getMes() {
        return mes;
    }

    public void setMes(long mes) {
        this.mes = mes;
    }

    public long getAnio() {
        return anio;
    }

    public void setAnio(long anio) {
        this.anio = anio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
