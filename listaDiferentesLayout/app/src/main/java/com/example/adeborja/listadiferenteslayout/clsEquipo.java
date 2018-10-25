package com.example.adeborja.listadiferenteslayout;

public class clsEquipo {

    private int id;
    private String nombre;
    private String imagen;

    public clsEquipo(int nId, String nNombre, String nImagen)
    {
        this.id=nId;
        this.nombre=nNombre;
        this.imagen=nImagen;
    }

    public int getId()
    {
        return this.id;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setImagen(String nImagen)
    {
        this.imagen = nImagen;
    }

    public String getImagen()
    {
        return this.imagen;
    }
}
