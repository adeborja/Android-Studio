package com.example.adeborja.intentimplicito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentImplicito extends AppCompatActivity {

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_implicito);

        boton = (Button) findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prueba();
            }
        });
    }

    public void prueba()
    {
        //action-filter creado en SpinnerDos y Spinner
        Intent i = new Intent();
        i.addCategory("APLICACION_ANGEL");
        i.setAction(Intent.ACTION_VIEW);
        if(i.resolveActivity(getPackageManager())!=null)
        {
            startActivity(i);
        }
    }
}
