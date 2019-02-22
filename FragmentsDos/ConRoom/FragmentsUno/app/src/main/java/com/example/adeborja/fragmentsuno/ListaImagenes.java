package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//@Entity(tableName = "listaimagenes")
public class ListaImagenes implements Parcelable
{
    //@PrimaryKey
    @ColumnInfo(name = "imagenes")
    //@TypeConverters({ListaImagenesConverter.class})
    private List<Uri> imagenes;

    public ListaImagenes()
    {
        this.imagenes = new ArrayList<Uri>(0);
    }

    //@Ignore
    public ListaImagenes(List<Uri> imagenes)
    {
        this.imagenes = imagenes;
    }


    public List<Uri> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Uri> imagenes) {
        this.imagenes = imagenes;
    }

    public void anadirImagen(Uri imagen)
    {
        this.imagenes.add(imagen);
    }

    public int getSize()
    {
        return this.imagenes.size();
    }

    public Uri getImagenDeLista(int posicion)
    {
        return this.imagenes.get(posicion);
    }




    protected ListaImagenes(Parcel in) {
        imagenes = in.createTypedArrayList(Uri.CREATOR);
    }

    public static final Creator<ListaImagenes> CREATOR = new Creator<ListaImagenes>() {
        @Override
        public ListaImagenes createFromParcel(Parcel in) {
            return new ListaImagenes(in);
        }

        @Override
        public ListaImagenes[] newArray(int size) {
            return new ListaImagenes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(imagenes);
    }
}
