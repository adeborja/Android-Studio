package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface miDao
{

    @Insert
    public void anadirPersonaje(Personaje p);

    @Query("select * from personajes")
    public List<Personaje> obtenerPersonajes();

    @Delete
    public void borrarPersonaje(Personaje p);

    @Update
    public void actualizarPersonaje(Personaje p);

}
