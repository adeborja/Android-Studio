package com.example.adeborja.spinneruno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerUno extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String[] PALABRAS = {"hola", "halo", "helado", "hilado", "hondo", "holograma", "hololens", "hiniesta", "hilo", "humo", "hoja", "hojaldre", "hijo", "hija", "hipopotamo", "hermano", "hermana"};

    private TextView cajaTexto;
    private Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_uno);

        cajaTexto = (TextView)findViewById(R.id.txvTexto);
        spin = (Spinner)findViewById(R.id.spinner);

        //Añadir a spinner la propiedad para escuchar que se ha seleccionado (debe haberse implementado la interfaz AdapterView.OnItemSelectedListener)
        spin.setOnItemSelectedListener(this);

        //Definir el adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, PALABRAS);

        //Añadir el layout que tendra la lista desplegada del adaptador
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //asignar adaptador al spinner
        spin.setAdapter(adaptador);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        cajaTexto.setText(PALABRAS[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        cajaTexto.setText("");
    }
}
