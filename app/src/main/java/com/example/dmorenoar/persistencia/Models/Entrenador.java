package com.example.dmorenoar.persistencia.Models;

public class Entrenador {

    private int idEntrenador;
    private String nombre;
    private int edad;
    private String especialidad;

    public Entrenador() {}


    public Entrenador(int idEntrenador, String nombre, int edad, String especialidad) {
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.edad = edad;
        this.especialidad = especialidad;
    }

    public Entrenador(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "idEntrenador=" + idEntrenador +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}