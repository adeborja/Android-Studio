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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class CrearFragment extends Fragment
{

    ImageView imgRetrato;
    File fRetrato = null;

    private List<String> galeriaImagenes;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

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

        final EditText etxNombre = (EditText) v.findViewById(R.id.etxCrearNombre);
        final EditText etxAlias = (EditText)v.findViewById(R.id.etxCrearAlias);
        final EditText etxDesctipcion = (EditText)v.findViewById(R.id.etxCrearDesc);


        Button btnCrear = (Button)v.findViewById(R.id.btnCrearAceptar);
        Button btnRetrato = (Button) v.findViewById(R.id.btnElegirPerfil);
        Button btnGaleria = (Button) v.findViewById(R.id.btnElegirGaleria);
        imgRetrato = (ImageView) v.findViewById(R.id.imgElegirPerfil);

        galeriaImagenes = new ArrayList<>();

        btnCrear.setEnabled(true);
        btnCrear.setText(R.string.crear_personaje);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri retrato = null;

                //Si ocurre un error en el proceso de asignacion de la imagen seleccionada, se asigna una imagen generica
                try
                {
                    retrato = Uri.fromFile(fRetrato);
                }
                catch (NullPointerException e)
                {
                    retrato = Utilidades.getUriToDrawable(getContext(), R.drawable.ic_launcher_background);
                }

                mListener.onCrearPersFragmentInteraction(etxNombre.getText().toString(), etxAlias.getText().toString(), etxDesctipcion.getText().toString(), retrato, galeriaImagenes);
            }
        });

        btnRetrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {
                    Utilidades.pedirPermisoGaleria(getActivity());
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));

                    startActivityForResult(intent, Utilidades.PICK_IMAGE);
                }
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {
                    Utilidades.pedirPermisoGaleria(getActivity());
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));

                    startActivityForResult(intent, Utilidades.PICK_GALLERY);
                }
            }
        });

        this.recyclerView = v.findViewById(R.id.rvCrear);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        this.recyclerViewAdapter = new RecyclerViewAdapter(galeriaImagenes);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(recyclerViewAdapter);

        //Esto deshabilita el scroll de la vista principal cuando se interactua con la galeria de imagenes
        this.recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        //Esto hace que al arrastrar hacia abajo un objeto de la galeria, este sea borrado
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.DOWN) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                galeriaImagenes.remove(viewHolder.getAdapterPosition());
                recyclerViewAdapter = new RecyclerViewAdapter(galeriaImagenes);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        }).attachToRecyclerView(recyclerView);

        return v;
    }


    @Override
    public void onActivityCreated(Bundle b)
    {
        super.onActivityCreated(b);
    }

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
        void onCrearPersFragmentInteraction(String nombre, String alias, String desc, Uri retrato, List<String> imagenes);
    }

    //Se sobreescribe este metodo para especificar que hacer al devolver la respuesta de preguntar si hay acceso para elegir una imagen del dispositivo
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            Uri uri;
            String aux;

            switch (requestCode)
            {
                case Utilidades.PICK_IMAGE:

                    uri = data.getData();
                    aux = Utilidades.getPath(uri, getContext());

                    fRetrato = new File(aux);

                    imgRetrato.setImageURI(Uri.fromFile(fRetrato));

                    break;

                case Utilidades.PICK_GALLERY:

                    uri = data.getData();
                    aux = Utilidades.getPath(uri, getContext());

                    galeriaImagenes.add(aux);

                    recyclerViewAdapter = new RecyclerViewAdapter(galeriaImagenes);
                    recyclerView.setAdapter(recyclerViewAdapter);

                    break;
            }
        }
    }

}
