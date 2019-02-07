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

import java.util.List;

public class MainActivity extends AppCompatActivity implements Navegacion.OnFragmentInteractionListener {

    private ViewModel mainViewModel;
    private Datos misDatos;
    private List<Personaje> listaPersonajes;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: todo lo de aquí que sea del listview FUERA, tiene que estar en la clase Navegacion

        //misDatos = (Datos) getApplicationContext();
        misDatos = new Datos();

        listaPersonajes = misDatos.getListaPersonajes();

        //ListAdapter

        listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(new PersonajesAdapter());//TODO: peta, no castea arriba, y aqui dice que la referencia del objeto es null


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

    public void onListItemClick(ListView padre, View vista, int posicion, long id)
    {
        Intent i;
        Personaje p;

        p = (Personaje) listaPersonajes.get(posicion);
        i = new Intent(this, ImagenesFragment.class);
        i.putExtra("personaje", p);
        startActivity(i);
    }

    class ViewHolderPersonaje
    {
        TextView alias;
        ImageView retrato;
        TextView cantidadImagenes;

        ViewHolderPersonaje(){}

        ViewHolderPersonaje (TextView nAlias, ImageView nRetrato, TextView nCantidadImagenes)
        {
            this.alias=nAlias;
            this.retrato=nRetrato;
            this.cantidadImagenes=nCantidadImagenes;
        }

        public TextView getAlias()
        {
            return this.alias;
        }

        public TextView getCantidadImagenes()
        {
            return this.cantidadImagenes;
        }

        public ImageView getRetrato()
        {
            return this.retrato;
        }

    }

    public class PersonajesAdapter extends BaseAdapter
    {
        public PersonajesAdapter()
        {
            super();
        }

        @Override
        public int getCount() {
            return listaPersonajes.size();
        }

        @Override
        public int getViewTypeCount()
        {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return listaPersonajes.get(position);
        }

        @Override
        public long getItemId(int position) {

            long id = ((Personaje) listaPersonajes.get(position)).getId();

            return id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View fila = convertView;

            Personaje p;

            TextView alias, cantidadImagenes;
            ImageView retrato;

            ViewHolderPersonaje holderPersonaje = new ViewHolderPersonaje();

            if(fila == null)
            {
                LayoutInflater inflater = getLayoutInflater();

                fila = inflater.inflate(R.layout.estilo_fila, parent, false);

                alias = (TextView)fila.findViewById(R.id.txvAlias);
                cantidadImagenes = (TextView)fila.findViewById(R.id.txvCantidadImagenes);
                retrato = (ImageView)fila.findViewById(R.id.imgRetrato);

                holderPersonaje = new ViewHolderPersonaje(alias, retrato, cantidadImagenes);

                fila.setTag(holderPersonaje);
            }
            else
            {
                holderPersonaje = (ViewHolderPersonaje) fila.getTag();
            }

            p = (Personaje)listaPersonajes.get(position);
            holderPersonaje.getAlias().setText(p.getAlias());
            holderPersonaje.getCantidadImagenes().setText(p.getCantidadImagenes());
            holderPersonaje.getRetrato().setImageResource(p.getRetrato());

            return fila;
        }
    }
}
