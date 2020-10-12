package com.example.parcial1;

import java.util.List;

public class Corte {
   private String nombre;
   private int porcentaje;
   private double nota;
    private int idMateria;
   private String idCorte;


    public Corte() {
    }

    public Corte(String nombre, int porcentaje, double nota, int idMateria, String idCorte) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.nota = nota;
        this.idMateria = idMateria;
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

    public int getIdMateria() {
        return idMateria;
    }

    public String getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(String idCorte) {
        this.idCorte = idCorte;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }


}
