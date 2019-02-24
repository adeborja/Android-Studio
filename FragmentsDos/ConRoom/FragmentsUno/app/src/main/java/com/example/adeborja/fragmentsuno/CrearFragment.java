package com.example.adeborja.fragmentsuno;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

import static android.app.Activity.RESULT_OK;

public class CrearFragment extends Fragment
{
    //el valor asignado es su clave. Si dos parametros tienen la misma clave, se sobreescriben.
    private static final String NOMBRE = "nombre";
    private static final String ALIAS = "alias";
    private static final String DESCRIPCION = "descripcion";
    private static final String RETRATO = "retrato";
    private static final String ID = "id";
    private static final String NUMERO_IMAGENES = "numero_imagenes";

    private static String nombre;
    private static String alias;
    private static String descripcion;
    private static String retrato;
    private static String id;
    private static String numero_imagenes;
    private static final int PICK_IMAGE = 100;

    ImageView imgRetrato;
    File fRetrato = null;

    private int PERMISO_LEER_GALERIA = 1;

    private OnFragmentInteractionListener mListener;

    public CrearFragment() {
        // Required empty public constructor
    }


    public static CrearFragment newInstance() {
        CrearFragment fragment = new CrearFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_crear_personaje, container, false);

        //ImageView imgRetrato = (ImageView)v.findViewById(R.id.imgRetratoDetalles);
        final EditText etxNombre = (EditText) v.findViewById(R.id.etxCrearNombre);
        final EditText etxAlias = (EditText)v.findViewById(R.id.etxCrearAlias);
        final EditText etxDesctipcion = (EditText)v.findViewById(R.id.etxCrearDesc);

        //imgRetrato.setImageResource(Integer.parseInt(retrato));
        /*txvNombre.setText(nombre);
        txvAlias.setText(alias);
        txvDesctipcion.setText(descripcion);*/

        //Para que tenga scrollbar
        //txvDesctipcion.setMovementMethod(new ScrollingMovementMethod());

        Button btnCrear = (Button)v.findViewById(R.id.btnCrearAceptar);
        Button btnRetrato = (Button) v.findViewById(R.id.btnElegirPerfil);
        imgRetrato = (ImageView) v.findViewById(R.id.imgElegirPerfil);

        //int imagenes = Integer.parseInt(numero_imagenes);


        //TODO: Esto hay que cambiarlo, quizas quitarlo, para hacer que el boton de crear
        // solo este activo cuando se haya introducido al menos nombre y alias
        /*if(nombre == "" || alias == "")
        {
            btnCrear.setEnabled(false);
            btnCrear.setText(R.string.crear_vacio);
        }
        else
        {*/
            btnCrear.setEnabled(true);
            btnCrear.setText(R.string.crear_personaje);

            btnCrear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(getActivity(),"Has pulsado crear", Toast.LENGTH_SHORT).show();

                    //Uri retrato = Uri.fromFile(fRetrato);

                    mListener.onCrearPersFragmentInteraction(etxNombre.getText().toString(), etxAlias.getText().toString(), etxDesctipcion.getText().toString(), Uri.fromFile(fRetrato), null);
                }
            });

            btnRetrato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                    {
                        pedirPermisoGaleria();
                    }
                    else
                    {
                        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));

                        startActivityForResult(intent, PICK_IMAGE);
                    }

                    /*Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));

                    startActivityForResult(intent, PICK_IMAGE);*/
                }
            });
        //}

        return v;
    }

    private void pedirPermisoGaleria()
    {
        /*if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(getContext())
                    .setTitle("Necesario permiso")
                    .setMessage("Este permiso es necesario para seleccionar imagenes de tu galería")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISO_LEER_GALERIA);
                        }
                    })
                    .setNegativeButton("no ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
        }
        else
        {*/
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISO_LEER_GALERIA);
        //}
    }

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISO_LEER_GALERIA)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED)
            {
                Toast.makeText(getActivity().getApplicationContext(), "Permiso denegado. No se podrá añadir una imagen.", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    @Override
    public void onActivityCreated(Bundle b)
    {
        super.onActivityCreated(b);

        /*if (getArguments() != null) {
            nombre = getArguments().getString(NOMBRE);
            alias = getArguments().getString(ALIAS);
            descripcion = getArguments().getString(DESCRIPCION);
            retrato = getArguments().getString(RETRATO);
        }*/
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(View v) {
        if (mListener != null) {
            mListener.onDetFragmentInteraction(v);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onCrearPersFragmentInteraction(String nombre, String alias, String desc, Uri retrato, ListaImagenes imagenes);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE)
        {
            Uri uri = data.getData();
            String aux = getPath(uri);
            //Toast.makeText(getContext(), aux, Toast.LENGTH_SHORT).show();

            //Bitmap bm = BitmapFactory.decodeFile(aux);
            //imgRetrato.setImageBitmap(bm);

            fRetrato = new File(aux);

            //try
            //{
                imgRetrato.setImageURI(Uri.fromFile(fRetrato));
            /*}
            catch (FileNotFoundException e)
            {
                Toast.makeText(getContext(), "No tienes permisos suficientes", Toast.LENGTH_SHORT).show();
            }*/
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
            cursor = getContext().getContentResolver().query(uri, proyeccion, null,null,null);

            if(cursor!=null)
            {
                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                path = cursor.getString(columnIndex);
                cursor.close();
            }
            else
            {
                path = uri.getPath();
            }
        }

        return path;
    }
}
