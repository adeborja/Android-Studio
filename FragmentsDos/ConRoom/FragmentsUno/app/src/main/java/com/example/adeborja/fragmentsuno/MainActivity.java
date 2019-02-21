package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Navegacion.OnFragmentInteractionListener,
        DetallesFragment.OnFragmentInteractionListener, FragmentManager.OnBackStackChangedListener {

    public static ViewModel mainViewModel;
    ConstraintLayout lineaBotones;
    //Button btnCrear, btnListar;
    View contenedorPantallaCompleta;

    BottomNavigationView bottomNavigationView;

    //TextView aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //t odo lo de aquí que sea del listview FUERA, tiene que estar en la clase Navegacion



        //View contenedorPantallaCompleta;
        /*lineaBotones = (ConstraintLayout) findViewById(R.id.linBotones);
        btnCrear = (Button)findViewById(R.id.btnCrear);
        btnListar = (Button)findViewById(R.id.btnListar);*/

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        //btnListar.setOnClickListener(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        bottomNavigationView.getMenu().getItem(0).setEnabled(true);
        bottomNavigationView.getMenu().getItem(1).setEnabled(false);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        bottomNavigationView.getMenu().getItem(2).setVisible(false);

        //para utilizar ViewModelProviders es necesario añadirlo en el gradle de module:app
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //((MainViewModel) mainViewModel).rellenarLista();

        contenedorPantallaCompleta = findViewById(R.id.contenedorPantallaCompleta);

        //getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        //aux = findViewById(R.id.aux);

        if(contenedorPantallaCompleta == null)
        {
            ((MainViewModel) mainViewModel).setTablet(true);
        }
        else
        {
            Navegacion frag = Navegacion.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedorPantallaCompleta, frag)
                    //.addToBackStack(null)
                    .commit();

            ((MainViewModel) mainViewModel).setTablet(false);

            /*int aux = getSupportFragmentManager().getBackStackEntryCount();

            if(aux>0)
            {
                getSupportFragmentManager().popBackStack(getSupportFragmentManager()
                        .getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }*/

            //getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }



        //desactivar botones
        //btnCrear.setEnabled(false);

        //aux.setText(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            //Fragment f = null;

            switch (menuItem.getItemId())
            {
                case R.id.nav_lista_personajes:

                    Navegacion frag = Navegacion.newInstance();

                    if(contenedorPantallaCompleta == null)
                    {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.barraImagenes, frag)
                                .addToBackStack(null)
                                .commit();
                    }
                    else
                    {
                        /*getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedorPantallaCompleta, frag)
                                //.addToBackStack(null)
                                .commit();*/

                        //Vuelve a la lista de personajes y borra el historial de vistas, haciendo
                        //que el backstack del programa esté como cuando está recién ejecutado
                        getSupportFragmentManager().popBackStack(getSupportFragmentManager()
                                .getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);


                    }

                    bottomNavigationView.getMenu().getItem(1).setEnabled(false);

                    break;

                case R.id.nav_crear_personaje:

                    CrearFragment frag2 = new CrearFragment();

                    if(contenedorPantallaCompleta == null)
                    {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.barraImagenes, frag2)
                                .addToBackStack(null)
                                .commit();
                    }
                    else
                    {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedorPantallaCompleta, frag2)
                                .addToBackStack(null)
                                .commit();


                    }

                    bottomNavigationView.getMenu().getItem(1).setEnabled(true);

                    break;
            }

            //aux.setText(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));

            return true;
        }
    };

    /*@Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnListar:

                Navegacion frag = Navegacion.newInstance();

                //btnCrear.setEnabled(true);
                //btnListar.setEnabled(false);

                if(contenedorPantallaCompleta == null)
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

                //Vuelve a la lista de personajes y borra el historial de vistas, haciendo
                //que el backstack del programa esté como cuando está recién ejecutado
                getSupportFragmentManager().popBackStack(getSupportFragmentManager()
                        .getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

                break;
        }
    }*/

    @Override
    public void onBackStackChanged() {
        /*Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_detalles);
        Toast.makeText(getApplicationContext(),"onCreate", Toast.LENGTH_SHORT).show();*/

        int i = getSupportFragmentManager().getBackStackEntryCount();

        if(i<1)
        {
            bottomNavigationView.getMenu().getItem(0).setEnabled(true);
            bottomNavigationView.getMenu().getItem(1).setEnabled(false);
            //bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        }
        else
        {
            bottomNavigationView.getMenu().getItem(1).setEnabled(true);
            //bottomNavigationView.getMenu().getItem(2).setEnabled(true);
        }

        //aux.setText(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
    }

    @Override
    public void onDetFragmentInteraction(int id)
    {
        //este habria que modificar el nombre o borrarlo, ya que cada fragment va a implementar
        //esta interface con nombre distinto, que tendra que ser implementado en esta clase
        //TODO: modificar, llamar a cada metodo que debe implementar cada fragment de forma distinta. Se pueden modificar los parametros a pasar para incluir lo que nosotros queramos usar.

        //Toast.makeText(this,"Has pulsado el boton de id "+id,Toast.LENGTH_SHORT).show();

        Personaje p = ((MainViewModel) mainViewModel).getPersonajePorId(id);

        ImagenesFragment frag = ImagenesFragment.newInstance(p.getImagenes());

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

            //hacer desaparecer la barra de botones
            //lineaBotones.setVisibility(View.GONE);
            bottomNavigationView.setVisibility(View.GONE);
        }

        //aux.setText(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        //if(!((MainViewModel) mainViewModel).isTablet() && lineaBotones.getVisibility()==View.GONE)
        //{
            //lineaBotones.setVisibility(View.VISIBLE);
        bottomNavigationView.setVisibility(View.VISIBLE);
        //}

        //aux.setText(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
    }


    @Override
    public void onNavFragmentInteraction(int posicion)
    {
        Personaje p = ((MainViewModel) mainViewModel).getPersonaje(posicion);

        DetallesFragment frag = DetallesFragment.newInstance(p);

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

        //activar botones
        //btnCrear.setEnabled(true);

        //aux.setText(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    /*@Override
    public void OnImagFragmentInteraction(int posicion) {

    }*/
}
