package com.example.dmorenoar.persistencia.Models;

public class Pokemon {
    private String nombre;
    private String tipo;
    private int fuerza;
    private int imagen;
    private Entrenador entrenador;


    public Pokemon() { }

    public Pokemon(String nombre, String tipo, int fuerza, int imagen, Entrenador entrenador) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fuerza = fuerza;
        this.imagen = imagen;
        this.entrenador = entrenador;
    }


    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    @Override
    public String toString() {
        return "Pokemon{" + "nombre=" + nombre + ", tipo=" + tipo + ", fuerza=" + fuerza + ", entrenador=" + entrenador + '}';
    }
}
