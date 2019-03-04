package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
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

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Navegacion.OnFragmentInteractionListener,
        FragmentManager.OnBackStackChangedListener,
        CrearFragment.OnFragmentInteractionListener, EditarFragment.OnFragmentInteractionListener {

    public static MainViewModel mainViewModel;
    View contenedorPantallaCompleta;

    BottomNavigationView bottomNavigationView;

    static miBaseDatos myBaseDatos;

    //Esta variable se utiliza para evitar la destruccion de vistas al girar la pantalla
    static boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        bottomNavigationView.getMenu().getItem(0).setEnabled(true);
        bottomNavigationView.getMenu().getItem(1).setEnabled(false);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);
        bottomNavigationView.getMenu().getItem(4).setEnabled(false);

        myBaseDatos = miBaseDatos.getInstance(this);

        //para utilizar ViewModelProviders es necesario añadirlo en el gradle de module:app
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setContext(this);

        contenedorPantallaCompleta = findViewById(R.id.contenedorPantallaCompleta);

        if(flag)
        {

            if(contenedorPantallaCompleta == null)
            {
                mainViewModel.setTablet(true);
                bottomNavigationView.getMenu().getItem(1).setVisible(false);
            }
            else
            {
                Navegacion frag = Navegacion.newInstance();

                int aux2 = getSupportFragmentManager().getBackStackEntryCount();

                if(aux2>0)
                {
                    getSupportFragmentManager().popBackStack(getSupportFragmentManager()
                            .getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedorPantallaCompleta, frag)
                        .commit();

                mainViewModel.setTablet(false);
            }
        }
        else
        {
            bottomNavigationView.getMenu().getItem(1).setEnabled(true);
        }
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Personaje pers;

            switch (menuItem.getItemId())
            {
                case R.id.nav_lista_personajes:

                        iniciarListaPrincipal();

                    break;

                case R.id.nav_crear_personaje:

                    CrearFragment frag2 = CrearFragment.newInstance();

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

                    bottomNavigationView.getMenu().getItem(0).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(2).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(3).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(4).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(1).setEnabled(true);

                    break;

                case R.id.nav_galeria:

                    pers = mainViewModel.getPersonajeSeleccionado();

                    ImagenesFragment fragx = ImagenesFragment.newInstance(pers.getListImagenes());

                    if(contenedorPantallaCompleta == null)
                    {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.barraImagenes, fragx)
                                .addToBackStack(null)
                                .commit();
                    }
                    else
                    {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedorPantallaCompleta, fragx)
                                .addToBackStack(null)
                                .commit();
                    }

                    bottomNavigationView.setVisibility(View.GONE);

                    break;

                case R.id.nav_editar_personaje:

                    pers = mainViewModel.getPersonajeSeleccionado();

                    bottomNavigationView.getMenu().getItem(0).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(2).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(3).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(4).setEnabled(false);

                    EditarFragment frag3 = EditarFragment.newInstance(pers);

                    if(contenedorPantallaCompleta == null)
                    {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.barraImagenes, frag3)
                                .addToBackStack(null)
                                .commit();
                    }
                    else
                    {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedorPantallaCompleta, frag3)
                                .addToBackStack(null)
                                .commit();
                    }

                    break;

                case R.id.nav_borrar_personaje:


                    new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.cuadro_borrar_personaje_titulo)
                        .setMessage(R.string.cuadro_borrar_personaje_desc)
                        .setPositiveButton(R.string.cuadro_borrar_personaje_aceptar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Personaje p = mainViewModel.getPersonajeSeleccionado();

                                borrarPersonaje(p);

                                iniciarListaPrincipal();
                            }
                        })
                        .setNegativeButton(R.string.cuadro_borrar_personaje_cancelar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();

                    break;
            }

            return true;
        }
    };

    @Override
    public void onBackStackChanged() {

        //TODO: revisar
        /*int i = getSupportFragmentManager().getBackStackEntryCount();

        if(i<1)
        {
            //TODO: mirar que cuando se entra en un perfil, se da a crear y se vuelve atras, aparezca de nuevo
            //habilitado el boton de crear
            bottomNavigationView.getMenu().getItem(0).setEnabled(true);
            bottomNavigationView.getMenu().getItem(1).setEnabled(false);
            bottomNavigationView.getMenu().getItem(2).setEnabled(false);
            bottomNavigationView.getMenu().getItem(3).setEnabled(false);
            bottomNavigationView.getMenu().getItem(4).setEnabled(false);
            //bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        }
        else
        {
            bottomNavigationView.getMenu().getItem(1).setEnabled(true);
            //bottomNavigationView.getMenu().getItem(2).setEnabled(true);
        }*/

        //aux.setText(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
    }

    /*@Override
    public void onDetFragmentInteraction()
    {
        Personaje p = mainViewModel.getPersonajeSeleccionado();

        ImagenesFragment frag = ImagenesFragment.newInstance(p.getListImagenes()); //getImagenes());

        if(mainViewModel.isTablet())
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
    }*/

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        bottomNavigationView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onNavFragmentInteraction(Personaje p)
    {
        mainViewModel.setPersonajeSeleccionado(p);

        DetallesFragment frag = DetallesFragment.newInstance(p);

        if(mainViewModel.isTablet())
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.barraImagenes, frag)
                    .addToBackStack(null)
                    .commit();
            bottomNavigationView.getMenu().getItem(0).setEnabled(true);
        }
        else
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedorPantallaCompleta, frag)
                    .addToBackStack(null)
                    .commit();

            bottomNavigationView.getMenu().getItem(0).setEnabled(false);
            bottomNavigationView.getMenu().getItem(1).setEnabled(true);
        }

        if(p.getCantidadImagenes()==0)
        {
            bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        }
        else
        {
            bottomNavigationView.getMenu().getItem(2).setEnabled(true);
        }

        bottomNavigationView.getMenu().getItem(3).setEnabled(true);
        bottomNavigationView.getMenu().getItem(4).setEnabled(true);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    /*@Override
    public void OnImagFragmentInteraction(int posicion) {

    }*/

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        flag = false;
        super.onConfigurationChanged(newConfig);
    }

    public void iniciarListaPrincipal()
    {
        if(contenedorPantallaCompleta == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.barraImagenes, new Fragment())
                    .commit();
        }
        else
        {
            Navegacion frag = Navegacion.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedorPantallaCompleta, frag)
                    .commit();
        }

        bottomNavigationView.getMenu().getItem(0).setEnabled(true);
        bottomNavigationView.getMenu().getItem(1).setEnabled(false);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);
        bottomNavigationView.getMenu().getItem(4).setEnabled(false);
    }

    @Override
    public void onCrearPersFragmentInteraction(String nombre, String alias, String desc, Uri retrato, List<String> imagenes)
    {
        Personaje p = new Personaje(nombre, alias, desc, retrato, imagenes, 0); //Dejar id en 0 para que se autogenere

        mainViewModel.insert(p);

        Toast.makeText(this,"Personaje creado", Toast.LENGTH_SHORT).show();

        iniciarListaPrincipal();
    }

    public void borrarPersonaje(Personaje p)
    {
        mainViewModel.delete(p);

        Toast.makeText(this,""+p.getAlias()+" ha sido borrado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditPersFragmentInteraction(String nombre, String alias, String desc, Uri retrato, List<String> imagenes, long id)
    {
        Personaje p = new Personaje(nombre, alias, desc, retrato, imagenes, id);

        mainViewModel.update(p);

        Toast.makeText(this,""+p.getAlias()+" ha sido actualizado", Toast.LENGTH_SHORT).show();

        bottomNavigationView.getMenu().getItem(0).setEnabled(true);
        bottomNavigationView.getMenu().getItem(1).setEnabled(false);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);
        bottomNavigationView.getMenu().getItem(4).setEnabled(false);

        iniciarListaPrincipal();
    }
}
