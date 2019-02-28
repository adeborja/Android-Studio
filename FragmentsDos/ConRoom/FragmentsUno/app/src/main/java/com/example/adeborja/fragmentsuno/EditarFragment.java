package com.example.adeborja.fragmentsuno;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class EditarFragment extends Fragment
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

    private static Personaje personajeEditable;

    private boolean retratoCambiado = false;
    private Uri retratoOriginal;

    ImageView imgRetrato;
    File fRetrato = null;

    private OnFragmentInteractionListener mListener;

    public EditarFragment() {
        // Required empty public constructor
    }


    public static EditarFragment newInstance(Personaje p) {
        EditarFragment fragment = new EditarFragment();

        personajeEditable = p;

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
        View v = inflater.inflate(R.layout.fragment_editar_personaje, container, false);

        //ImageView imgRetrato = (ImageView)v.findViewById(R.id.imgRetratoDetalles);
        final EditText etxNombre = (EditText) v.findViewById(R.id.etxEditarNombre);
        final EditText etxAlias = (EditText)v.findViewById(R.id.etxEditarAlias);
        final EditText etxDesctipcion = (EditText)v.findViewById(R.id.etxEditarDesc);
        imgRetrato = (ImageView) v.findViewById(R.id.imgEditarElegirPerfil);

        etxNombre.setText(personajeEditable.getNombre());
        etxAlias.setText(personajeEditable.getAlias());
        etxDesctipcion.setText(personajeEditable.getDescripcion());
        imgRetrato.setImageURI(personajeEditable.getRetrato());
        fRetrato = new File(personajeEditable.getRetrato().toString());
        retratoOriginal = personajeEditable.getRetrato();

        Button btnEditar = (Button)v.findViewById(R.id.btnEditarAceptar);
        Button btnRetrato = (Button) v.findViewById(R.id.btnEditarElegirPerfil);

        btnEditar.setEnabled(true);
        btnEditar.setText("Editar personaje");

        btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(getActivity(),"Has pulsado crear", Toast.LENGTH_SHORT).show();

                    String nombre = etxNombre.getText().toString();
                    String alias = etxAlias.getText().toString();
                    String desc = etxDesctipcion.getText().toString();

                    Uri retrato;
                    if(retratoCambiado)
                    {
                        retrato = Uri.fromFile(fRetrato);
                    }
                    else
                    {
                        retrato = retratoOriginal;
                    }


                    mListener.onEditPersFragmentInteraction(nombre, alias, desc, retrato, null, personajeEditable.getId());
                }
            });

            btnRetrato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                    {
                        //pedirPermisoGaleria();
                        Utilidades.pedirPermisoGaleria(getActivity());
                    }
                    else
                    {
                        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));

                        startActivityForResult(intent, Utilidades.PICK_IMAGE);
                    }

                }
            });
        //}

        return v;
    }

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
        void onEditPersFragmentInteraction(String nombre, String alias, String desc, Uri retrato, List<String> imagenes, long id);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==Utilidades.PICK_IMAGE)
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

                retratoCambiado = true;
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
