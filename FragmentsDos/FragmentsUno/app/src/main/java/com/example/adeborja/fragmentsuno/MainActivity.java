package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Navegacion.OnFragmentInteractionListener, DetallesFragment.OnFragmentInteractionListener {

    public static ViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: todo lo de aquí que sea del listview FUERA, tiene que estar en la clase Navegacion



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

        //TODO: modificar, llamar a cada metodo que debe implementar cada fragment de forma distinta. Se pueden modificar los parametros a pasar para incluir lo que nosotros queramos usar.

        String nombre = "pNombre", alias = "pAlias", desc = "pDesc", retrato = String.valueOf(R.drawable.ic_launcher_background);

        DetallesFragment frag = DetallesFragment.newInstance(nombre,alias, desc, retrato);

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


        //Toast.makeText(this,"onFragmentInteraction MAIN", Toast.LENGTH_SHORT);

        //Añadir aqui lo que tiene que aparecer al entrar en el fragment de las imagenes

        /*String texto = "";
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
        }*/

    }


}
