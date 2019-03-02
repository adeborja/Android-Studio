package com.example.adeborja.fragmentsuno;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Personaje.class}, version = 1)//, exportSchema = true)
@TypeConverters({Converters.class})
public abstract class miBaseDatos extends RoomDatabase
{
    public abstract miDao miDao();

    private static miBaseDatos instance;

    public static synchronized miBaseDatos getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), miBaseDatos.class, "personajesdb")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
