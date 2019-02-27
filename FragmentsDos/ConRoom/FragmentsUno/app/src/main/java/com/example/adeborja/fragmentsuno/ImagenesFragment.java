package com.example.adeborja.fragmentsuno;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ImagenesFragment extends Fragment {

    private static final String ARRAY_IMAGENES = "array_imagenes";
    //private static ListaImagenes imagenes;
    private static List<String> listImagenes;

    private ViewPager viewPager;
    private SlideAdapter slideAdapter;

    //private OnFragmentInteractionListener mListener;

    public ImagenesFragment()
    {
        //Constructor vacio
    }

    public static ImagenesFragment newInstance(List<String> stringList) //ListaImagenes arrayImagenes)
    {
        ImagenesFragment fragment = new ImagenesFragment();

        Bundle args = new Bundle();

        /*String[] aux = new String[arrayImagenes.length];

        for(int i=0;i<arrayImagenes.length;i++)
        {
            aux[i] = arrayImagenes[i].toString();
        }

        args.putStringArray(ARRAY_IMAGENES, aux);*/

        //args.putParcelable(ARRAY_IMAGENES, arrayImagenes);

        Gson gson = new Gson();
        //Type listType = new TypeToken<List<String>>() {}.getType();
        String aux = gson.toJson(stringList); //, listType);

        args.putString(ARRAY_IMAGENES, aux);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);

        if(getArguments() != null)
        {
            /*String[] aux = getArguments().getStringArray(ARRAY_IMAGENES);

            Uri[] aux2 = new Uri[aux.length];
            for(int i=0;i<aux.length;i++)
            {
                aux2[i]=Uri.parse(aux[i]);
            }

            imagenes = aux2;*/

            //imagenes = getArguments().getParcelable(ARRAY_IMAGENES);

            Gson gson = new Gson();
            String auxString = getArguments().getString(ARRAY_IMAGENES);
            Type listType = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> auxList = gson.fromJson(auxString, listType);

            listImagenes =  auxList;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor, Bundle b)
    {
        View v = inflador.inflate(R.layout.fragment_imagenes, contenedor, false);

        viewPager = (ViewPager)v.findViewById(R.id.miViewPager);
        slideAdapter = new SlideAdapter(getActivity(), listImagenes);

        viewPager.setAdapter(slideAdapter);

        return v;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        /*if(context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + "debe implementarse OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

    /*public interface OnFragmentInteractionListener
    {
        void OnImagFragmentInteraction(int posicion);
    }*/

    public class SlideAdapter extends PagerAdapter
    {

        private Context contexto;
        private LayoutInflater inflador;

        //private ListaImagenes listaImagenes = imagenes;
        private List<String> listaStringImagenes;

        public SlideAdapter(Context contexto, List<String> listaStringImagenes)
        {
            this.contexto = contexto;
            this.listaStringImagenes = listaStringImagenes;
        }


        @Override
        public int getCount() {
            //return listaImagenes.getSize();
            return listaStringImagenes.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return (view==(ConstraintLayout)o);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            inflador = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

            View view = inflador.inflate(R.layout.slide, container, false);

            ConstraintLayout layoutSlide = (ConstraintLayout) view.findViewById(R.id.miSlide);
            ImageView imgSlide = (ImageView) view.findViewById(R.id.imgSlide);

            //imgSlide.setImageURI(listaImagenes.getImagenDeLista(position));
            imgSlide.setImageURI(Uri.parse(listaStringImagenes.get(position)));

            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ConstraintLayout)object);
        }
    }

}
