package com.example.adeborja.fragmentsuno;

import android.app.Application;
import android.content.res.Configuration;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class Datos extends Application {

    private List<Personaje> listaPersonajes;

    @Override
    public void onCreate()
    {
        super.onCreate();

        listaPersonajes = new ArrayList<Personaje>();

        rellenarLista();
    }

    private void rellenarLista() {

        Personaje p;
        int[] imagenes = {R.drawable.goku01, R.drawable.goku01, R.drawable.goku01};

        for(int i=0;i<10;i++)
        {
            p = new Personaje("Son Goku", "Goku 0"+i, "El prota de la serie", R.drawable.goku, imagenes);
        }

    }

    public List<Personaje> getListaPersonajes()
    {
        return this.listaPersonajes;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
    }

}
