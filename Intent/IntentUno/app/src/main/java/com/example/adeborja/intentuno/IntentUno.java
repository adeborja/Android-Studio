package com.example.adeborja.intentuno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentUno extends AppCompatActivity {

    Button boton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_uno);

        boton1 = (Button)findViewById(R.id.boton1);
        boton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                abrirActividad2();
            }
        });


    }

    public void abrirActividad2()
    {
        Intent i = new Intent(this, actividad2.class);
        startActivity(i);
    }

}
