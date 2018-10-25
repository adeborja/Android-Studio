package com.example.adeborja.listadiferenteslayout;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//necesario cambiar la clase de la que hereda
public class listaDiferentesLayout extends ListActivity {

    TextView txvCajaTexto;
    //ListView lista;

    //ArrayList<clsEquipo> listaEquipos;
    static clsEquipo[] listaEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_diferentes_layout);

        txvCajaTexto = (TextView)findViewById(R.id.txvCajaTexto);
        //lista = (ListView) findViewById(R.id.list);

        rellenarLista();

        //definir el setListAdapter. Para ello, primero es necesario crear la clase interna a utilizar. Cuando se defina esta linea, sera necesario crear el metodo correspondiente
        setListAdapter(new IconicAdapter<clsEquipo>(this,R.layout.fila_con_imagen, R.id.txvFilaConImagen,listaEquipos));
    }

    public void onListItemClick(ListView padre, View vista, int posicion, long id)
    {
        txvCajaTexto.setText(listaEquipos[posicion].getNombre());
    }

    //Metodo para rellenar un array de equipos
    public void rellenarLista()
    {
        /*listaEquipos = new ArrayList<clsEquipo>(0);

        listaEquipos.add(new clsEquipo(0,"Sevilla FC",""));
        listaEquipos.add(new clsEquipo(1,"FC Barcelona",""));
        listaEquipos.add(new clsEquipo(2,"Real Madrid",""));
        listaEquipos.add(new clsEquipo(3,"Atletico Madrid",""));
        listaEquipos.add(new clsEquipo(4,"Valencia",""));
        listaEquipos.add(new clsEquipo(5,"Real Betis",""));
        listaEquipos.add(new clsEquipo(6,"Villarreal",""));
        listaEquipos.add(new clsEquipo(7,"Getafe",""));
        listaEquipos.add(new clsEquipo(8,"Alaves",""));
        listaEquipos.add(new clsEquipo(9,"Athletic Bilbao",""));
        listaEquipos.add(new clsEquipo(10,"Celta",""));
        listaEquipos.add(new clsEquipo(11,"Eibar",""));
        listaEquipos.add(new clsEquipo(12,"Espanyol",""));
        listaEquipos.add(new clsEquipo(13,"Girona",""));
        listaEquipos.add(new clsEquipo(14,"Huesca",""));
        listaEquipos.add(new clsEquipo(15,"Leganes",""));
        listaEquipos.add(new clsEquipo(16,"Levante",""));
        listaEquipos.add(new clsEquipo(17,"Rayo Vallecano",""));
        listaEquipos.add(new clsEquipo(18,"Real Sociedad",""));
        listaEquipos.add(new clsEquipo(19,"Valladolid",""));*/

        listaEquipos = new clsEquipo[3];

        listaEquipos[0] = new clsEquipo(0,"Sevilla FC","");
        listaEquipos[1] = new clsEquipo(1,"FC Barcelona","");
        listaEquipos[2] = new clsEquipo(2,"Real Madrid","");


    }





    //Creacion de la clase interna
    class IconicAdapter<T> extends ArrayAdapter<T>
    {
        IconicAdapter(Context contexto, int idRecurso, int idTexto, T[] objetos)
        {
            super(contexto,idRecurso,idTexto,objetos);
        }

        public View getView(int posicion, View convertView, ViewGroup padre)
        {
            LayoutInflater inflador;
            TextView label;
            ImageView icono;

            View fila = convertView;

            if(fila == null)
            {
                inflador = getLayoutInflater();

                //indicar que diseño de fila se va a utilizar
                fila = inflador.inflate(R.layout.fila_con_imagen, padre, false);
            }

            //Elegir donde se va a escribir el nombre del equipo
            label = (TextView)findViewById(R.id.filaSimple);

            //Coger el nombre del equipo
            //label.setText(listaEquipos.get(posicion).getNombre());
            label.setText(listaEquipos[posicion].getNombre());

            //Elegir el icono a mostrar en la fila
            icono = (ImageView)findViewById(R.id.imgFilaConImagen);

            //if(listaEquipos.get(posicion).getId()==0)
            if(listaEquipos[posicion].getId()==0)
            {
                icono.setImageResource(R.mipmap.ic_launcher);
            }
            else
            {
                icono.setImageResource(R.mipmap.ic_launcher_round);
            }

            //retornar la fila
            return (fila);
        }
    }

    //Metodo para pintar el nombre del un equipo cuando se pincha en el
}
