package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private boolean tablet;
    private List<Personaje> listaPersonajes;

    public MainViewModel()
    {
        super();
        rellenarLista();
    }

    public boolean isTablet() {
        return tablet;
    }

    public void setTablet(boolean tablet)
    {
        this.tablet = tablet;
    }

    public List<Personaje> getListaPersonajes()
    {
        return listaPersonajes;
    }

    public Personaje getPersonaje(int posicion)
    {
        return this.listaPersonajes.get(posicion);
    }

    /*
    public void setListaPersonajes(List<Personaje> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }*/

    private void rellenarLista() {

        Personaje p;
        listaPersonajes = new ArrayList<Personaje>();
        int[] imagenes = {R.drawable.goku01, R.drawable.goku02, R.drawable.goku03};

        for(int i=0;i<10;i++)
        {
            p = new Personaje("Son Goku", "Goku 0"+i, "El prota de la serie", R.drawable.goku, imagenes, i);

            this.listaPersonajes.add(p);
        }

    }
}
