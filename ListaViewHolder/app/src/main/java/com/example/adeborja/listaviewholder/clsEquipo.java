package com.example.adeborja.listaviewholder;

public class clsEquipo {

    private int id;
    private String nombre;
    private int imagenEscudo;
    private int imagenEstadio;

    public clsEquipo(int nId, String nNombre)
    {
        this.id=nId;
        this.nombre=nNombre;

        //Por defecto
        this.imagenEscudo=0;
        this.imagenEstadio=0;
    }

    public clsEquipo(int nId, String nNombre, int nImagenEscudo)
    {
        this.id=nId;
        this.nombre=nNombre;
        this.imagenEscudo=nImagenEscudo;

        //Por defecto
        this.imagenEstadio=0;
    }

    public clsEquipo(int nId, String nNombre, int nImagenEscudo, int nImagenEstadio)
    {
        this.id=nId;
        this.nombre=nNombre;
        this.imagenEscudo=nImagenEscudo;
        this.imagenEstadio=nImagenEstadio;
    }

    public int getId()
    {
        return this.id;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setImagenEscudo(int nImagenEscudo)
    {
        this.imagenEscudo = nImagenEscudo;
    }

    public int getImagenEscudo()
    {
        return this.imagenEscudo;
    }

    public int getImagenEstadio() {
        return imagenEstadio;
    }

    public void setImagenEstadio(int nImagenEstadio) {
        this.imagenEstadio = nImagenEstadio;
    }
}
