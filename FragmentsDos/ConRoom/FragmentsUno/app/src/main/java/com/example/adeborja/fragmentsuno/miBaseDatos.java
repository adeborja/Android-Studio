package com.example.adeborja.fragmentsuno;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Personaje.class}, version = 1, exportSchema = true)
@TypeConverters({Converters.class, ListaImagenesConverter.class})
public abstract class miBaseDatos extends RoomDatabase
{
    public abstract miDao miDao();
}
