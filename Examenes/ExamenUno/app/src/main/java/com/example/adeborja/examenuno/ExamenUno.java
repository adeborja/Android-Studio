package com.example.adeborja.examenuno;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ExamenUno extends ListActivity {

    private List<Object> listaJugadores;

    private MyApplication miAplicacion;
    private Intent i;
    private String IntentPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen_uno);

        miAplicacion = (MyApplication) getApplicationContext();

        listaJugadores = miAplicacion.getJugadores();

        setListAdapter(new JugadoresAdapter());

        i = getIntent();

        if(i!=null)
        {
            IntentPackage=i.getPackage();

            if(IntentPackage=="guardar_f")
            {

            }
            else if(IntentPackage=="borrar_f")
            {

            }
            else if(IntentPackage=="clonar_f")
            {

            }
            if(IntentPackage=="guardar_b")
            {

            }
            else if(IntentPackage=="borrar_b")
            {

            }
            else if(IntentPackage=="clonar_b")
            {

            }
        }
    }

    public void onListItemClick(ListView padre, View vista, int posicion, long id)
    {
        Intent i;
        clsJugadorFutbol jf;
        clsJugadorBaloncesto jb;

        if(listaJugadores.get(posicion) instanceof clsJugadorFutbol)
        {
            jf = (clsJugadorFutbol)listaJugadores.get(posicion);

            i = new Intent(this, editarFutbolista.class);

            i.putExtra("jugador", jf);
        }
        else
        {
            jb = (clsJugadorBaloncesto) listaJugadores.get(posicion);

            i = new Intent(this, editarBaloncesto.class);

            i.putExtra("jugador", jb);
        }

        startActivity(i);
    }


    class ViewHolderFutbol
    {
        TextView nombre;
        TextView posicion;
        ImageView foto;

        ViewHolderFutbol(){}

        ViewHolderFutbol (TextView nNombre, TextView nPosicion, ImageView nFoto)
        {
            this.nombre = nNombre;
            this.posicion = nPosicion;
            this.foto = nFoto;
        }

        public TextView getNombre()
        {
            return this.nombre;
        }

        public TextView getPosicion() { return this.posicion; }

        public ImageView getFoto() { return this.foto; }
    }

    class ViewHolderBaloncesto
    {
        TextView nombre;
        TextView puntos;
        TextView rebotes;
        TextView asistencias;
        ImageView foto;

        ViewHolderBaloncesto(){}

        ViewHolderBaloncesto (TextView nNombre, ImageView nFoto, TextView nPuntos, TextView nRebotes, TextView nAsistencias)
        {
            this.nombre = nNombre;
            this.foto = nFoto;
            this.puntos = nPuntos;
            this.rebotes = nRebotes;
            this.asistencias = nAsistencias;
        }

        public TextView getNombre()
        {
            return this.nombre;
        }

        public ImageView getFoto()
        {
            return this.foto;
        }

        public TextView getPuntos()
        {
            return this.puntos;
        }

        public TextView getRebotes()
        {
            return this.rebotes;
        }

        public TextView getAsistencias()
        {
            return this.asistencias;
        }
    }

    public class JugadoresAdapter extends BaseAdapter {
        public JugadoresAdapter() {
            super();
        }

        //pulsar control+U en los nombres de los metodos para ver que devuelven
        @Override
        public Object getItem(int posicion) {
            return listaJugadores.get(posicion);
        }

        @Override
        public long getItemId(int posicion) {
            long id;

            if(listaJugadores.get(posicion) instanceof clsJugadorFutbol)
            {
                id  = ((clsJugadorFutbol) listaJugadores.get(posicion)).getId();
            }
            else
            {
                id = ((clsJugadorBaloncesto) listaJugadores.get(posicion)).getId();
            }

            return id;
        }

        @Override
        public int getCount() {
            return listaJugadores.size();
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int posicion) {
            int devolver;

            if(listaJugadores.get(posicion) instanceof clsJugadorFutbol)
            {
                devolver = 0;
            }
            else
            {
                devolver = 1;
            }

            return devolver;
        }


        @Override
        public View getView(int posicion, View convertView, ViewGroup padre) {
            View fila = convertView;
            int tipo = getItemViewType(posicion);
            clsJugadorBaloncesto jb;
            clsJugadorFutbol jf;

            TextView nombre;
            ImageView imgUno, imgDos;

            ViewHolderFutbol holderFutbol = new ViewHolderFutbol();
            ViewHolderBaloncesto holderBaloncesto = new ViewHolderBaloncesto();
            TextView hNombre, hPuntos, hRebotes, hAsistencias, hPosicion;
            ImageView hFoto;

            if (fila == null) {
                LayoutInflater inflador = getLayoutInflater();


                //tipo 0 = futbol, tipo 1 = baloncesto
                if (tipo == 0) {
                    fila = inflador.inflate(R.layout.fila_futbolista, padre, false);

                    hNombre = (TextView) fila.findViewById(R.id.txvNombre);
                    hFoto = (ImageView) fila.findViewById(R.id.imgFoto);
                    hPosicion = (TextView) fila.findViewById(R.id.txvPosicion);

                    holderFutbol = new ViewHolderFutbol(hNombre, hPosicion, hFoto);

                    fila.setTag(holderFutbol);
                }
                else {
                    fila = inflador.inflate(R.layout.fila_baloncesto, padre, false);

                    hNombre = (TextView) fila.findViewById(R.id.txvNombre);
                    hFoto = (ImageView) fila.findViewById(R.id.imgFoto);
                    hPuntos = (TextView) fila.findViewById(R.id.txvPuntos);
                    hRebotes = (TextView) fila.findViewById(R.id.txvRebotes);
                    hAsistencias = (TextView) fila.findViewById(R.id.txvAsistencias);

                    holderBaloncesto = new ViewHolderBaloncesto(hNombre, hFoto, hPuntos, hRebotes, hAsistencias);

                    fila.setTag(holderBaloncesto);
                }
            }
            else {
                //tipo 0 = futbol, tipo 1 = baloncesto
                if (tipo == 0) {
                    holderFutbol = (ViewHolderFutbol) fila.getTag();
                } else {
                    holderBaloncesto = (ViewHolderBaloncesto) fila.getTag();
                }
            }


            if (tipo == 0) {
                jf = (clsJugadorFutbol)listaJugadores.get(posicion);
                holderFutbol.getNombre().setText(jf.getNombre());
                holderFutbol.getFoto().setImageResource(jf.getFoto());
                holderFutbol.getPosicion().setText(jf.getPosicionActual());
            }
            else {
                jb = (clsJugadorBaloncesto)listaJugadores.get(posicion);
                holderBaloncesto.getNombre().setText(jb.getNombre());
                holderBaloncesto.getFoto().setImageResource(jb.getFoto());
                holderBaloncesto.getPuntos().setText(String.valueOf(jb.getPuntosPorPartido())+" puntos");
                holderBaloncesto.getRebotes().setText(String.valueOf(jb.getRebotesPorPartido())+" rebotes");
                holderBaloncesto.getAsistencias().setText(String.valueOf(jb.getAsistenciasPorPartido())+" asistencias");
            }


            return fila;
        }
    }
}
