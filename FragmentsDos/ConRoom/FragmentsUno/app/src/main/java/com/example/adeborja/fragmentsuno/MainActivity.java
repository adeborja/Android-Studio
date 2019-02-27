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
        DetallesFragment.OnFragmentInteractionListener, FragmentManager.OnBackStackChangedListener,
        CrearFragment.OnFragmentInteractionListener, EditarFragment.OnFragmentInteractionListener {

    public static MainViewModel mainViewModel;
    View contenedorPantallaCompleta;

    BottomNavigationView bottomNavigationView;

    static miBaseDatos myBaseDatos;

    static boolean flag = true;

    LiveData<Personaje> personajeLiveData;
    Observer observerPersonajeLiveData;

    //private static Personaje personajeSeleccionado;

    //TextView aux;

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
        bottomNavigationView.getMenu().getItem(2).setVisible(false);

        //myBaseDatos = Room.databaseBuilder(getApplicationContext(), miBaseDatos.class, "personajesdb").allowMainThreadQueries().build();
        myBaseDatos = miBaseDatos.getInstance(this);

        //para utilizar ViewModelProviders es necesario añadirlo en el gradle de module:app
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.setContext(this);
        //mainViewModel.rellenarLista();
        //((MainViewModel) mainViewModel).obtenerPersonajesDeBaseDatos();

        /*((MainViewModel) mainViewModel).getListaLiveData().observe(this, new Observer<List<Personaje>>() {
            @Override
            public void onChanged(@Nullable List<Personaje> personajes) {
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });*/



        contenedorPantallaCompleta = findViewById(R.id.contenedorPantallaCompleta);

        //aux = findViewById(R.id.aux);



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

            //desactivar botones
            //btnCrear.setEnabled(false);
        }
        else
        {
            //Navegacion nav = (Navegacion) getSupportFragmentManager().findFragmentByTag("Navegacion");

            bottomNavigationView.getMenu().getItem(1).setEnabled(true);
        }

        //aux.setText(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                        //Vuelve a la lista de personajes y borra el historial de vistas, haciendo
                        //que el backstack del programa esté como cuando está recién ejecutado
                        /*getSupportFragmentManager().popBackStack(getSupportFragmentManager()
                                .getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedorPantallaCompleta, frag)
                                .commit();*/

                        iniciarListaPrincipal();
                    }

                    bottomNavigationView.getMenu().getItem(0).setEnabled(true);
                    bottomNavigationView.getMenu().getItem(1).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(3).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(4).setEnabled(false);

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

                    bottomNavigationView.getMenu().getItem(0).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(3).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(4).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(1).setEnabled(true);

                    break;

                case R.id.nav_editar_personaje:
                    //Toast.makeText(getApplicationContext(), "Has pulsado editar", Toast.LENGTH_SHORT).show();

                    Personaje p = mainViewModel.getPersonajeSeleccionado();

                    bottomNavigationView.getMenu().getItem(0).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(3).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(4).setEnabled(false);

                    /*Gson gson = new Gson();
                    String json = gson.toJson(p);

                    Toast.makeText(getApplicationContext(), json, Toast.LENGTH_LONG).show();*/

                    EditarFragment frag3 = EditarFragment.newInstance(p);

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


                    //Toast.makeText(getApplicationContext(), "Has pulsado editar", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.nav_borrar_personaje:


                    new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.cuadro_borrar_personaje_titulo)
                        .setMessage(R.string.cuadro_borrar_personaje_desc)
                        .setPositiveButton(R.string.cuadro_borrar_personaje_aceptar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Personaje p2 = mainViewModel.getPersonajeSeleccionado();

                                borrarPersonaje(p2);

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


                    /*Gson gson2 = new Gson();
                    String json2 = "";
                    ListaImagenes img = p2.getImagenes();

                    json2 = gson2.toJson(img);

                    Toast.makeText(getApplicationContext(), json2, Toast.LENGTH_LONG).show();*/

                    //Toast.makeText(getApplicationContext(), "Has pulsado borrar", Toast.LENGTH_SHORT).show();
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
                //que el backstack del programa esté como cuando está. recién ejecutado
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
            //TODO: mirar que cuando se entra en un perfil, se da a crear y se vuelve atras, aparezca de nuevo
            //habilitado el boton de crear
            bottomNavigationView.getMenu().getItem(0).setEnabled(true);
            bottomNavigationView.getMenu().getItem(1).setEnabled(false);
            bottomNavigationView.getMenu().getItem(3).setEnabled(false);
            bottomNavigationView.getMenu().getItem(4).setEnabled(false);
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
        Personaje p = mainViewModel.getPersonajePorId(id);

        ImagenesFragment frag = ImagenesFragment.newInstance(p.getImagenes());

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
    public void onNavFragmentInteraction(Personaje p)
    {
        /*mainViewModel.getPersonajeLiveData(id).observe(this, new Observer<Personaje>() {
            @Override
            public void onChanged(@Nullable Personaje personaje) {
                //personajeSeleccionado = personaje;
                //if(personaje!=null) {
                    mainViewModel.setPersonajeSeleccionado(personaje);
                    //Personaje aux = personaje;

                    if (personaje != null) {
                        DetallesFragment frag = DetallesFragment.newInstance(mainViewModel.getPersonajeSeleccionado());

                        if (mainViewModel.isTablet()) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.barraImagenes, frag)
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.contenedorPantallaCompleta, frag)
                                    .addToBackStack(null)
                                    .commit();
                        }

                        mainViewModel.getPersonajeLiveData(personaje.getId()).removeObserver(this);

                        bottomNavigationView.getMenu().getItem(0).setEnabled(false);
                        bottomNavigationView.getMenu().getItem(3).setEnabled(true);
                        bottomNavigationView.getMenu().getItem(4).setEnabled(true);
                    }
                //}
            }
        });*/



        mainViewModel.setPersonajeSeleccionado(p);

        DetallesFragment frag = DetallesFragment.newInstance(p);//mainViewModel.getPersonajeSeleccionado());

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

            bottomNavigationView.getMenu().getItem(0).setEnabled(false);
            bottomNavigationView.getMenu().getItem(1).setEnabled(true);
        }

        bottomNavigationView.getMenu().getItem(3).setEnabled(true);
        bottomNavigationView.getMenu().getItem(4).setEnabled(true);

        //activar botones
        //btnCrear.setEnabled(true);

        //aux.setText(String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
    }

    /*public void iniciarObserverPersonajeLiveData()
    {
        observerPersonajeLiveData = new Observer<Personaje>()
        {
            @Override
            public void onChanged(@Nullable Personaje personaje) {
                mainViewModel.setPersonajeSeleccionado(personaje);
                Personaje aux = personaje;

                if (aux != null) {
                    DetallesFragment frag = DetallesFragment.newInstance(mainViewModel.getPersonajeSeleccionado());

                    if (mainViewModel.isTablet()) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.barraImagenes, frag)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedorPantallaCompleta, frag)
                                .addToBackStack(null)
                                .commit();
                    }

                    //finalizarObserverPersonajeLiveData(aux.getId());

                    bottomNavigationView.getMenu().getItem(0).setEnabled(false);
                    bottomNavigationView.getMenu().getItem(3).setEnabled(true);
                    bottomNavigationView.getMenu().getItem(4).setEnabled(true);
                }
            }
        };
    }*/

    /*public void finalizarObserverPersonajeLiveData(long id)
    {
        mainViewModel.getPersonajeLiveData(id).removeObserver(observerPersonajeLiveData);
    }*/

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
        //Navegacion frag = Navegacion.newInstance();

        /*getSupportFragmentManager().popBackStack(getSupportFragmentManager()
                .getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);*/



        if(contenedorPantallaCompleta == null)
        {
            //DetallesFragment frag = DetallesFragment.newInstance()

            /*getSupportFragmentManager().beginTransaction()
                    .remove(getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getBackStackEntryCount()-1))
                    .commit();*/
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
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);
        bottomNavigationView.getMenu().getItem(4).setEnabled(false);
    }

    @Override
    public void onCrearPersFragmentInteraction(String nombre, String alias, String desc, Uri retrato, ListaImagenes imagenes)
    {
        /*int id = ((MainViewModel) mainViewModel).getListaPersonajes().size();

        Personaje p = new Personaje(nombre, alias, desc, retrato, imagenes, id);

        ((MainViewModel) mainViewModel).getListaPersonajes().add(p);

        Toast.makeText(this,"Personaje creado", Toast.LENGTH_SHORT).show();

        iniciarListaPrincipal();*/



        Personaje p = new Personaje(nombre, alias, desc, retrato, imagenes, 0); //Dejar id en 0 para que se autogenere

        //MainActivity.myBaseDatos.miDao().anadirPersonaje(p);
        mainViewModel.insert(p);

        Toast.makeText(this,"Personaje creado", Toast.LENGTH_SHORT).show();

        iniciarListaPrincipal();
    }

    public void borrarPersonaje(Personaje p)
    {
        //MainActivity.myBaseDatos.miDao().borrarPersonaje(p);
        mainViewModel.delete(p);

        Toast.makeText(this,""+p.getAlias()+" ha sido borrado", Toast.LENGTH_SHORT).show();
    }

    /*public void actualizarPersonaje(Personaje p)
    {
        MainActivity.myBaseDatos.miDao().actualizarPersonaje(p);

        Toast.makeText(this,""+p.getAlias()+" ha sido actualizado", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onEditPersFragmentInteraction(String nombre, String alias, String desc, Uri retrato, ListaImagenes imagenes, long id)
    {
        Personaje p = new Personaje(nombre, alias, desc, retrato, imagenes, id);

        //MainActivity.myBaseDatos.miDao().actualizarPersonaje(p);
        mainViewModel.update(p);

        //finalizarObserverPersonajeLiveData(id);

        Toast.makeText(this,""+p.getAlias()+" ha sido actualizado", Toast.LENGTH_SHORT).show();


        bottomNavigationView.getMenu().getItem(0).setEnabled(true);
        bottomNavigationView.getMenu().getItem(1).setEnabled(false);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);
        bottomNavigationView.getMenu().getItem(4).setEnabled(false);

        //TODO: temporal, hasta solucionar lo de que no se vea la lista principal tras editar
        /*getSupportFragmentManager().popBackStack(getSupportFragmentManager()
                .getBackStackEntryAt(1).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);*/

        iniciarListaPrincipal();
        //bottomNavigationView.setSelectedItemId(1);
        //bottomNavigationView.getMenu().getItem(1).setChecked(true);
    }
}
