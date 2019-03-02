package com.example.adeborja.fragmentsuno;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class PersonajeRepository
{
    private miDao miDao;
    private LiveData<List<Personaje>> listLiveData;

    public PersonajeRepository(Application application)
    {
        miBaseDatos miBaseDatos = com.example.adeborja.fragmentsuno.miBaseDatos.getInstance(application);
        miDao = miBaseDatos.miDao();
        listLiveData = miDao.obtenerPersonajesLiveData();
    }

    public void insert(Personaje p)
    {
        new InsertPersonajeAsyncTask(miDao).execute(p);
    }

    public void update(Personaje p)
    {
        //this.personajeLiveData = null;
        new UpdatePersonajeAsyncTask(miDao).execute(p);
    }

    public void delete(Personaje p)
    {
        new DeletePersonajeAsyncTask(miDao).execute(p);
    }

    public LiveData<List<Personaje>> obtenerPersonajesLiveData()
    {
        return this.listLiveData;
    }

    private static class InsertPersonajeAsyncTask extends AsyncTask<Personaje, Void, Void>
    {
        private miDao miDao;

        private InsertPersonajeAsyncTask(miDao miDao)
        {
            this.miDao = miDao;
        }

        @Override
        protected Void doInBackground(Personaje... personajes) {
            this.miDao.anadirPersonaje(personajes[0]);
            return null;
        }
    }

    private static class UpdatePersonajeAsyncTask extends AsyncTask<Personaje, Void, Void>
    {
        private miDao miDao;

        private UpdatePersonajeAsyncTask(miDao miDao)
        {
            this.miDao = miDao;
        }

        @Override
        protected Void doInBackground(Personaje... personajes) {
            this.miDao.actualizarPersonaje(personajes[0]);
            return null;
        }
    }

    private static class DeletePersonajeAsyncTask extends AsyncTask<Personaje, Void, Void>
    {
        private miDao miDao;

        private DeletePersonajeAsyncTask(miDao miDao)
        {
            this.miDao = miDao;
        }

        @Override
        protected Void doInBackground(Personaje... personajes) {
            this.miDao.borrarPersonaje(personajes[0]);
            return null;
        }
    }
}
