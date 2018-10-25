package com.example.adeborja.listadiferenteslayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class listaDiferentesLayout extends AppCompatActivity {

    ArrayList<clsEquipo> listaEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_diferentes_layout);

        rellenarLista();
    }

    public void rellenarLista()
    {
        listaEquipos = new ArrayList<clsEquipo>(0);

        listaEquipos.add(new clsEquipo(0,"Sevilla FC",""));
        listaEquipos.add(new clsEquipo(1,"FC Barcelona",""));
        listaEquipos.add(new clsEquipo(2,"Real Madrid",""));
        listaEquipos.add(new clsEquipo(3,"Atletico Madrid",""));
        listaEquipos.add(new clsEquipo(4,"Valencia",""));
        listaEquipos.add(new clsEquipo(5,"Real Betis",""));
        listaEquipos.add(new clsEquipo(6,"Villarreal",""));
        listaEquipos.add(new clsEquipo(7,"Getafe",""));
        listaEquipos.add(new clsEquipo(8,"Alaves",""));
        listaEquipos.add(new clsEquipo(9,"Athletic Bilbao",""));
        listaEquipos.add(new clsEquipo(10,"Celta",""));
        listaEquipos.add(new clsEquipo(11,"Eibar",""));
        listaEquipos.add(new clsEquipo(12,"Espanyol",""));
        listaEquipos.add(new clsEquipo(13,"Girona",""));
        listaEquipos.add(new clsEquipo(14,"Huesca",""));
        listaEquipos.add(new clsEquipo(15,"Leganes",""));
        listaEquipos.add(new clsEquipo(16,"Levante",""));
        listaEquipos.add(new clsEquipo(17,"Rayo Vallecano",""));
        listaEquipos.add(new clsEquipo(18,"Real Sociedad",""));
        listaEquipos.add(new clsEquipo(19,"Valladolid",""));


    }

}
