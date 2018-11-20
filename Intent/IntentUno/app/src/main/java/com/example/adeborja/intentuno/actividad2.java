package com.example.adeborja.intentuno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class actividad2 extends AppCompatActivity {

    Button boton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        boton2 = (Button)findViewById(R.id.boton2);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActividad1();
            }
        });
    }

    public void abrirActividad1()
    {
        Intent i = new Intent(this, IntentUno.class);
        startActivity(i);
    }
}
