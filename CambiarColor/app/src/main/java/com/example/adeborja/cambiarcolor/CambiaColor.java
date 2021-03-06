package com.example.adeborja.cambiarcolor;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
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
        palabra.setTextColor(Color.BLUE);
    }

    public void pintarRojo(View V)
    {
        palabra.setTextColor(Color.RED);
    }

    public void pintarVerde(View V)
    {
        palabra.setTextColor(Color.GREEN);
    }
}
