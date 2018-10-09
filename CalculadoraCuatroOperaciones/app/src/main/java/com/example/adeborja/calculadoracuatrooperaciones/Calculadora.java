package com.example.adeborja.calculadoracuatrooperaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Calculadora extends AppCompatActivity implements View.OnClickListener {

    EditText campo1, campo2;
    TextView campoSimbolo, campoResultado;
    RadioButton btnSuma, btnResta, btnMultiplica, btnDivide;
    RadioGroup grupoBotones;
    Button btnCalcula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        campo1 = (EditText) findViewById(R.id.edtxtNumero1);
        campo2 = (EditText) findViewById(R.id.edtxtNumero2);
        campoSimbolo = (TextView) findViewById(R.id.txtvwSimbolo);
        campoResultado = (TextView) findViewById(R.id.txtvwResultado);
        btnCalcula = (Button) findViewById(R.id.btnCalcular);

        grupoBotones = (RadioGroup) findViewById(R.id.grupoOperaciones);
        btnSuma = (RadioButton) findViewById(R.id.rdbtnSumar);
        btnResta = (RadioButton) findViewById(R.id.rdbtnRestar);
        btnMultiplica = (RadioButton) findViewById(R.id.rdbtnMultiplicar);
        btnDivide = (RadioButton) findViewById(R.id.rdbtnDividir);

        btnCalcula.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int num1, num2, btnId;
        String aux1, aux2, resultado="";

        aux1 = campo1.getText().toString();
        aux2 = campo2.getText().toString();

        num1 = Integer.valueOf(aux1);
        num2 = Integer.valueOf(aux2);

        btnId = grupoBotones.getCheckedRadioButtonId();

        switch (btnId)
        {
            case R.id.rdbtnSumar:
                    resultado = sumar(num1,num2);
                campoSimbolo.setText("+");
                break;
            case R.id.rdbtnRestar:
                resultado = restar(num1,num2);
                campoSimbolo.setText("-");
                break;
            case R.id.rdbtnMultiplicar:
                resultado = multiplicar(num1,num2);
                campoSimbolo.setText("*");
                break;
            case R.id.rdbtnDividir:
                resultado = dividir(num1,num2);
                campoSimbolo.setText("/");
                break;
        }

        campoResultado.setText(resultado);
    }

    public String sumar(int numero1, int numero2)
    {
        int auxRes;
        String resultado;

        auxRes = numero1+numero2;

        resultado = String.valueOf(auxRes);

        return resultado;
    }

    public String restar(int numero1, int numero2)
    {
        int auxRes;
        String resultado;

        auxRes = numero1-numero2;

        resultado = String.valueOf(auxRes);

        return resultado;
    }

    public String multiplicar(int numero1, int numero2)
    {
        int auxRes;
        String resultado;

        auxRes = numero1*numero2;

        resultado = String.valueOf(auxRes);

        return resultado;
    }

    public String dividir(int numero1, int numero2)
    {
        int auxRes;
        String resultado;

        auxRes = numero1/numero2;

        resultado = String.valueOf(auxRes);

        return resultado;
    }

}
