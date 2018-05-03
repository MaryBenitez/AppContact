package com.example.maris.appmaryjencontact;

import java.io.Serializable;

public class Contacto implements Serializable {

    private String nombre;
    private String direccion;
    private int numero;
    private int imagencontacto;

    public Contacto(String nombre, String direccion, int numero, int imagencontacto) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numero = numero;
        this.imagencontacto = imagencontacto;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getImagencontacto() {
        return imagencontacto;
    }
    public void setImagencontacto(int imagencontacto) {
        this.imagencontacto = imagencontacto;
    }
}
