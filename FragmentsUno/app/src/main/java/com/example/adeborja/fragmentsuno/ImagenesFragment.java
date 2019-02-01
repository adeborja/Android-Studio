package com.example.adeborja.fragmentsuno;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ImagenesFragment extends Fragment {

    private static final String TEXTO_IMAGENES = "texto_imagenes";
    private String miTextoImagenes;

    public ImagenesFragment()
    {
        //Constructor vacio
    }

    public static ImagenesFragment newInstance(String miTextoImagenes)
    {
        ImagenesFragment fragment = new ImagenesFragment();

        Bundle args = new Bundle();
        args.putString(TEXTO_IMAGENES, miTextoImagenes);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);

        if(getArguments() != null)
        {
            miTextoImagenes = getArguments().getString(TEXTO_IMAGENES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor, Bundle b)
    {
        View v = inflador.inflate(R.layout.fragment_imagenes, contenedor, false);

        TextView txvImagenes = v.findViewById(R.id.textoImagenes);
        txvImagenes.setText(miTextoImagenes);

        return v;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

}
