package com.example.adeborja.spinnerdos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerDos extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    private Spinner spin;
    private TextView cajaTexto;
    private String[] equipos = {"Mercedes", "Ferrari", "Red Bull", "Renault"};
    private int[] logos; //TODO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_dos);

        //Crear un adaptador personalizado para utilizarlo en esta app
    }

    class FormulaAdapter<T> extends ArrayAdapter<T>
    {
        public FormulaAdapter(Context c, int fila, int txv, T[] array)
        {
            super(c, fila, txv, array);
        }

        //TODO
    }
}
