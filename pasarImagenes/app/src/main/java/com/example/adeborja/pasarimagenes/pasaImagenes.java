package com.example.adeborja.pasarimagenes;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class pasaImagenes extends AppCompatActivity implements View.OnClickListener
{

    ImageView patata1, patata2, patata3;
    ArrayList<ImageView> arrayListPatatas = new ArrayList<ImageView>(0);
    //ImageView[] arrayPatatas = new ImageView[3];
    ImageView cuadroFotos;
    //Drawable[] arrayPatatas = new Drawable[3];


    Button btnPrev, btnNext;

    int posicionActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasa_imagenes);

        /*arrayPatatas[0] = (ImageView) findViewById(R.id.cuadroPotatoeyes);
        arrayPatatas[1] = (ImageView) findViewById(R.id.cuadroPotatoface);
        arrayPatatas[2] = (ImageView) findViewById(R.id.cuadroPotatomister);*/

        patata1 = (ImageView) findViewById(R.id.cuadroPotatoeyes);
        patata2 = (ImageView) findViewById(R.id.cuadroPotatoface);
        patata3 = (ImageView) findViewById(R.id.cuadroPotatomister);

        arrayListPatatas.add(patata1);
        arrayListPatatas.add(patata2);
        arrayListPatatas.add(patata3);


        cuadroFotos = (ImageView) findViewById(R.id.cuadroFotografias);

        btnPrev = (Button) findViewById(R.id.btnAnterior);
        btnNext = (Button) findViewById(R.id.btnSiguiente);

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        //cuadroFotos.setImageDrawable(arrayPatatas[0].getDrawable());
        cuadroFotos.setImageDrawable(arrayListPatatas.get(0).getDrawable());
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnAnterior:
                    if (posicionActual!=0)
                    {
                        posicionActual--;
                    }
                    else
                    {
                        //posicionActual = arrayPatatas.length-1;
                        posicionActual = arrayListPatatas.size()-1;
                    }

                    //cuadroFotos.setImageResource(R.drawable.potatoeyes);
                //cuadroFotos.setImageDrawable(arrayPatatas[posicionActual].getDrawable());
                break;

            case R.id.btnSiguiente:

                if (posicionActual==arrayListPatatas.size()-1)
                {
                    posicionActual=0;
                }
                else
                {
                    posicionActual++;
                }

                break;
        }

        //cuadroFotos.setImageDrawable(arrayPatatas[posicionActual].getDrawable());
        cuadroFotos.setImageDrawable(arrayListPatatas.get(posicionActual).getDrawable());
    }


}
