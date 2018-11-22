package com.example.adeborja.spinnerdos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class principal extends AppCompatActivity {

    private Button btnBuscar;
    private TextView txvNombreEquipo;
    private ImageView imgEscudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txvNombreEquipo = (TextView)findViewById(R.id.txvNombreEquipo);
        imgEscudo = (ImageView)findViewById(R.id.imgEscudoEquipo);

        btnBuscar = (Button)findViewById(R.id.btnBuscarEquipo);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirSpinner();
            }
        });

        Intent recibirEquipo = getIntent(); //Meter un listener al boton de enviar que mande un intent q agrege un parcelable

        usarIntentRecibido(recibirEquipo);
    }

    public void abrirSpinner() {
        Intent i = new Intent(this, SpinnerDos.class);
        startActivity(i);
    }

    public void usarIntentRecibido(Intent i)
    {
        if(i.getExtras()!=null)
        {
            clsEquipo equipo = i.getParcelableExtra("equipo");
            txvNombreEquipo.setText(equipo.getNombre());
            imgEscudo.setImageResource(equipo.getEscudo());
        }
    }
}
