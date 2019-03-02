package com.example.adeborja.fragmentsuno;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class EditarFragment extends Fragment
{

    private static Personaje personajeEditable;

    private boolean retratoCambiado = false;
    private Uri retratoOriginal;

    private ImageView imgRetrato;
    private File fRetrato = null;

    private List<String> galeriaImagenes;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

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

        galeriaImagenes = personajeEditable.getListImagenes();

        Button btnEditar = (Button)v.findViewById(R.id.btnEditarAceptar);
        Button btnRetrato = (Button) v.findViewById(R.id.btnEditarElegirPerfil);
        Button btnGaleria = (Button) v.findViewById(R.id.btnEditarElegirGaleria);

        btnEditar.setEnabled(true);
        btnEditar.setText("Editar personaje");

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


                mListener.onEditPersFragmentInteraction(nombre, alias, desc, retrato, galeriaImagenes, personajeEditable.getId());
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


        this.recyclerView = v.findViewById(R.id.rvEditar);
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
        void onEditPersFragmentInteraction(String nombre, String alias, String desc, Uri retrato, List<String> imagenes, long id);
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

                    retratoCambiado = true;

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
