package com.example.adeborja.examenuno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class editarFutbolista extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView Foto;
    private EditText Nombre, EditarNuevaPosicion;
    private TextView PosicionActual, PosicionNueva;
    private Button Borrar, Clonar, Guardar, btnNuevaPosicion, btnGuardarNuevaPosicion;
    private Spinner spin;
    private clsJugadorFutbol jf;

    private Switch switchEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_futbolista);

        Borrar=(Button)findViewById(R.id.btnBorrar);
        Clonar=(Button)findViewById(R.id.btnClonar);
        Guardar=(Button)findViewById(R.id.btnGuardar);
        btnNuevaPosicion=(Button)findViewById(R.id.btnNuevaPosicion);
        btnGuardarNuevaPosicion=(Button)findViewById(R.id.btnGuardarNuevaPosicion);

        Foto = (ImageView)findViewById(R.id.imgFoto);
        Nombre=(EditText)findViewById(R.id.etxNombre);
        PosicionActual=(TextView) findViewById(R.id.txvPosicion);
        PosicionNueva=(TextView) findViewById(R.id.txvNuevaPosicion);

        EditarNuevaPosicion = (EditText)findViewById(R.id.etxNuevaPosicion);
        switchEditar=(Switch)findViewById(R.id.swtEditar);
        switchEditar.setChecked(false);

        spin = (Spinner)findViewById(R.id.spinner);

        spin.setOnItemSelectedListener(this);

        Intent i = getIntent();

        mostrarIntentRecibido(i);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jf.getPosiciones());

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adaptador);

        btnNuevaPosicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarControlesNuevaPosicion();
            }
        });
        
        switchEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivarCampos();
            }
        });

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarJugador();
            }
        });

        Borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarJugador();
            }
        });

        Clonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clonarJugador();
            }
        });

        switchActivarCampos();
    }

    private void guardarJugador() {
        Intent i = new Intent(this, ExamenUno.class);
        i.putExtra("jugador_f", jf);
        i.setPackage("guardar_f");
        startActivity(i);
    }

    private void clonarJugador() {
        Intent i = new Intent(this, ExamenUno.class);
        i.putExtra("jugador_f", jf);
        startActivity(i);
    }

    private void borrarJugador() {
        Intent i = new Intent(this, ExamenUno.class);
        i.putExtra("jugador_f", jf);
        startActivity(i);
    }



    private void switchActivarCampos() {

        Nombre.setEnabled(!Nombre.isEnabled());
        Foto.setEnabled(!Foto.isEnabled());
        btnNuevaPosicion.setEnabled(!btnNuevaPosicion.isEnabled());
        spin.setEnabled(!spin.isEnabled());

        if(btnGuardarNuevaPosicion.getVisibility()== View.VISIBLE)
        {
            mostrarControlesNuevaPosicion();
        }
    }

    private void mostrarControlesNuevaPosicion() {

        if(btnGuardarNuevaPosicion.getVisibility()== View.INVISIBLE)
        {
            /*btnGuardarNuevaPosicion.setVisibility(View.VISIBLE);
            PosicionNueva.setVisibility(View.VISIBLE);
            EditarNuevaPosicion.setVisibility(View.VISIBLE);*/
            if(switchEditar.isChecked())
            {
                btnGuardarNuevaPosicion.setVisibility(View.VISIBLE);
                PosicionNueva.setVisibility(View.VISIBLE);
                EditarNuevaPosicion.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            btnGuardarNuevaPosicion.setVisibility(View.INVISIBLE);
            PosicionNueva.setVisibility(View.INVISIBLE);
            EditarNuevaPosicion.setVisibility(View.INVISIBLE);
        }

    }

    private void mostrarIntentRecibido(Intent i) {

        jf = i.getParcelableExtra("jugador");
        Foto.setImageResource(jf.getFoto());
        Nombre.setText(jf.getNombre());
        PosicionActual.setText(jf.getPosicionActual());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String pos = jf.getPosiciones()[i];
        PosicionActual.setText(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
