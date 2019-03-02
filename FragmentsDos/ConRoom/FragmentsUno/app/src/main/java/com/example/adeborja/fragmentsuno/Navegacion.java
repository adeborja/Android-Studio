package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

public class Navegacion extends ListFragment {

    private ListView listView;
    private LiveData<List<Personaje>> listLiveData;
    private MainViewModel vm;
    private PersonajesAdapter adapter;


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

        void onNavFragmentInteraction(Personaje p);
    }

    //Factory method para crear una nueva instancia de este fragmento,
    //añadiendo parametros si los tuviera.
    public static Navegacion newInstance()
    {
        Navegacion fragment = new Navegacion();

        return fragment;
    }

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

        vm = ViewModelProviders.of(this).get(MainViewModel.class);
        vm.setContext(getContext());

        listView = (ListView)getListView().findViewById(android.R.id.list);

        //Observador para la lista. Dentro del onChanged van la asignacion de la lista cada vez que hay un cambio y el set del adaptador
        vm.getListaLiveData().observe(this, new Observer<List<Personaje>>() {
            @Override
            public void onChanged(@Nullable List<Personaje> personajes) {

                adapter = new PersonajesAdapter(personajes);
                if(adapter.getCount()==0) vm.rellenarLista();
                listView.setAdapter(adapter);
            }
        });

        listLiveData = vm.getListaLiveData();

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
    public void onListItemClick(ListView padre, View vista, int posicion, long id)
    {
        //aqui hay que llamar al metodo onfragmentinteraction implementado en el main. Es el
        //main el que debe cambiar entre fragments, como un programa que llama a metodos.

        Personaje p = (Personaje) adapter.getItem(posicion);

        miListener.onNavFragmentInteraction(p);
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
        private List<Personaje> listaAdapter;

        public PersonajesAdapter()
        {
            super();
            this.listaAdapter = new ArrayList<Personaje>(0);
        }

        public PersonajesAdapter(List<Personaje> list)
        {
            super();
            this.listaAdapter = list;
        }

        public void setListaAdapter(List<Personaje> list)
        {
            this.listaAdapter = list;
        }

        @Override
        public int getCount() {

            return listaAdapter.size();
        }

        @Override
        public int getViewTypeCount()
        {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return listaAdapter.get(position);
        }

        @Override
        public long getItemId(int position) {

            long id = listaAdapter.get(position).getId();

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

            p = listaAdapter.get(position);

            holderPersonaje.getAlias().setText(p.getAlias());
            holderPersonaje.getCantidadImagenes().setText(String.valueOf(p.getCantidadImagenes()));
            holderPersonaje.getRetrato().setImageURI(p.getRetrato());

            return fila;
        }
    }

}
