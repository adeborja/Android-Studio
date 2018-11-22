package com.example.angeldavid.intentemail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntentEmail extends AppCompatActivity {

    private TextView txvEmail, txvAsunto, txvTexto;
    private EditText edtEmail, edtAsunto, edtTexto;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_email);

        txvEmail = (TextView)findViewById(R.id.txvEmail);
        txvAsunto = (TextView)findViewById(R.id.txvAsunto);
        txvTexto = (TextView)findViewById(R.id.txvTexto);

        edtEmail = (EditText)findViewById(R.id.etxEmail);
        edtAsunto = (EditText)findViewById(R.id.etxAsunto);
        edtTexto = (EditText)findViewById(R.id.etxTexto);

        btnEnviar = (Button)findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cualquier aplicacion de mensajeria usa este
                //mandarCorreoSend(edtEmail.getText().toString(), edtAsunto.getText().toString(), edtTexto.getText().toString());

                //Solo las aplicaciones de emails usan este
                mandarCorreoSendTo(edtEmail.getText().toString(), edtAsunto.getText().toString(), edtTexto.getText().toString());
            }
        });

    }

    public void mandarCorreoSend(String email, String asunto, String mensaje)
    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("*/*");
        i.putExtra(Intent.EXTRA_EMAIL, email);
        i.putExtra(Intent.EXTRA_SUBJECT, asunto);
        i.putExtra(Intent.EXTRA_TEXT, mensaje);

        if(i.resolveActivity(getPackageManager())!=null)
        {
            startActivity(i);
        }
    }

    public void mandarCorreoSendTo(String email, String asunto, String mensaje)
    {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_EMAIL, email);
        i.putExtra(Intent.EXTRA_SUBJECT, asunto);
        i.putExtra(Intent.EXTRA_TEXT, mensaje);

        if(i.resolveActivity(getPackageManager())!=null)
        {
            startActivity(i);
        }
    }
}
