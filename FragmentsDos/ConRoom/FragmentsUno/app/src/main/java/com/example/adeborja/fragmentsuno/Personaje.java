package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "personajes")
public class Personaje implements Parcelable {

    @ColumnInfo (name = "nombre")
    private String nombre;
    @ColumnInfo (name = "alias")
    private String alias;
    @ColumnInfo (name = "descripcion")
    private String descripcion;
    @ColumnInfo (name = "retrato")
    private Uri retrato;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id")
    private long id;

    /*@ColumnInfo (name = "imagenes")
    @TypeConverters({ListaImagenesConverter.class})
    //@Embedded
    private ListaImagenes imagenes;*/

    @ColumnInfo (name = "listimagenes")
    @TypeConverters({ListUriConverter.class})
    private List<Uri> listImagenes;


    public Personaje() {

    }

    //public Personaje(String nombre, String alias, String descripcion, Uri retrato, ListaImagenes imagenes, long nId) {
    public Personaje(String nombre, String alias, String descripcion, Uri retrato, List<Uri> listimagenes, long nId) {
        this.nombre = nombre;
        this.alias = alias;
        this.descripcion = descripcion;
        this.retrato=retrato;
        this.id=nId;
        /*if(imagenes==null)
        {
            this.imagenes = new ListaImagenes(new ArrayList<Uri>(0));
        }
        else
        {
            this.imagenes = imagenes;
        }*/

        if(listImagenes==null)
        {
            this.listImagenes = new ArrayList<Uri>(0);
        }
        else
        {
            this.listImagenes = listimagenes;
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

    /*public ListaImagenes getImagenes() {
        return imagenes;
    }

    public void setImagenes(ListaImagenes imagenes) {
        this.imagenes = imagenes;
    }*/

    public List<Uri> getListImagenes() {
        return listImagenes;
    }

    public void setListImagenes(List<Uri> listImagenes) {
        this.listImagenes = listImagenes;
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
        //this.imagenes.anadirImagen(imagen);
        this.listImagenes.add(imagen);
    }

    public String getCantidadImagenes()
    {
        String cantidad = String.valueOf(this.listImagenes.size()); //imagenes.getSize());

        return cantidad;
    }




    protected Personaje(Parcel in) {
        nombre = in.readString();
        alias = in.readString();
        descripcion = in.readString();
        retrato = in.readParcelable(Uri.class.getClassLoader());
        id = in.readLong();
        listImagenes = in.createTypedArrayList(Uri.CREATOR);
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
        dest.writeLong(id);
        dest.writeTypedList(listImagenes);
    }

    //constructor: imagenes = in.readParcelable(ListaImagenes.class.getClassLoader());
    //write to parcel: dest.writeParcelable(imagenes, flags);


}
