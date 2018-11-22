package com.example.angeldavid.intentmapa;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class IntentMapa extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Button btnIr;

    String[] ciudades = {"Sevilla", "Londres", "Paris", "Berlin", "Roma"};
    String[] geo = {"geo:37.373969, -5.969270", "geo:51.507817, -0.129252", "geo:48.859507, 2.347208", "geo:52.517415, 13.413719", "geo:41.897722, 12.508274"};
    Uri coordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_mapa);

        spinner = (Spinner)findViewById(R.id.spinnerLocalizaciones);
        btnIr = (Button)findViewById(R.id.btnIr);

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ciudades);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adaptador);

        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mandarCoordenadas();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        btnIr.setText("Ir a "+ciudades[position]);
        coordenadas = Uri.parse(geo[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public void mandarCoordenadas()
    {
        Intent i = new Intent(Intent.ACTION_VIEW, coordenadas);

        if(i.resolveActivity(getPackageManager())!=null)
        {
            startActivity(i);
        }
    }
}
