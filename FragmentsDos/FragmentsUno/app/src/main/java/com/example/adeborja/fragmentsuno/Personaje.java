package com.example.adeborja.fragmentsuno;

import android.net.Uri;

public class Personaje {

    private String nombre;
    private String alias;
    private String descripcion;
    private int retrato;
    private int[] imagenes;

    public Personaje(String nombre, String alias, String descripcion, int retrato, int[] imagenes) {
        this.nombre = nombre;
        this.alias = alias;
        this.descripcion = descripcion;
        this.retrato=retrato;
        if(imagenes==null)
        {
            this.imagenes = new int[0];
        }
        else
        {
            this.imagenes = imagenes;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(int[] imagenes) {
        this.imagenes = imagenes;
    }

    public int getRetrato() {
        return retrato;
    }

    public void setRetrato(int retrato) {
        this.retrato = retrato;
    }

    public void anadirImagen(int imagen)
    {
        int[] nuevoImagenes = new int[imagenes.length+1];

        for(int i=0;i<imagenes.length;i++)
        {
            nuevoImagenes[i]=imagenes[i];
        }

        nuevoImagenes[imagenes.length] = imagen;
    }

    public int getCantidadImagenes()
    {
        return this.imagenes.length;
    }
}
