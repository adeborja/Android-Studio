package com.example.adeborja.cambiaimagenalpulsar;

import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CambiaImagen extends AppCompatActivity implements View.OnClickListener {

    ImageView cuadro, conSonido, sinSonido;

    boolean muteado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambia_imagen);

        cuadro = (ImageView) findViewById(R.id.cuadroVacio);
        conSonido = (ImageView) findViewById(R.id.conSonido);
        sinSonido = (ImageView) findViewById(R.id.conSilencio);


        cuadro.setOnClickListener(this);
        //cuadro.setImageDrawable(conSonido.getDrawable());
        cuadro.setImageResource(R.drawable.consonido);
    }

    @Override
    public void onClick(View view) {
        /*switch (view.getId())
        {
            case R.id.conSonido:
                //cuadro.setImageDrawable(sinSonido.getDrawable());
                cuadro.setImageResource(R.drawable.muted);
                break;

            case R.id.conSilencio:
                //cuadro.setImageDrawable(conSonido.getDrawable());
                cuadro.setImageResource(R.drawable.consonido);
                break;
        }*/

        if(view.getId()==R.id.cuadroVacio)
        {
            if(!muteado)
            {
                cuadro.setImageResource(R.drawable.muted);
                muteado = true;
            }
            else
            {
                cuadro.setImageResource(R.drawable.consonido);
                muteado = false;
            }
        }
    }
}
