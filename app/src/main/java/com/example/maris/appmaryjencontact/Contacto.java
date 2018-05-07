package com.example.maris.appmaryjencontact;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.ArrayList;

public class Contacto implements Parcelable{

    private String nombre;
    private String email;
    private String numero;
    private int imagencontacto;
    private boolean lista_favoritos;

    public static String KEY_CONTACT = "KEY_CONTACT";

    public Contacto(){}

    public Contacto(String nombre, String email, String numero, int imagencontacto) {
        this.nombre = nombre;
        this.email = email;
        this.numero = numero;
        this.imagencontacto = imagencontacto;
        lista_favoritos = false;
    }

    protected Contacto(Parcel in) {
        nombre = in.readString();
        email = in.readString();
        numero = in.readString();
        imagencontacto = in.readInt();
        lista_favoritos = in.readByte() != 0;
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(email);
        parcel.writeString(numero);
        parcel.writeInt(imagencontacto);
        parcel.writeByte((byte)(lista_favoritos?1:0));
    }
}
