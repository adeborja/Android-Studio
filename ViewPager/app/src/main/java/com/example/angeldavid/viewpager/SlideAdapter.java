package com.example.angeldavid.viewpager;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter
{

    private Context contexto;
    private LayoutInflater inflador;

    //Lista de imagenes
    private int[] listaImagenes =
    {
            R.drawable.mercedes_dos,
            R.drawable.ferrari_dos,
            R.drawable.redbull_dos,
            R.drawable.renault_dos
    };

    //lista de nombres
    private String[] listaNombres = {"Mercedes", "Ferrari", "Red Bull", "Renault"};

    //Lista de descripciones
    private String[] listaDescripciones = {"Equipo de las flechas plateadas", "Equipo del cavallino rampante", "Equipo de la bebida que te da alas", "Equipo con el motor Baguette6"};

    //lista de colores de fondo
    private int[] listaColoresFondo =
            {
                    Color.rgb(98,112,120),
                    Color.rgb(237,28,36),
                    Color.rgb(0, 29, 135),
                    Color.rgb(237, 184, 51)
            };

    public SlideAdapter(Context contexto)
    {
        this.contexto = contexto;
    }


    @Override
    public int getCount() {
        return listaNombres.length;
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

        ConstraintLayout layoutSlide = (ConstraintLayout) view.findViewById(R.id.slidexml);
        ImageView imgSlide = (ImageView) view.findViewById(R.id.imageView);
        TextView txvTitulo = (TextView) view.findViewById(R.id.txvTitulo);
        TextView txvContenido = (TextView) view.findViewById(R.id.txvContenido);

        layoutSlide.setBackgroundColor(listaColoresFondo[position]);
        imgSlide.setImageResource(listaImagenes[position]);
        txvTitulo.setText(listaNombres[position]);
        txvContenido.setText(listaDescripciones[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
