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
    private LiveData<Personaje> personajeLiveData;

    public PersonajeRepository(Application application)
    {
        miBaseDatos miBaseDatos = com.example.adeborja.fragmentsuno.miBaseDatos.getInstance(application);
        miDao = miBaseDatos.miDao();
        listLiveData = miDao.obtenerPersonajesLiveData();
        personajeLiveData = null;
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

    /*public void obtenerPersonajePorId(Personaje p)
    {
        new DeletePersonajeAsyncTask(miDao).execute(p);
    }*/

    /*public int obtenerCantidadPersonajes()
    {
//todo
    }*/

    public LiveData<List<Personaje>> obtenerPersonajesLiveData()
    {
        return this.listLiveData;
    }

    public LiveData<Personaje> obtenerPersonajePorId(long id)
    {
        personajeLiveData = this.miDao.obtenerPersonajePorId(id);

        return this.personajeLiveData;
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

    private static class CantidadPersonajeAsyncTask extends AsyncTask<Personaje, Void, Void>
    {
        private miDao miDao;
//todo: sigue por el video del viewmodel (5)
        private CantidadPersonajeAsyncTask(miDao miDao)
        {
            this.miDao = miDao;
        }

        @Override
        protected Void doInBackground(Personaje... personajes) {
            this.miDao.borrarPersonaje(personajes[0]);
            return null;
        }
    }

    //TODO: añadir aqui el metodo que meta en la base de datos los datos de prueba en caso de estar sin datos

    private static class rellenarBdAsyncTask extends AsyncTask<Personaje, Void, Void>
    {
        private miDao miDao;
        @Override
        protected Void doInBackground(Personaje... personajes) {
            for(int i=0;i<personajes.length;i++)
            {

            }
            return null;
        }
    }

}
