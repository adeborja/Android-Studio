package com.example.angel.textoconcheckboxes;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class TextoCheckboxes extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    TextView cuadro;
    CheckBox btnNegrito, btnRojito, btnPequenio, btnGrande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_checkboxes);

        cuadro = (TextView) findViewById(R.id.cuadroTexto);

        btnNegrito = (CheckBox) findViewById(R.id.btnNegrita);
        btnRojito = (CheckBox) findViewById(R.id.btnRojita);
        btnPequenio = (CheckBox) findViewById(R.id.btnChiquitita);
        btnGrande = (CheckBox) findViewById(R.id.btnGrandecita);

        /*btnNegrito.setOnClickListener(this);
        btnRojito.setOnClickListener(this);
        btnPequenio.setOnClickListener(this);
        btnGrande.setOnClickListener(this);*/

        btnNegrito.setOnCheckedChangeListener(this);
        btnRojito.setOnCheckedChangeListener(this);
        btnPequenio.setOnCheckedChangeListener(this);
        btnGrande.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    /*
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnNegrita:
                cuadro.setTypeface(cuadro.getTypeface(), Typeface.BOLD);
                break;
            case R.id.btnRojita:
                cuadro.setTextColor(Color.RED);
                break;
        }
    }
    */
}
