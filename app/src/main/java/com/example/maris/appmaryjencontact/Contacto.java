package com.example.maris.appmaryjencontact;

public class Contacto {

    private String nombre;
    private String apellido;
    private String direccion;
    private int numero;

    public Contacto(String nombre, String apellido, String direccion, int numero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
}
