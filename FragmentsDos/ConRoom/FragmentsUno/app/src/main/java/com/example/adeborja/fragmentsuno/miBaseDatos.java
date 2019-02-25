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
@TypeConverters({Converters.class, ListaImagenesConverter.class})
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
            new rellenarDbAsyncTask(instance).execute();
        }
    };

    private static class rellenarDbAsyncTask extends AsyncTask<Context, Void, Void>
    {
        private miDao miDao;

        private rellenarDbAsyncTask(miBaseDatos db)
        {
            miDao = db.miDao();
        }

        @Override
        protected Void doInBackground(Context... contexts) {

            /*ListaImagenes imagenes = new ListaImagenes(new ArrayList<Uri>(0));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(contexts[0], R.drawable.goku01));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(contexts[0], R.drawable.goku02));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(contexts[0], R.drawable.goku03));*/

            this.miDao.anadirPersonaje(new Personaje("Son Goku", "Goku", "El prota de la serie", Utilidades.getUriToDrawable(contexts[0],R.drawable.goku), null, 0));
            return null;
        }
    }
}
