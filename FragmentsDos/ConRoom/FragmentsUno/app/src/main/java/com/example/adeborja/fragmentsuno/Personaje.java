package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "personajes")
public class Personaje implements Parcelable {

    @ColumnInfo (name = "nombre")
    private String nombre;
    @ColumnInfo (name = "alias")
    private String alias;
    @ColumnInfo (name = "descripcion")
    private String descripcion;
    @ColumnInfo (name = "retrato")
    private int retrato;
    //?? decidir
    private int[] imagenes;
    @PrimaryKey
    private long id;

    public Personaje(String nombre, String alias, String descripcion, int retrato, int[] imagenes, long nId) {
        this.nombre = nombre;
        this.alias = alias;
        this.descripcion = descripcion;
        this.retrato=retrato;
        this.id=nId;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCantidadImagenes()
    {
        String cantidad = String.valueOf(this.imagenes.length);

        return cantidad;
    }



    protected Personaje(Parcel in)
    {
        this.nombre = in.readString();
        this.alias = in.readString();
        this.descripcion = in.readString();
        this.retrato=in.readInt();
        //this.imagenes = in.readIntArray(); //TODO
        this.imagenes = in.createIntArray();
        this.id = in.readLong();
    }

    public static final Creator<Personaje> CREATOR = new Creator<Personaje>() {
        @Override
        public Personaje createFromParcel(Parcel source) {
            return new Personaje(source);
        }

        @Override
        public Personaje[] newArray(int size) {
            return new Personaje[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(alias);
        dest.writeString(descripcion);
        dest.writeInt(retrato);
        dest.writeIntArray(imagenes);
        dest.writeLong(id);
    }
}
