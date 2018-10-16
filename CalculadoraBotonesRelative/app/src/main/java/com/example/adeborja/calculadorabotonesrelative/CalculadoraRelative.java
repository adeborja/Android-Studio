package com.example.adeborja.calculadorabotonesrelative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CalculadoraRelative extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnComa;
    Button btnSuma, btnResta, btnMultiplica, btnDivide, btnRaiz2, btnPotencia2, btnUnoDivideNumero, btnIgual, btnBorrar;
    TextView txvTeclasPulsadas, txvResultado;
    LinearLayout cjBoton;

    boolean borrarPantallaResultado=false, botonCalcularPulsado=false;
    String ultimaTeclaOperacion="";
    String ultimaOperacion="";

    int altoPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_relative);
        //onWindowFocusChanged(true); //para obtener el alto de la pantalla

        //Cajas de texto
        txvResultado = (TextView) findViewById(R.id.txvResultado);
        txvTeclasPulsadas = (TextView) findViewById(R.id.txvTeclasPulsadas);

        //Botones de operaciones
        //cjBoton = (LinearLayout) findViewById(R.id.lnlCajaBotones); no se puede simplemente usar el contenedor, hay que instanciar los elementos
        btnIgual = (Button) findViewById(R.id.btnCalcular);
        btnSuma = (Button) findViewById(R.id.btnSumar);
        btnResta = (Button) findViewById(R.id.btnRestar);
        btnMultiplica = (Button) findViewById(R.id.btnMultiplicar);
        btnDivide = (Button) findViewById(R.id.btnDividir);
        btnRaiz2 = (Button) findViewById(R.id.btnRaizCuadrada);
        btnPotencia2 = (Button) findViewById(R.id.btnAlCuadrado);
        btnUnoDivideNumero = (Button) findViewById(R.id.btnUnoEntreNumero);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);

        //cjBoton.setOnClickListener(this);
        btnIgual.setOnClickListener(this);
        btnSuma.setOnClickListener(this);
        btnResta.setOnClickListener(this);
        btnMultiplica.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnRaiz2.setOnClickListener(this);
        btnPotencia2.setOnClickListener(this);
        btnUnoDivideNumero.setOnClickListener(this);
        btnBorrar.setOnClickListener(this);

        //Botones numericos
        btn1 = (Button) findViewById(R.id.btnUno);
        btn2 = (Button) findViewById(R.id.btnDos);
        btn3 = (Button) findViewById(R.id.btnTres);
        btn4 = (Button) findViewById(R.id.btnCuatro);
        btn5 = (Button) findViewById(R.id.btnCinco);
        btn6 = (Button) findViewById(R.id.btnSeis);
        btn7 = (Button) findViewById(R.id.btnSiete);
        btn8 = (Button) findViewById(R.id.btnOcho);
        btn9 = (Button) findViewById(R.id.btnNueve);
        btn0 = (Button) findViewById(R.id.btnCero);
        btnComa = (Button) findViewById(R.id.btnComa);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnComa.setOnClickListener(this);

        //Caja botones
        cjBoton = (LinearLayout) findViewById(R.id.lnlCajaBotones);

    }

    @Override
    public void onClick(View v)
    {
        if(borrarPantallaResultado)
        {
            txvResultado.setText("");
            borrarPantallaResultado = false;
        }

        switch (v.getId())
        {
            //Botones de operaciones
            case R.id.btnCalcular:
                if(botonCalcularPulsado)
                {
                    //repetir ultima operacion
                }
                else
                {
                    txvTeclasPulsadas.setText(txvTeclasPulsadas.getText()+""+txvResultado.getText());
                    ultimaOperacion = txvTeclasPulsadas.getText().toString();
                    calcular();
                    botonCalcularPulsado = true;
                    txvTeclasPulsadas.setText("");
                }
                break;

            case R.id.btnBorrar:
                txvTeclasPulsadas.setText("");
                txvResultado.setText("");
                borrarPantallaResultado=false;
                botonCalcularPulsado=false;
                ultimaTeclaOperacion="";
                break;

            case R.id.btnSumar:
                //if(!botonCalcularPulsado)
                //{
                //Si no se ha introducido nada en txvTeclasPulsadas
                if (txvTeclasPulsadas.getText().toString().isEmpty()) {
                    txvTeclasPulsadas.setText(txvResultado.getText() + "" + btnSuma.getText());
                    borrarPantallaResultado = true;
                } else {
                    boolean auxNumerico = ultimoCaracterNumerico();
                    if(auxNumerico)
                    {
                        txvTeclasPulsadas.setText(txvTeclasPulsadas.getText() + "" + txvResultado.getText() + "" + btnSuma.getText());
                        borrarPantallaResultado = true;
                    }
                }

                ultimaTeclaOperacion = btnSuma.getText().toString();
                //}

                break;

            case R.id.btnRestar:
                //Si no se ha introducido nada en txvTeclasPulsadas
                if (txvTeclasPulsadas.getText().toString().isEmpty()) {
                    txvTeclasPulsadas.setText(txvResultado.getText() + "" + btnResta.getText());
                    borrarPantallaResultado = true;
                } else {
                    boolean auxNumerico = ultimoCaracterNumerico();
                    if(auxNumerico)
                    {
                        txvTeclasPulsadas.setText(txvTeclasPulsadas.getText() + "" + txvResultado.getText() + "" + btnResta.getText());
                        borrarPantallaResultado = true;
                    }
                }

                ultimaTeclaOperacion = btnResta.getText().toString();

                break;

            case R.id.btnMultiplicar:
                //Si no se ha introducido nada en txvTeclasPulsadas
                if (txvTeclasPulsadas.getText().toString().isEmpty()) {
                    txvTeclasPulsadas.setText(txvResultado.getText() + "" + btnMultiplica.getText());
                    borrarPantallaResultado = true;
                } else {
                    boolean auxNumerico = ultimoCaracterNumerico();
                    if(auxNumerico)
                    {
                        txvTeclasPulsadas.setText(txvTeclasPulsadas.getText() + "" + txvResultado.getText() + "" + btnMultiplica.getText());
                        borrarPantallaResultado = true;
                    }
                }

                ultimaTeclaOperacion = btnMultiplica.getText().toString();

                break;

            case R.id.btnDividir:
                //Si no se ha introducido nada en txvTeclasPulsadas
                if (txvTeclasPulsadas.getText().toString().isEmpty()) {
                    txvTeclasPulsadas.setText(txvResultado.getText() + "" + btnDivide.getText());
                    borrarPantallaResultado = true;
                } else {
                    boolean auxNumerico = ultimoCaracterNumerico();
                    if(auxNumerico)
                    {
                        txvTeclasPulsadas.setText(txvTeclasPulsadas.getText() + "" + txvResultado.getText() + "" + btnDivide.getText());
                        borrarPantallaResultado = true;
                    }
                }

                ultimaTeclaOperacion = btnDivide.getText().toString();

                break;

            case R.id.btnAlCuadrado:

                //double auxNumero = Double.parseDouble(txvResultado.getText().toString());

                //Simbolo: C
                //txvResultado.setText(txvResultado.getText().toString()+"C");
                //String auxxxx=txvResultado.getText().toString();
                ultimaTeclaOperacion = "C";

                calcular();
                botonCalcularPulsado = true;
                break;

            case R.id.btnRaizCuadrada:

                ultimaTeclaOperacion = "R";

                calcular();
                botonCalcularPulsado = true;
                break;

            case R.id.btnUnoEntreNumero:

                ultimaTeclaOperacion = "U";

                calcular();
                botonCalcularPulsado = true;
                break;

            //Botones numericos
            case R.id.btnComa:
                if(txvResultado.getText().toString().length()>0)
                {
                    txvResultado.setText(txvResultado.getText()+".");
                }
                break;

            case R.id.btnCero:
                if(botonCalcularPulsado)
                {
                    botonCalcularPulsado=false;
                }
                txvResultado.setText(txvResultado.getText()+"0");
                break;

            case R.id.btnUno:
                if(botonCalcularPulsado)
                {
                    botonCalcularPulsado=false;
                }
                txvResultado.setText(txvResultado.getText()+"1");
                break;

            case R.id.btnDos:
                if(botonCalcularPulsado)
                {
                    botonCalcularPulsado=false;
                }
                txvResultado.setText(txvResultado.getText()+"2");
                break;

            case R.id.btnTres:
                if(botonCalcularPulsado)
                {
                    botonCalcularPulsado=false;
                }
                txvResultado.setText(txvResultado.getText()+"3");
                break;

            case R.id.btnCuatro:
                if(botonCalcularPulsado)
                {
                    botonCalcularPulsado=false;
                }
                txvResultado.setText(txvResultado.getText()+"4");
                break;

            case R.id.btnCinco:
                if(botonCalcularPulsado)
                {
                    botonCalcularPulsado=false;
                }
                txvResultado.setText(txvResultado.getText()+"5");
                break;

            case R.id.btnSeis:
                if(botonCalcularPulsado)
                {
                    botonCalcularPulsado=false;
                }
                txvResultado.setText(txvResultado.getText()+"6");
                break;

            case R.id.btnSiete:
                if(botonCalcularPulsado)
                {
                    botonCalcularPulsado=false;
                }
                txvResultado.setText(txvResultado.getText()+"7");
                break;

            case R.id.btnOcho:
                if(botonCalcularPulsado)
                {
                    botonCalcularPulsado=false;
                }
                txvResultado.setText(txvResultado.getText()+"8");
                break;

            case R.id.btnNueve:
                if(botonCalcularPulsado)
                {
                    botonCalcularPulsado=false;
                }
                txvResultado.setText(txvResultado.getText()+"9");
                break;



            /*default:
                txvResultado.setText("Otro boton");
                break;*/
        }
    }

    public void calcular()
    {
        double auxResultado=0, auxNumero1, auxNumero2=0;
        String numero1="", numero2="", aux="";
        char simbolo=' ';
        boolean cambiarNumero=false;

        /*
        inicio
        si la barra de operacion esta vacia
            repetir ultimo calculo
        sino
            realizar operacion de la barra
        fin si
         */

        if(txvTeclasPulsadas.getText().toString().isEmpty())
        {
            //numero1 = txvResultado.getText().toString();
            auxNumero1 = Double.parseDouble(txvResultado.getText().toString());

            switch (ultimaTeclaOperacion)
            {
                case "C":
                    auxResultado = alCuadrado(auxNumero1);
                    break;
                case "R":
                    auxResultado = raizCuadrada(auxNumero1);
                    break;
                case "U":
                    auxResultado = unoEntreNumero(auxNumero1);
                    break;
            }
        }
        else
        {
            aux = txvTeclasPulsadas.getText().toString();

            //poner los numeros en su sitio
            for(int i=0;i<aux.length();i++)
            {
                if(Character.isDigit(aux.charAt(i)) || aux.charAt(i)=='.')
                {
                    if(!cambiarNumero)
                    {
                        numero1 = numero1+aux.charAt(i);
                    }
                    else
                    {
                        numero2 = numero2+aux.charAt(i);
                    }
                }
                else
                {
                    simbolo = aux.charAt(i);
                    cambiarNumero = true;
                }
            }

            auxNumero1 = Double.parseDouble(numero1);
            try
            {
                auxNumero2 = Double.parseDouble(numero2);
            }
            catch (Exception e){

            }

            switch (simbolo)
            {
                case '+':
                    auxResultado = sumar(auxNumero1,auxNumero2);
                    break;
                case '-':
                    auxResultado = restar(auxNumero1,auxNumero2);
                    break;
                case '*':
                    auxResultado = multiplicar(auxNumero1,auxNumero2);
                    break;
                case '÷':
                    auxResultado = dividir(auxNumero1,auxNumero2);
                    break;
            }
        }

        if(auxResultado%1==0)
        {
            txvResultado.setText(String.valueOf((int)auxResultado));
        }
        else
        {
            txvResultado.setText(String.valueOf(auxResultado));
        }

    }

    public boolean ultimoCaracterNumerico()
    {
        boolean esNumerico=false;
        //(txvTeclasPulsadas.getText().toString().charAt(txvTeclasPulsadas.getText().toString().length()-2))
        if(Character.isDigit(txvTeclasPulsadas.getText().toString().charAt(txvTeclasPulsadas.getText().toString().length()-1)))
        {
            esNumerico=true;
        }

        return esNumerico;
    }

    public double sumar(double num1, double num2)
    {
        double resultado = num1 + num2;

        return resultado;
    }

    public double restar(double num1, double num2)
    {
        double resultado = num1 - num2;

        return resultado;
    }

    public double multiplicar(double num1, double num2)
    {
        double resultado = num1 * num2;

        return resultado;
    }

    public double dividir(double num1, double num2)
    {
        double resultado = num1 / num2;

        return resultado;
    }

    public double alCuadrado(double num1)
    {
        double resultado = num1 * num1;

        return resultado;
    }

    public double raizCuadrada(double num1)
    {
        double resultado = Math.sqrt(num1);

        return resultado;
    }

    public double unoEntreNumero(double num1)
    {
        double resultado = 1 / num1;

        return resultado;
    }

/*
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus == true) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.panelCompleto);
            altoPantalla = layout.getHeight();

            //txvTeclasPulsadas.setText(""+altoPantalla);

            tamanoCajas(altoPantalla);
        }
    }

    public void tamanoCajas(int tamano)
    {
        ViewGroup.LayoutParams params = cjBoton.getLayoutParams();
        int veinte, sesenta;

        veinte = (tamano/100)*40;
        sesenta = tamano-(veinte*2);

        txvTeclasPulsadas.setHeight(veinte);
        txvTeclasPulsadas.setHeight(veinte);

        params.height=sesenta;

        cjBoton.setLayoutParams(params);

        txvTeclasPulsadas.setText(""+veinte);
        txvResultado.setText(""+sesenta);
    }
*/
}
