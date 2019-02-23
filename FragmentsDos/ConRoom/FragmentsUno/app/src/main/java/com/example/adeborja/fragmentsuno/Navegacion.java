package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.Toast;

import java.util.List;

public class Navegacion extends ListFragment {

    private static List<Personaje> listaPersonajes;
    private ListView listView;

    public static PersonajesAdapter mAdapter;

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

        void onNavFragmentInteraction(int posicion);
    }

    //Factory method para crear una nueva instancia de este fragmento,
    //añadiendo parametros si los tuviera.
    public static Navegacion newInstance()
    {
        Navegacion fragment = new Navegacion();

        return fragment;
    }

    /*public static Navegacion newInstance(List<Personaje> nuevaLista)
    {
        Navegacion fragment = new Navegacion();

        listaPersonajes = nuevaLista;

        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor, Bundle savedInstanceState)
    {
        View v = inflador.inflate(R.layout.fragment_navegacion, contenedor, false);

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);

        mAdapter = new PersonajesAdapter();

        ViewModel vm = MainActivity.mainViewModel;

        listaPersonajes = ((MainViewModel) vm).getListaPersonajes();

        listView = (ListView)getListView().findViewById(android.R.id.list);

        listView.setAdapter(mAdapter);

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

    /*@Override
    public void onClick(View v)
    {
        //Toast.makeText(getActivity(),"onClick", Toast.LENGTH_SHORT);
        miListener.onNavFragmentInteraction(v);
    }*/

    @Override
    public void onListItemClick(ListView padre, View vista, int posicion, long id)
    {

        //aqui hay que llamar al metodo onfragmentinteraction implementado en el main. Es el
        //main el que debe cambiar entre fragments, como un programa que llama a metodos.

        miListener.onNavFragmentInteraction(posicion);

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

        public void actualizarListaPersonajes(List<Personaje> lista)
        {
            listaPersonajes = lista;
            notifyDataSetChanged();
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
            holderPersonaje.getRetrato().setImageURI(p.getRetrato());

            return fila;
        }
    }

}
