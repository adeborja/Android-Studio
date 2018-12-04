package com.example.adeborja.viewmodelpuntos;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VmPuntos extends AppCompatActivity {

    private ViewModelPuntos mViewModel;
    TextView puntosA, puntosB;
    Button btnSumaA, btnRestaA, btnSumaB, btnRestaB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vm_puntos);

        puntosA = (TextView)findViewById(R.id.txvPuntosA);
        puntosB = (TextView)findViewById(R.id.txvPuntosB);
        btnSumaA = (Button)findViewById(R.id.btnSumarA);
        btnSumaB = (Button)findViewById(R.id.btnSumarB);
        btnRestaA = (Button)findViewById(R.id.btnRestarA);
        btnRestaB = (Button)findViewById(R.id.btnRestarB);

        mViewModel = ViewModelProvider.of(this).get(ViewModelPuntos.class);

        puntosA.setText(mViewModel.getPuntosA());
        puntosB.setText(mViewModel.getPuntosB());

        btnSumaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setPuntosA(mViewModel.getPuntosA()+1);
            }
        });

        btnSumaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setPuntosB(mViewModel.getPuntosB()+1);
            }
        });

        btnRestaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setPuntosA(mViewModel.getPuntosA()-1);
            }
        });

        btnRestaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.setPuntosB(mViewModel.getPuntosB()-1);
            }
        });


    }


}
