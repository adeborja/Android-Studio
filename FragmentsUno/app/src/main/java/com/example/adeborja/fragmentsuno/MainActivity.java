package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Navegacion.OnFragmentInteractionListener {

    ViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View contenedorPantallaCompleta;

        //para utilizar ViewModelProviders es necesario añadirlo en el gradle de module:app
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        contenedorPantallaCompleta = findViewById(R.id.contenedorPantallaCompleta);

        if(contenedorPantallaCompleta == null)
        {
            ((MainViewModel) mainViewModel).setTablet(true);
        }
        else
        {
            Navegacion frag = new Navegacion();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedorPantallaCompleta, frag)
                    .addToBackStack(null)
                    .commit();

            ((MainViewModel) mainViewModel).setTablet(false);
        }
    }

    @Override
    public void onFragmentInteraction(View v)
    {

        //Añadir aqui lo que tiene que aparecer al entrar en el fragment de las imagenes

        String texto = "";
        switch (v.getId())
        {
            case R.id.boton1:
                texto = "Has pulsado el boton 1";
                break;
            case R.id.boton2:
                texto = "HAS PULSADO EL BOTON DOS";
                break;

        }


        ImagenesFragment frag = ImagenesFragment.newInstance(texto);

        if(((MainViewModel) mainViewModel).isTablet())
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.barraImagenes, frag)
                    .addToBackStack(null)
                    .commit();
        }
        else
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedorPantallaCompleta, frag)
                    .addToBackStack(null)
                    .commit();
        }

    }
}
