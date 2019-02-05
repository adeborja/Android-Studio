package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    private boolean tablet;
    //private List<Personaje> listaPersonajes;
    //private static Datos datos = new Datos();

    public boolean isTablet() {
        return tablet;
    }

    public void setTablet(boolean tablet) {
        this.tablet = tablet;
    }

    /*public List<Personaje> getListaPersonajes(Datos d)
    {
        this.listaPersonajes=d.getListaPersonajes();

        return listaPersonajes;
    }

    public void setListaPersonajes(List<Personaje> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }*/
}
