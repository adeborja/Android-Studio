package com.example.adeborja.fragmentsuno;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CrearFragment extends Fragment
{
    //el valor asignado es su clave. Si dos parametros tienen la misma clave, se sobreescriben.
    private static final String NOMBRE = "nombre";
    private static final String ALIAS = "alias";
    private static final String DESCRIPCION = "descripcion";
    private static final String RETRATO = "retrato";
    private static final String ID = "id";
    private static final String NUMERO_IMAGENES = "numero_imagenes";

    private static String nombre;
    private static String alias;
    private static String descripcion;
    private static String retrato;
    private static String id;
    private static String numero_imagenes;

    //private OnFragmentInteractionListener mListener;

    public CrearFragment() {
        // Required empty public constructor
    }


    public static CrearFragment newInstance() {
        CrearFragment fragment = new CrearFragment();

        /*Bundle args = new Bundle();
        args.putString(NOMBRE, p.getNombre());
        args.putString(ALIAS, p.getAlias());
        args.putString(DESCRIPCION, p.getDescripcion());
        args.putString(RETRATO, String.valueOf(p.getRetrato()));
        args.putString(ID, String.valueOf(p.getId()));
        args.putString(NUMERO_IMAGENES, String.valueOf(p.getCantidadImagenes()));
        fragment.setArguments(args);*/

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        /*if (getArguments() != null) {
            nombre = getArguments().getString(NOMBRE);
            alias = getArguments().getString(ALIAS);
            descripcion = getArguments().getString(DESCRIPCION);
            retrato = getArguments().getString(RETRATO);
            id = getArguments().getString(ID);
            numero_imagenes = getArguments().getString(NUMERO_IMAGENES);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_crear_personaje, container, false);

        //ImageView imgRetrato = (ImageView)v.findViewById(R.id.imgRetratoDetalles);
        TextView txvNombre = (TextView)v.findViewById(R.id.txvNombreDetalles);
        TextView txvAlias = (TextView)v.findViewById(R.id.txvAliasDetalles);
        TextView txvDesctipcion = (TextView)v.findViewById(R.id.txvDescripcionDetalles);

        //imgRetrato.setImageResource(Integer.parseInt(retrato));
        /*txvNombre.setText(nombre);
        txvAlias.setText(alias);
        txvDesctipcion.setText(descripcion);*/

        //Para que tenga scrollbar
        //txvDesctipcion.setMovementMethod(new ScrollingMovementMethod());

        Button btnCrear = (Button)v.findViewById(R.id.btnCrearAceptar);

        //int imagenes = Integer.parseInt(numero_imagenes);


        //TODO: Esto hay que cambiarlo, quizas quitarlo, para hacer que el boton de crear
        // solo este activo cuando se haya introducido al menos nombre y alias
        if(nombre == "" || alias == "")
        {
            btnCrear.setEnabled(false);
            btnCrear.setText(R.string.crear_vacio);
        }
        else
        {
            btnCrear.setEnabled(true);
            btnCrear.setText(R.string.crear_personaje);

            btnCrear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getActivity(),"Has pulsado crear", Toast.LENGTH_SHORT).show();

                    /*int idPersonaje = Integer.parseInt(id);
                    mListener.onDetFragmentInteraction(idPersonaje);*/
                }
            });
        }

        return v;
    }

    @Override
    public void onActivityCreated(Bundle b)
    {
        super.onActivityCreated(b);

        /*if (getArguments() != null) {
            nombre = getArguments().getString(NOMBRE);
            alias = getArguments().getString(ALIAS);
            descripcion = getArguments().getString(DESCRIPCION);
            retrato = getArguments().getString(RETRATO);
        }*/
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(View v) {
        if (mListener != null) {
            mListener.onDetFragmentInteraction(v);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        void onDetFragmentInteraction(int id);
    }*/
}
