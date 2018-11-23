package com.example.adeborja.examenuno;

import android.app.Application;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private List<Object> jugadores;


    @Override
    public void onCreate() {
        super.onCreate();

        jugadores = new ArrayList<Object>();

        rellenarLista();
    }

    public List<Object> getJugadores()
    {
        return this.jugadores;
    }

    public void rellenarLista()
    {
        String[] posicionesFutbolista = new String[]{"Delantero", "Defensa"};

        jugadores.add(new clsJugadorFutbol("Romelu Lukaku", R.drawable.romelu_lukaku_f, "Delantero", posicionesFutbolista, 1));

        posicionesFutbolista = new String[]{"Centrocampista", "Defensa"};
        jugadores.add(new clsJugadorFutbol("Kevin de Bruyne", R.drawable.kevin_de_bruyne_f, "Centrocampista", posicionesFutbolista, 2));

        posicionesFutbolista = new String[]{"Centrocampista", "Defensa"};
        jugadores.add(new clsJugadorFutbol("Kevin de Bruyne_2", R.drawable.kevin_de_bruyne_f, "Centrocampista", posicionesFutbolista, 3));

        jugadores.add(new clsJugadorBaloncesto("Lebron James", R.drawable.lebron_james_b, 30, 10, 3, 4));
        jugadores.add(new clsJugadorBaloncesto("Marc Gasol", R.drawable.marc_gasol_b, 20, 13, 8, 5));
        jugadores.add(new clsJugadorBaloncesto("Pau Gasol", R.drawable.pau_gasol_b, 34, 34, 34, 6));

        posicionesFutbolista = new String[]{"Centrocampista", "Defensa"};
        jugadores.add(new clsJugadorFutbol("Kevin de Bruyne_3", R.drawable.kevin_de_bruyne_f, "Centrocampista", posicionesFutbolista, 7));

        jugadores.add(new clsJugadorBaloncesto("Marc Gasol_2", R.drawable.marc_gasol_b, 20, 13, 8, 8));

        posicionesFutbolista = new String[]{"Delantero derecho", "Defensa", "Portero"};
        jugadores.add(new clsJugadorFutbol("Romelu Lukaku_2", R.drawable.romelu_lukaku_f, "Delantero derecho", posicionesFutbolista, 9));

        posicionesFutbolista = new String[]{"Delantero", "Defensa"};

        jugadores.add(new clsJugadorFutbol("Romelu Lukaku", R.drawable.romelu_lukaku_f, "Delantero", posicionesFutbolista, 10));

        posicionesFutbolista = new String[]{"Centrocampista", "Defensa"};
        jugadores.add(new clsJugadorFutbol("Kevin de Bruyne", R.drawable.kevin_de_bruyne_f, "Centrocampista", posicionesFutbolista, 11));

        posicionesFutbolista = new String[]{"Centrocampista", "Defensa"};
        jugadores.add(new clsJugadorFutbol("Kevin de Bruyne_2", R.drawable.kevin_de_bruyne_f, "Centrocampista", posicionesFutbolista, 12));

        jugadores.add(new clsJugadorBaloncesto("Lebron James", R.drawable.lebron_james_b, 30, 10, 3, 13));
        jugadores.add(new clsJugadorBaloncesto("Marc Gasol", R.drawable.marc_gasol_b, 20, 13, 8, 14));
        jugadores.add(new clsJugadorBaloncesto("Pau Gasol", R.drawable.pau_gasol_b, 34, 34, 34, 15));

        posicionesFutbolista = new String[]{"Centrocampista", "Defensa"};
        jugadores.add(new clsJugadorFutbol("Kevin de Bruyne_3", R.drawable.kevin_de_bruyne_f, "Centrocampista", posicionesFutbolista, 16));

        jugadores.add(new clsJugadorBaloncesto("Marc Gasol_2", R.drawable.marc_gasol_b, 20, 13, 8, 17));

        posicionesFutbolista = new String[]{"Delantero derecho", "Defensa", "Portero"};
        jugadores.add(new clsJugadorFutbol("Romelu Lukaku_2", R.drawable.romelu_lukaku_f, "Delantero derecho", posicionesFutbolista, 18));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
