package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.LiveData;
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

    @Delete
    public void borrarPersonaje(Personaje p);

    @Update
    public void actualizarPersonaje(Personaje p);

    /*@Query("select count(*) from personajes")
    public int obtenerCantidadPersonajes();*/

    @Query("select * from personajes")
    public LiveData<List<Personaje>> obtenerPersonajesLiveData();

}
