package com.example.adeborja.fragmentsuno;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class DetallesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NOMBRE = "param1";
    private static final String ALIAS = "param2";
    private static final String DESCRIPCION = "param2";
    private static final String RETRATO = "0";

    // TODO: Rename and change types of parameters
    private static String nombre;
    private static String alias;
    private static String descripcion;
    private static String retrato;

    //private OnFragmentInteractionListener mListener;

    public DetallesFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DetallesFragment newInstance(String nNombre, String nAlias, String nDesc, String nRetrato) {
        DetallesFragment fragment = new DetallesFragment();

        Bundle args = new Bundle();
        args.putString(NOMBRE, nNombre);
        args.putString(ALIAS, nAlias);
        args.putString(DESCRIPCION, nDesc);
        args.putString(RETRATO, nRetrato);
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


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
