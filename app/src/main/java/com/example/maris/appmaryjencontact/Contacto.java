package com.example.maris.appmaryjencontact;

import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.ArrayList;

public class Contacto implements Serializable {

    private String nombre;
    private String email;
    private String numero;
    public static String KEY = "KEY";
    private int imagencontacto;
    private boolean lista_favoritos;

    public Contacto(){}

    public Contacto(String nombre, String email, String numero, int imagencontacto) {
        this.nombre = nombre;
        this.email = email;
        this.numero = numero;
        this.imagencontacto = imagencontacto;
        lista_favoritos = false;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getImagencontacto() {
        return imagencontacto;
    }
    public void setImagencontacto(int imagencontacto) {
        this.imagencontacto = imagencontacto;
    }

    public boolean isLista_favoritos() {
        return lista_favoritos;
    }
    public void setLista_favoritos(boolean lista_favoritos) {
        this.lista_favoritos = lista_favoritos;
    }
}
