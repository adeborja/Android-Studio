package com.example.adeborja.fragmentsuno;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class DetallesFragment extends Fragment {
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

    private OnFragmentInteractionListener mListener;

    public DetallesFragment() {
        // Required empty public constructor
    }


    public static DetallesFragment newInstance(Personaje p) {
        DetallesFragment fragment = new DetallesFragment();

        Bundle args = new Bundle();
        args.putString(NOMBRE, p.getNombre());
        args.putString(ALIAS, p.getAlias());
        args.putString(DESCRIPCION, p.getDescripcion());
        args.putString(RETRATO, String.valueOf(p.getRetrato()));
        args.putString(ID, String.valueOf(p.getId()));
        args.putString(NUMERO_IMAGENES, String.valueOf(p.getCantidadImagenes()));
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString(NOMBRE);
            alias = getArguments().getString(ALIAS);
            descripcion = getArguments().getString(DESCRIPCION);
            retrato = getArguments().getString(RETRATO);
            id = getArguments().getString(ID);
            numero_imagenes = getArguments().getString(NUMERO_IMAGENES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalles, container, false);

        ImageView imgRetrato = (ImageView)v.findViewById(R.id.imgRetratoDetalles);
        TextView txvNombre = (TextView)v.findViewById(R.id.txvNombreDetalles);
        TextView txvAlias = (TextView)v.findViewById(R.id.txvAliasDetalles);
        TextView txvDesctipcion = (TextView)v.findViewById(R.id.txvDescripcionDetalles);

        imgRetrato.setImageURI(Uri.parse(retrato));
        txvNombre.setText(nombre);
        txvAlias.setText(alias);
        txvDesctipcion.setText((descripcion == "")? "sin descripción" : descripcion );


        //Para que tenga scrollbar
        txvDesctipcion.setMovementMethod(new ScrollingMovementMethod());

        Button btnImagenes = (Button)v.findViewById(R.id.btnImagenes);

        int imagenes = Integer.parseInt(numero_imagenes);

        if(imagenes==0)
        {
            btnImagenes.setEnabled(false);
            btnImagenes.setText(R.string.galeria_sin_imagenes);
        }
        else
        {
            btnImagenes.setEnabled(true);
            btnImagenes.setText(R.string.ver_galeria);

            btnImagenes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int idPersonaje = Integer.parseInt(id);
                    mListener.onDetFragmentInteraction(idPersonaje);
                }
            });
        }

        return v;
    }

    @Override
    public void onActivityCreated(Bundle b)
    {
        super.onActivityCreated(b);

        if (getArguments() != null) {
            nombre = getArguments().getString(NOMBRE);
            alias = getArguments().getString(ALIAS);
            descripcion = getArguments().getString(DESCRIPCION);
            retrato = getArguments().getString(RETRATO);
        }
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        void onDetFragmentInteraction(int id);
    }
}
