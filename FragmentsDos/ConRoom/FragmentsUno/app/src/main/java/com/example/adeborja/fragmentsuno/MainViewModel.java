package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private boolean tablet;
    //private List<Personaje> listaPersonajes;
    private Context context;
    private Personaje personajeSeleccionado;

    private LiveData<List<Personaje>> listaLiveData;
    private miBaseDatos bd;

    public MainViewModel()
    {
        super();
    }

    public void setBd(miBaseDatos bd) {
        this.bd = bd;
    }

    public LiveData<List<Personaje>> getListaLiveData()
    {
        //if(listaLiveData == null)
        //{
            //listaLiveData = MainActivity.myBaseDatos.miDao().obtenerPersonajesLiveData();
            listaLiveData = bd.miDao().obtenerPersonajesLiveData();

            List<Personaje> aux = listaLiveData.getValue();
        //}

        return listaLiveData;
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
        return this.listaLiveData.getValue().get(posicion);
    }

    public Personaje getPersonajePorId(int id)
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
        for(int i=0;!encontrado&&i<listaLiveData.getValue().size();i++)
        {
            if(listaLiveData.getValue().get(i).getId()==id)
            {
                encontrado = true;
                p = listaLiveData.getValue().get(i);
            }
        }

        return p;
    }

    /*
    public void setListaPersonajes(List<Personaje> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }*/

    public void rellenarLista() {

        /*if(context != null && MainActivity.myBaseDatos.miDao().obtenerCantidadPersonajes() == 0)
        {
            Personaje p;
            listaPersonajes = new ArrayList<Personaje>();
            Uri retrato = Utilidades.getUriToDrawable(this.context,R.drawable.goku);
            ListaImagenes imagenes = new ListaImagenes(new ArrayList<Uri>(0));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.goku01));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.goku02));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.goku03));

            for(int i=0;i<10;i++)
            {
                p = new Personaje("Son Goku", "Goku 0"+i, "El prota de la serie", retrato, imagenes, 0);

                //this.listaPersonajes.add(p);
                MainActivity.myBaseDatos.miDao().anadirPersonaje(p);
            }*/

            /*retrato = Utilidades.getUriToDrawable(this.context, R.drawable.vegeta);
            imagenes = new ListaImagenes(new ArrayList<Uri>(0));
            //imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.dragon_ball_vegeta));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.vegeta01));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.vegeta02));
            p = new Personaje("Vegeta", "Vegeta", "El frentelarga", retrato, imagenes, 10);
            this.listaPersonajes.add(p);

            retrato = Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks);
            imagenes = new ListaImagenes(new ArrayList<Uri>(0));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks01));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks02));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks03));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks04));
            imagenes.anadirImagen(Utilidades.getUriToDrawable(this.context, R.drawable.ftrunks05));
            p = new Personaje("Trunks","Trunks del futuro", "Trunks del Futuro Alternativo (未来トランクス Mirai Torankusu), también conocido como Trunks del futuro, es un guerrero mestizo Saiyan/terrícola, hijo de las contrapartes de Bulma y Vegeta, Bulma del futuro y Vegeta del futuro, así como alumno de Gohan del futuro y aprendiz de Kaio-shin quien, con ayuda de su Máquina del tiempo, consigue viajar al tiempo de Son Goku proveniente de una línea de tiempo apocalíptica.\n" +
                    "\n" +
                    "Como el único bastión de los Guerreros Z del Futuro Alternativo, es sin duda uno de los personajes más recurrentes en la historia de Dragon Ball, sea como un protagonista o como apoyo, como se vio en su rol de Patrullero del Tiempo. ",retrato, imagenes, 11);
            this.listaPersonajes.add(p);

            retrato = Utilidades.getUriToDrawable(this.context, R.drawable.ic_launcher_background);
            imagenes = null;
            p = new Personaje("Son Gohan","Gohan","",retrato, imagenes, 12);
            this.listaPersonajes.add(p);*/
        //}

    }


    /*public void obtenerPersonajesDeBaseDatos()
    {
        List<Personaje> lista = MainActivity.myBaseDatos.miDao().obtenerPersonajes();

        listaPersonajes = lista;
    }*/
}
