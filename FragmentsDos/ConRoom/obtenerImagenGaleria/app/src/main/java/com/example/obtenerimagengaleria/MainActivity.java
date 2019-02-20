package com.example.obtenerimagengaleria;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button boton;
    private static final int PICK_IMAGE=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.button);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));

                startActivityForResult(i, PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE)
        {
            Uri uri = data.getData();
            String aux = getPath(uri);
            Toast.makeText(getApplicationContext(), aux, Toast.LENGTH_SHORT).show();
        }
    }

    public String getPath(Uri uri)
    {
        String path = null;
        String[] proyeccion = null;
        Cursor cursor = null;
        int columnIndex = 0;

        if(uri!=null)
        {
            proyeccion = new String[]{MediaStore.Images.Media.DATA};
            cursor = managedQuery(uri, proyeccion, null,null,null);

            if(cursor!=null)
            {
                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                path = cursor.getString(columnIndex);
            }
            else
            {
                path = uri.getPath();
            }
        }

        return path;
    }
}
