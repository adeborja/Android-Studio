package com.example.adeborja.spinnerdos;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerDos extends Activity implements Spinner.OnItemSelectedListener{

    private Spinner spin;
    private TextView cajaTexto;
    private String[] equipos = {"Mercedes", "Ferrari", "Red Bull", "Renault"};
    private int[] logos = {R.drawable.mercedes, R.drawable.ferrari, R.drawable.redbull, R.drawable.renault};
    //private int[] logos = {R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_dos);

        spin = (Spinner)findViewById(R.id.spinner);
        cajaTexto = (TextView)findViewById(R.id.txvTexto);

        //Crear un adaptador personalizado para utilizarlo en esta app
        //Parametros: this, id del layout de la fila, id del campo de la fila
        // donde aparecerá el nombre, lista de donde coger los nombre
        FormulaAdapter<String> adaptador = new FormulaAdapter<String>(this, R.layout.fila, R.id.fila, equipos);
        adaptador.setDropDownViewResource(R.layout.fila);

        spin.setAdapter(adaptador);
        spin.setOnItemSelectedListener(this);
    }

    //Es necesario crear una carpeta en "res" que se llame menu y meter un diseño de xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if(id==R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Obligatorio por implementacion
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        cajaTexto.setText(equipos[i]);
    }

    //Obligatorio por implementacion
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        cajaTexto.setText("");
    }

    //ADAPTADOR
    class FormulaAdapter<T> extends ArrayAdapter<T>
    {
        public FormulaAdapter(Context c, int fila, int txv, T[] array)
        {
            super(c, fila, txv, array);
        }

        @Override
        public View getView(int posicion, View convertView, ViewGroup padre)
        {
            View fila;
            TextView tv;

            //Hay que crear un viewHolder
            ViewHolder holder;

            fila = convertView;

            if(convertView==null)
            {
                LayoutInflater inflador = getLayoutInflater();
                fila = inflador.inflate(R.layout.fila, padre, false);
                holder = new ViewHolder(fila);
                fila.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) fila.getTag();
            }

            tv = (TextView) holder.getTv(); //tv llega a null

            tv.setText(equipos[posicion]);

            tv.setCompoundDrawablesWithIntrinsicBounds(logos[posicion],0,0,0);

            return fila;
        }

        @Override
        public View getDropDownView(int posicion, View convertView, ViewGroup padre)
        {
            View fila;
            TextView tv;

            ViewHolder holder;

            fila = convertView;

            if(convertView==null)
            {
                LayoutInflater inflador = getLayoutInflater();
                fila = inflador.inflate(R.layout.fila, padre, false);
                holder = new ViewHolder(fila);
                fila.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) fila.getTag();
            }

            tv = (TextView) holder.getTv();

            tv.setText(equipos[posicion]);

            tv.setCompoundDrawablesWithIntrinsicBounds(logos[posicion], 0,0, 0);

            return fila;
        }
    }

    //VIEWHOLDER, fuera del adaptador
    class ViewHolder
    {
        TextView tv;

        public ViewHolder(View fila)
        {
            tv = (TextView)fila.findViewById(R.id.fila);
        }

        public TextView getTv()
        {
            return tv;
        }
    }
}
