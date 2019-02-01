package com.example.david.sharedpreferencesuno;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "texto a mostrar";
    public static final String VECES_SWITCH = "0";
    public static final String SWITCH1 = "switch1";

    TextView txvTexto, txvSwitchTxt, txvSwitchVeces;
    EditText etxTexto;
    Button btnTexto, btnGuardar;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvTexto = (TextView) findViewById(R.id.txvTexto);
        txvSwitchTxt = (TextView) findViewById(R.id.txvSwitchTxt);
        txvSwitchVeces = (TextView) findViewById(R.id.txvSwitchVeces);

        etxTexto = (EditText) findViewById(R.id.etxTexto);

        switch1 = (Switch) findViewById(R.id.switch1);

        btnTexto = (Button) findViewById(R.id.btnTexto);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });
    }

    private void guardarDatos() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TEXT, txvTexto.getText().toString());
        editor.putBoolean(SWITCH1, switch1.isChecked());

    }
}
