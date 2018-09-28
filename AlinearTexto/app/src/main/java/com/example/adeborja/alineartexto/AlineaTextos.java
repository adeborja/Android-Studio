package com.example.adeborja.alineartexto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AlineaTextos extends AppCompatActivity implements View.OnClickListener {

    Button btnIzquieda, btnDerecha;
    EditText cajaEscribir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alinea_textos);

        btnIzquieda = (Button) findViewById(R.id.btnIzquierda);
        btnDerecha = (Button) findViewById(R.id.btnDerecha);
        cajaEscribir = (EditText) findViewById(R.id.cajaTexto);

        btnIzquieda.setOnClickListener(this);
        btnDerecha.setOnClickListener(this);

    }


    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnIzquierda:
                //cajaEscribir.setText("Izquierda");
                cajaEscribir.setGravity(Gravity.LEFT);
                break;
            case R.id.btnDerecha:
                //cajaEscribir.setText("Derecha");
                cajaEscribir.setGravity(Gravity.RIGHT);
                break;
        }
    }
}
