package com.example.adeborja.fragmentsuno;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Navegacion extends ListFragment implements View.OnClickListener{

    private static final String TEXTO_BOTON1 = "boton1";
    private static final String TEXTO_BOTON2 = "boton2";
    private String miBoton1, miBoton2;

    private Datos misDatos;
    private List<Personaje> listaPersonajes;
    private ListView listView;

    private OnFragmentInteractionListener miListener;

    //Es obligatorio incluir un constructor vacío
    public Navegacion()
    {

    }

    /**Es obligatorio que las actividades que vayan a contener este fragment
    * implementen esta interfaz para permitir que la interaccion en este fragment
    * se comunique con la actividad y potencialmente con otros fragments
    *incluidos en esa actividad.
    */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(View v);
    }

    //Factory method para crear una nueva instancia de este fragmento,
    //añadiendo parametros si los tuviera.
    public static Navegacion newInstance(String miBoton1, String miBoton2)
    {
        Navegacion fragment = new Navegacion();

        //Aquí se añadirian los parametros que hubiera, por ejemplo:
        Bundle args = new Bundle();
        args.putString(TEXTO_BOTON1, miBoton1);
        args.putString(TEXTO_BOTON2, miBoton2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Aquí se añadirian los parametros que hubiera, por ejemplo:
        if(getArguments() != null)
        {
            miBoton1 = getArguments().getString(TEXTO_BOTON1);
            miBoton2 = getArguments().getString(TEXTO_BOTON2);
        }

        /*misDatos = (Datos) getActivity().getApplicationContext();

        listaPersonajes = misDatos.getListaPersonajes();

        //ListAdapter

        listView = (ListView)getListView().findViewById(android.R.id.list);
        listView.setAdapter(new Navegacion.PersonajesAdapter());//TODO: peta, no castea arriba, y aqui dice que la referencia del objeto es null
*/
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor, Bundle savedInstanceState)
    {
        View v = inflador.inflate(R.layout.fragment_navegacion, contenedor, false); //TODO: layout de este fragment

        //En este view va a ir una listview que saldran los nombres y una foto del personaje
        //TODO: listview. Aquí debe colocarse todo el codigo para crear la lista (adaptador, bindeos, etc)
        //En el ejemplo, se pone los findviewbyid de los elementos y se les pone su setonclicklistener
        /*Button b1 = v.findViewById(R.id.boton1);
        Button b2 = v.findViewById(R.id.boton2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);*/

        //TODO: todo lo que hay que hacer para que aparezca la lista en el programa de la lista hay que meterlo en esta clase, ya que pertenece al fragment y es aquí donde debe estar creado y asignado. El main activity simplemente tiene que usar esta clase.

        /*misDatos = (Datos) getActivity().getApplicationContext();

        listaPersonajes = misDatos.getListaPersonajes();

        //ListAdapter

        listView = (ListView)getListView().findViewById(android.R.id.list);
        listView.setAdapter(new Navegacion.PersonajesAdapter());//TODO: peta, no castea arriba, y aqui dice que la referencia del objeto es null
*/

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        /*misDatos = (Datos) getActivity().getApplicationContext();

        listaPersonajes = misDatos.getListaPersonajes();

        //ListAdapter

        listView = (ListView)getListView().findViewById(android.R.id.list);
        listView.setAdapter(new PersonajesAdapter());//TODO: peta, no castea arriba, y aqui dice que la referencia del objeto es null
*/
    }

    @Override
    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);

        misDatos = (Datos) getActivity().getApplicationContext();

        listaPersonajes = misDatos.getListaPersonajes();

        //ListAdapter

        listView = (ListView)getListView().findViewById(android.R.id.list);
        PersonajesAdapter personajesAdapter = new PersonajesAdapter();
        setListAdapter(personajesAdapter);//TODO: peta, no castea arriba, y aqui dice que la referencia del objeto es null

    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if(context instanceof OnFragmentInteractionListener)
        {
            miListener = (OnFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + "debe implementarse OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        miListener = null;
    }

    @Override
    public void onClick(View v)
    {
        miListener.onFragmentInteraction(v);
    }

    public void onListItemClick(ListView padre, View vista, int posicion, long id)
    {
        Intent i;
        Personaje p;

        p = (Personaje) listaPersonajes.get(posicion);
        i = new Intent(getActivity(), ImagenesFragment.class);
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
            //return 10;
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
            ViewHolderPersonaje holderPersonaje;

            Personaje p;

            TextView alias, cantidadImagenes;
            ImageView retrato;

            holderPersonaje = new ViewHolderPersonaje();

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

            p = listaPersonajes.get(position);
            holderPersonaje.getAlias().setText(p.getAlias());
            holderPersonaje.getCantidadImagenes().setText(p.getCantidadImagenes());
            holderPersonaje.getRetrato().setImageResource(p.getRetrato());

            return fila;
        }
    }

}
