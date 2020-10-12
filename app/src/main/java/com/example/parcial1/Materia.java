package com.example.parcial1;

import java.util.List;

public class Materia {
    private int codigoMateria;
    private String nombre;
    private double definitiva;


    public Materia() {

    }

    public Materia(int codigoMateria, String nombre, double definitiva) {
        this.codigoMateria = codigoMateria;
        this.nombre = nombre;
        this.definitiva = definitiva;
    }


    public int getCodigoMateria() {
        return codigoMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDefinitiva() {
        return definitiva;
    }

    public void setCodigoMateria(int codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDefinitiva(double definitiva) {
        this.definitiva = definitiva;
    }

    public String toString(){
        return Integer.toString(codigoMateria);
    }
}
