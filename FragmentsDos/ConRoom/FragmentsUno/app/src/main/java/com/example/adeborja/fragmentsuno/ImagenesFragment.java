package com.example.adeborja.fragmentsuno;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
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

public class ImagenesFragment extends Fragment {

    private static final String TEXTO_IMAGENES = "texto_imagenes";
    private String miTextoImagenes;
    private static final String IMAGEN = "imagen";
    private static int imagenMostrada;

    private static final String ARRAY_IMAGENES = "array_imagenes";
    private static int[] imagenes;

    private ViewPager viewPager;
    private SlideAdapter slideAdapter;

    private OnFragmentInteractionListener mListener;

    public ImagenesFragment()
    {
        //Constructor vacio
    }

    //TODO: hay que implementar bien los metodos de viewPager en esta clase

    public static ImagenesFragment newInstance(int[] arrayImagenes)
    {
        ImagenesFragment fragment = new ImagenesFragment();

        Bundle args = new Bundle();
        //args.putString(TEXTO_IMAGENES, miTextoImagenes);
        //args.putInt(IMAGEN, imagenMostrada);
        args.putIntArray(ARRAY_IMAGENES, arrayImagenes);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);

        if(getArguments() != null)
        {
            //miTextoImagenes = getArguments().getString(TEXTO_IMAGENES);
            //imagenMostrada = getArguments().getInt(IMAGEN);
            imagenes = getArguments().getIntArray(ARRAY_IMAGENES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor, Bundle b)
    {
        View v = inflador.inflate(R.layout.fragment_imagenes, contenedor, false);

        //TextView txvImagenes = v.findViewById(R.id.textoImagenes);
        //TODO: implementar las imagenes para probar si se ven
        /*TextView txvImagenes = v.findViewById(R.id.textoImagenes);
        txvImagenes.setText(miTextoImagenes);*/

        viewPager = (ViewPager)v.findViewById(R.id.miViewPager);
        slideAdapter = new SlideAdapter(getActivity());

        viewPager.setAdapter(slideAdapter);

        return v;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if(context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
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
    }

    public interface OnFragmentInteractionListener
    {
        void OnImagFragmentInteraction(int posicion);
    }

    public class SlideAdapter extends PagerAdapter
    {

        private Context contexto;
        private LayoutInflater inflador;

        //Lista de imagenes
        /*private int[] listaImagenes =
                {
                        R.drawable.goku,
                        R.drawable.goku01,
                        R.drawable.goku02,
                        R.drawable.goku03
                };*/
        private int[] listaImagenes = imagenes;

        public SlideAdapter(Context contexto)
        {
            this.contexto = contexto;
        }


        @Override
        public int getCount() {
            return listaImagenes.length;
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

            //layoutSlide.setBackgroundColor(listaColoresFondo[position]);
            imgSlide.setImageResource(listaImagenes[position]);

            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ConstraintLayout)object);
        }
    }

}
