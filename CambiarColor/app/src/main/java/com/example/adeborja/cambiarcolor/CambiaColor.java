package com.example.adeborja.cambiarcolor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CambiaColor extends AppCompatActivity {

    Button btnRed, btnBlue, btnGreen;
    EditText palabra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambia_color);

        btnBlue = (Button) findViewById(R.id.btnAzul);
        btnRed = (Button) findViewById(R.id.btnRojo);
        btnGreen = (Button) findViewById(R.id.btnVerde);
        palabra = (EditText) findViewById(R.id.cajaNombre);

    }

    public void pintarAzul(View V)
    {

    }
}
