package com.example.angel.textoconcheckboxes;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
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

        btnNegrito.setChecked(false);
        btnRojito.setChecked(false);
        btnPequenio.setChecked(false);
        btnGrande.setChecked(false);

        /*btnNegrito.setOnClickListener(this);
        btnRojito.setOnClickListener(this);
        btnPequenio.setOnClickListener(this);
        btnGrande.setOnClickListener(this);*/

        btnNegrito.setOnCheckedChangeListener(this);
        btnRojito.setOnCheckedChangeListener(this);
        btnPequenio.setOnCheckedChangeListener(this);
        btnGrande.setOnCheckedChangeListener(this);

    }

    public void onCheckBoxClicked(View v)
    {
        switch (v.getId())
        {
            case R.id.btnChiquitita:
                btnGrande.setChecked(false);
                break;

            case R.id.btnGrandecita:
                btnPequenio.setChecked(false);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId())
        {
            case R.id.btnNegrita:
                if(isChecked)
                {
                    cuadro.setTypeface(cuadro.getTypeface(), Typeface.BOLD);
                }
                else
                {
                    cuadro.setTypeface(Typeface.create(cuadro.getTypeface(), Typeface.NORMAL)); //investigar como poner texto bold a normal
                }
                break;

            case R.id.btnRojita:
                if(isChecked)
                {
                    cuadro.setTextColor(Color.RED);
                }
                else
                {
                    cuadro.setTextColor(Color.BLACK);
                }
                break;

            case R.id.btnChiquitita:
                if(isChecked)
                {
                    cuadro.setTextSize(TypedValue.COMPLEX_UNIT_DIP, cuadro.getTextSize()-15);
                }
                else
                {
                    cuadro.setTextSize(TypedValue.COMPLEX_UNIT_DIP, cuadro.getTextSize()+15);
                }
                break;

            case R.id.btnGrandecita:
                if(isChecked)
                {
                    cuadro.setTextSize(TypedValue.COMPLEX_UNIT_DIP, cuadro.getTextSize()+15);
                }
                else
                {
                    cuadro.setTextSize(TypedValue.COMPLEX_UNIT_DIP, cuadro.getTextSize()-15);
                }
                break;
        }
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
