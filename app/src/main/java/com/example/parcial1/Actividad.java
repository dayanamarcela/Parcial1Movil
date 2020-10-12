package com.example.parcial1;

public class Actividad {
private String nombre;
private int porcentaje;
private String idActividad;
private double nota;
private String idCorte;


    public Actividad() {
    }

    public Actividad(String nombre, int porcentaje, String idActividad, double nota, String idCorte) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.idActividad = idActividad;
        this.nota = nota;
        this.idCorte = idCorte;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }



    public double getNota() {
        return nota;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(String idCorte) {
        this.idCorte = idCorte;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }


}
