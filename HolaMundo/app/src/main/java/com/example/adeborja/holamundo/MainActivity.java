package com.example.adeborja.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) //Viene a ser el main
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Especifica el archivo layout xml

        btn1 = (Button)findViewById(R.id.button33); //Hay que hacer casting porque el boton no es view, pero hereda de view

        //updateTime();
    }

    public void miMetodo(View V)
    {
        updateTime();
    }

    private void updateTime()
    {
        btn1.setText(new Date().toString());
    }
}
