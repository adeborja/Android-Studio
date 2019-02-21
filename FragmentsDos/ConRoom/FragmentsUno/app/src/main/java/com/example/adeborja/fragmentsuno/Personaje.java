package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

//@Entity(tableName = "personajes")
public class Personaje implements Parcelable {

    //@ColumnInfo (name = "nombre")
    private String nombre;
    //@ColumnInfo (name = "alias")
    private String alias;
    //@ColumnInfo (name = "descripcion")
    private String descripcion;
    //@ColumnInfo (name = "retrato")
    private Uri retrato;
    //@ColumnInfo (typeAffinity = ColumnInfo.BLOB)
    private Uri[] imagenes;
    //@PrimaryKey(autoGenerate = true)
    //@ColumnInfo (name = "id")
    private long id;

    public Personaje(String nombre, String alias, String descripcion, Uri retrato, Uri[] imagenes, long nId) {
        this.nombre = nombre;
        this.alias = alias;
        this.descripcion = descripcion;
        this.retrato=retrato;
        this.id=nId;
        if(imagenes==null)
        {
            this.imagenes = new Uri[0];
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

    public Uri[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(Uri[] imagenes) {
        this.imagenes = imagenes;
    }

    public Uri getRetrato() {
        return retrato;
    }

    public void setRetrato(Uri retrato) {
        this.retrato = retrato;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void anadirImagen(Uri imagen)
    {
        Uri[] nuevoImagenes = new Uri[imagenes.length+1];

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



    protected Personaje(Parcel in) {
        nombre = in.readString();
        alias = in.readString();
        descripcion = in.readString();
        retrato = in.readParcelable(Uri.class.getClassLoader());
        imagenes = in.createTypedArray(Uri.CREATOR);
        id = in.readLong();
    }

    public static final Creator<Personaje> CREATOR = new Creator<Personaje>() {
        @Override
        public Personaje createFromParcel(Parcel in) {
            return new Personaje(in);
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
        dest.writeParcelable(retrato, flags);
        dest.writeTypedArray(imagenes, flags);
        dest.writeLong(id);
    }
}
