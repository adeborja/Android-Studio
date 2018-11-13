package com.example.adeborja.autocompletetxv;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

//implementar textWatcher para que la caja de texto se rellene con las letras que introducimos al mismo tiempo
public class AutoCompleteTxV extends Activity implements TextWatcher {

    private static final String[] PALABRAS = {"hola", "halo", "helado", "hilado", "hondo", "holograma", "hololens", "hiniesta", "hilo", "humo", "hoja", "hojaldre", "hijo", "hija", "hipopotamo", "hermano", "hermana"};

    private AutoCompleteTextView lista;
    private TextView cajaTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_tx_v);

        //Adaptador que define el estilo de la columna que aparece con las opciones de autocompletar
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, PALABRAS);

        cajaTexto = (TextView)findViewById(R.id.txvTexto);
        lista = (AutoCompleteTextView)findViewById(R.id.actvLista);

        //https://www.journaldev.com/9574/android-autocompletetextview-example-tutorial

        //cuantas letras hay que introducir antes de que salgan opciones para autocompletar
        lista.setThreshold(1);

        //fijar el adaptador de estilo
        lista.setAdapter(adaptador);

        //Añadirle a lista un listener para capturar las letras
        lista.addTextChangedListener(this);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        cajaTexto.setText(lista.getText());
    }

    //Metodos que son necesarios implementar pero no completar
    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
