package com.example.adeborja.spinnerdos;

import android.os.Parcel;
import android.os.Parcelable;

public class clsEquipo implements Parcelable {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String nombre;

    public int getEscudo() {
        return escudo;
    }

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    private int escudo;

    public clsEquipo()
    {
        this.nombre="";
        this.escudo=0;
    }

    public clsEquipo(String nNombre, int nEscudo)
    {
        this.nombre=nNombre;
        this.escudo=nEscudo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
