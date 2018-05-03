package com.example.maris.appmaryjencontact;

import java.io.Serializable;
import java.util.ArrayList;

public class Contacto implements Serializable {

    private String nombre;
    private ArrayList<String> email, numero;
    private int imagencontacto;
    private boolean favoritos;

    public Contacto(String nombre, ArrayList<String> email, ArrayList<String> numero, int imagencontacto) {
        this.nombre = nombre;
        this.email = email;
        this.numero = numero;
        this.imagencontacto = imagencontacto;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getEmail() {
        return email;
    }
    public void setEmail(ArrayList<String> email) {
        this.email = email;
    }

    public ArrayList<String> getNumero() {
        return numero;
    }
    public void setNumero(ArrayList<String> numero) {
        this.numero = numero;
    }

    public int getImagencontacto() {
        return imagencontacto;
    }
    public void setImagencontacto(int imagencontacto) {
        this.imagencontacto = imagencontacto;
    }

    public boolean isFavoritos() {
        return favoritos;
    }
    public void setFavoritos(boolean favoritos) {
        this.favoritos = favoritos;
    }
}
