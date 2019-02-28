package com.example.adeborja.fragmentsuno;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private boolean tablet;
    //private List<Personaje> listaPersonajes;
    private Context context;
    private Personaje personajeSeleccionado;

    private LiveData<List<Personaje>> listaLiveData;
    //private miBaseDatos bd;
    private PersonajeRepository repository;
    //private LiveData<Personaje> personajeLiveData;

    public MainViewModel(@NonNull Application application)
    {
        super(application);
        repository = new PersonajeRepository(application);
        //getListaLiveData();
        listaLiveData = repository.obtenerPersonajesLiveData();
        //personajeLiveData = null;
    }

    /*public void setBd(miBaseDatos bd) {
        this.bd = bd;
    }*/

    public LiveData<List<Personaje>> getListaLiveData()
    {
        //if(listaLiveData == null)
        //{
            //listaLiveData = MainActivity.myBaseDatos.miDao().obtenerPersonajesLiveData();

        //esto va al constructor y se deja solo el return, de momento se deja aqui por testeo
            //listaLiveData = repository.obtenerPersonajesLiveData();

            //List<Personaje> aux = listaLiveData.getValue();
        //}

        return listaLiveData;
    }

    /*public LiveData<Personaje> getPersonajeLiveData(long id)
    {
        personajeLiveData = repository.obtenerPersonajePorId(id);

        return personajeLiveData;
    }*/

    public void insert(Personaje p)
    {
        repository.insert(p);
    }

    public void update(Personaje p)
    {
        repository.update(p);
    }

    public void delete(Personaje p)
    {
        repository.delete(p);
    }



    public Personaje getPersonajeSeleccionado() {
        return personajeSeleccionado;
    }

    public void setPersonajeSeleccionado(Personaje personajeSeleccionado) {
        this.personajeSeleccionado = personajeSeleccionado;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean isTablet() {
        return tablet;
    }

    public void setTablet(boolean tablet)
    {
        this.tablet = tablet;
    }

    /*public List<Personaje> getListaPersonajes()
    {
        return listaPersonajes;
    }*/

    public Personaje getPersonaje(int posicion)
    {
        //return this.listaPersonajes.get(posicion);
        Personaje p = listaLiveData.getValue().get(posicion);

        return p;
    }

    /*public Personaje getPersonajePorId(int id)
    {
        Personaje p = null;
        boolean encontrado = false;
        /*for(int i=0;!encontrado&&i<listaPersonajes.size();i++)
        {
            if(listaPersonajes.get(i).getId()==id)
            {
                encontrado = true;
                p = listaPersonajes.get(i);
            }
        }*/
        /*for(int i=0;!encontrado&&i<listaLiveData.getValue().size();i++)
        {
            if(listaLiveData.getValue().get(i).getId()==id)
            {
                encontrado = true;
                p = listaLiveData.getValue().get(i);
            }
        }

        return p;
    }*/

    /*
    public void setListaPersonajes(List<Personaje> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }*/

    public void rellenarLista() {

        //if(context != null && MainActivity.myBaseDatos.miDao().obtenerCantidadPersonajes() == 0)
        //{
            Personaje p;
            PersonajeRepository r = new PersonajeRepository(getApplication());
            //listaPersonajes = new ArrayList<Personaje>();
            Uri retrato = Utilidades.getUriToDrawable(this.context,R.drawable.goku);
            /*ListaImagenes imagenes = new ListaImagenes(new ArrayList<Uri>(0));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.goku01));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.goku02));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.goku03));*/

            List<String> listStringImagenes = new ArrayList<>(0);
            listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.goku01).toString());
            listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.goku02).toString());
            listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.goku03).toString());

            //for(int i=0;i<10;i++)
            //{
                p = new Personaje("Son Goku", "Goku", "El prota de la serie", retrato, listStringImagenes, 0);

                //this.listaPersonajes.add(p);
                //MainActivity.myBaseDatos.miDao().anadirPersonaje(p);
                r.insert(p);
            //}

            retrato = Utilidades.getUriToDrawable(this.context, R.drawable.vegeta);
            listStringImagenes = new ArrayList<>(0);
            listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.vegeta01).toString());
            listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.vegeta02).toString());

            p = new Personaje("Vegeta", "Vegeta", "El frentelarga", retrato, listStringImagenes, 0);
            r.insert(p);

            retrato = Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks);
            listStringImagenes = new ArrayList<>(0);
            listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks01).toString());
            listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks02).toString());
            listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks03).toString());
            listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks04).toString());
            listStringImagenes.add(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks05).toString());

            p = new Personaje("Trunks","Trunks del futuro", "Trunks del Futuro Alternativo (未来トランクス Mirai Torankusu), también conocido como Trunks del futuro, es un guerrero mestizo Saiyan/terrícola, hijo de las contrapartes de Bulma y Vegeta, Bulma del futuro y Vegeta del futuro, así como alumno de Gohan del futuro y aprendiz de Kaio-shin quien, con ayuda de su Máquina del tiempo, consigue viajar al tiempo de Son Goku proveniente de una línea de tiempo apocalíptica.\n" +
                    "\n" +
                    "Como el único bastión de los Guerreros Z del Futuro Alternativo, es sin duda uno de los personajes más recurrentes en la historia de Dragon Ball, sea como un protagonista o como apoyo, como se vio en su rol de Patrullero del Tiempo. ",retrato, listStringImagenes, 0);
            r.insert(p);

            retrato = Utilidades.getUriToDrawable(this.context, R.drawable.ic_launcher_background);
            listStringImagenes = null;
            p = new Personaje("Son Gohan","Gohan","",retrato, listStringImagenes, 0);
            r.insert(p);
        //}

    }


    /*public void obtenerPersonajesDeBaseDatos()
    {
        List<Personaje> lista = MainActivity.myBaseDatos.miDao().obtenerPersonajes();

        listaPersonajes = lista;
    }*/
}
