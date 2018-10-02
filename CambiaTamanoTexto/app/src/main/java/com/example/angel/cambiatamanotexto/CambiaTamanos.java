package com.example.angel.cambiatamanotexto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CambiaTamanos extends AppCompatActivity implements View.OnClickListener {

    Button reducir, ampliar;
    TextView cuadro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambia_tamanos);

        cuadro = (TextView) findViewById(R.id.cuadroTexto);
        reducir = (Button) findViewById(R.id.btnReducir);
        ampliar = (Button) findViewById(R.id.btnAmpliar);

        reducir.setOnClickListener(this);
        ampliar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnReducir:
                cuadro.setTextSize(TypedValue.COMPLEX_UNIT_DIP, cuadro.getTextSize()-3);
                break;
            case R.id.btnAmpliar:
                cuadro.setTextSize(TypedValue.COMPLEX_UNIT_DIP, cuadro.getTextSize()+3);
                break;
        }
    }
}
