
package com.example.adeborja.lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Lista extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lista;
    TextView barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = findViewById(R.id.listita);
        barra = (TextView) findViewById(R.id.barrita);

        String[] numeros = {"uno","dos","tres","cuatro"};
        ArrayAdapter<String> a = new ArrayAdapter<String>(this,R.layout.fila, R.id.lineaBarraTexto, numeros);
        lista.setAdapter(a);
        lista.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //String item = ((TextView)view.findViewById(R.id.listita)).getText().toString();
        TextView item = view.findViewById(R.id.lineaBarraTexto);
        String s = item.getText().toString();
        barra.setText(s);
        //Toast toast = Toast.makeText(this,s + " pulsado",Toast.LENGTH_SHORT);
        //toast.show();
    }
}
