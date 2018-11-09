package com.example.adeborja.autocompletetextview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class AutoCompleteTextView extends Activity {

    private static final String[] PALABRAS = {hola, halo, helado, hilado, hondo, holograma, hololens, hiniesta, hilo, humo, hoja, hojaldre, hijo, hija, hipopotamo};

    AutoCompleteTextView cajaTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, PALABRAS);

        cajaTexto = (AutoCompleteTextView)findViewById(R.id.actvLista);

        //https://www.journaldev.com/9574/android-autocompletetextview-example-tutorial
    }
}
