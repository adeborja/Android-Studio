package com.example.david.sharedpreferencesuno;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "texto a mostrar";
    public static final String VECES_SWITCH = "0";
    public static final String SWITCH1 = "switch1";

    TextView txvTexto, txvSwitchTxt, txvSwitchVeces;
    EditText etxTexto;
    Button btnTexto, btnGuardar;
    Switch switch1;

    int veces_switch;
    String texto;
    boolean switchEstado;

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
                txvTexto.setText(etxTexto.getText().toString());
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                aumentarMarcadorSwitch();
            }
        });

        cargarDatos();
        actualizarRegistros();
    }



    private void aumentarMarcadorSwitch() {

        veces_switch++;
        txvSwitchVeces.setText(String.valueOf(veces_switch));

    }

    private void guardarDatos() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TEXT, txvTexto.getText().toString());
        editor.putBoolean(SWITCH1, switch1.isChecked());
        editor.putInt(VECES_SWITCH, veces_switch);

        editor.apply();

        Toast.makeText(this,"Datos guardados", Toast.LENGTH_SHORT).show();

    }

    private void cargarDatos() {

        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        texto = sp.getString(TEXT, "");
        switchEstado = sp.getBoolean(SWITCH1, false);
        veces_switch = sp.getInt(VECES_SWITCH, 0);

    }

    private void actualizarRegistros() {
        txvTexto.setText(texto);
        switch1.setChecked(switchEstado);
        txvSwitchVeces.setText(String.valueOf(veces_switch));
    }
}
