package com.example.adeborja.viewmodelpuntos;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VmPuntos extends AppCompatActivity {

    private ViewModelPuntos mViewModel;
    private TextView puntosA, puntosB, veces;
    private Button btnSumaA, btnRestaA, btnSumaB, btnRestaB;

    private int auxInt;
    private String auxString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vm_puntos);

        puntosA = (TextView)findViewById(R.id.txvPuntosA);
        puntosB = (TextView)findViewById(R.id.txvPuntosB);
        veces = (TextView)findViewById(R.id.txvVeces);
        btnSumaA = (Button)findViewById(R.id.btnSumarA);
        btnSumaB = (Button)findViewById(R.id.btnSumarB);
        btnRestaA = (Button)findViewById(R.id.btnRestarA);
        btnRestaB = (Button)findViewById(R.id.btnRestarB);

        mViewModel = ViewModelProviders.of(this).get(ViewModelPuntos.class);

        //Crear el observador para el viewModel
        final Observer<Integer> observadorVeces = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {
                veces.setText(String.valueOf(s));
            }
        };

        //Observar el LiveData
        mViewModel.getVecesPulsado().observe(this, observadorVeces);


        //mViewModel.setPuntosA(0);
        //mViewModel.setPuntosB(0);

        puntosA.setText(String.valueOf(mViewModel.getPuntosA()));
        puntosB.setText(String.valueOf(mViewModel.getPuntosB()));

        btnSumaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setPuntosA(mViewModel.getPuntosA()+1);
                modificarPuntosA();
                actualizarVeces();
            }
        });

        btnSumaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setPuntosB(mViewModel.getPuntosB()+1);
                modificarPuntosB();
                actualizarVeces();
            }
        });

        btnRestaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setPuntosA(mViewModel.getPuntosA()-1);
                modificarPuntosA();
                actualizarVeces();
            }
        });

        btnRestaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setPuntosB(mViewModel.getPuntosB()-1);
                modificarPuntosB();
                actualizarVeces();
            }
        });

        //modificarPuntosA();
        //modificarPuntosA();

    }

    public void actualizarVeces()
    {
        auxInt = mViewModel.getVecesPulsado().getValue()+1;
        mViewModel.getVecesPulsado().setValue(auxInt);
    }

    public void modificarPuntosA()
    {
        puntosA.setText(String.valueOf(mViewModel.getPuntosA()));
    }

    public void modificarPuntosB()
    {
        puntosB.setText(String.valueOf(mViewModel.getPuntosB()));
    }


}
