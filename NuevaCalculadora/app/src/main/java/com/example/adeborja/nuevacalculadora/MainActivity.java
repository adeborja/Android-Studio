package com.example.adeborja.nuevacalculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCalculo;
    EditText num1, num2;
    TextView cajaSolucion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText) findViewById(R.id.cajaNumero1);
        num2 = (EditText) findViewById(R.id.cajaNumero2);

        cajaSolucion = (TextView) findViewById(R.id.cajaResultado);

        btnCalculo = (Button) findViewById(R.id.btnCalcular);
        btnCalculo.setOnClickListener(this);
    }

   /* public void calcularSuma(View v) {
        int aux1, aux2, resultado;
        String string1, string2, res;

            string1 = num1.getText().toString();
            string2 = num2.getText().toString();

            aux1 = Integer.parseInt(string1);
            aux2 = Integer.parseInt(string2);

            resultado = aux1 + aux2;

            res = String.valueOf(resultado);

            cajaSolucion.setText(res);


    }*/

    @Override
    public void onClick(View view) {
        int aux1, aux2, resultado;
        String string1, string2, res;

        string1 = num1.getText().toString();
        string2 = num2.getText().toString();

        aux1 = Integer.parseInt(string1);
        aux2 = Integer.parseInt(string2);

        resultado = aux1 + aux2;

        res = String.valueOf(resultado);

        cajaSolucion.setText(res);
    }
}
