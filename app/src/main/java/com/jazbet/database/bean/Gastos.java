package com.jazbet.database.bean;

/**
 * Created by jazbet on 12/14/14.
 */

public class Gastos {
    private long id;
    private String concepto;
    private double cantdad;
    private long plazos;
    private String fecha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getCantdad() {
        return cantdad;
    }

    public void setCantdad(double cantdad) {
        this.cantdad = cantdad;
    }

    public long getPlazos() {
        return plazos;
    }

    public void setPlazos(long plazos) {
        this.plazos = plazos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
