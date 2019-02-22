package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface miDao
{

    @Insert
    public void anadirPersonaje(Personaje p);

    @Query("select * from personajes")
    public List<Personaje> obtenerPersonajes();

}
