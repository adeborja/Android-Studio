package com.example.adeborja.fragmentsuno;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>
{
    private List<String> listaImagenes;

    public RecyclerViewAdapter(List<String> list)
    {
        this.listaImagenes = list;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.estilo_columna, viewGroup, false);

        RecyclerViewHolder rvh = new RecyclerViewHolder(v);

        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        String item = listaImagenes.get(i);

        recyclerViewHolder.imageView.setImageURI(Uri.parse(item));
    }

    @Override
    public int getItemCount() {
        return this.listaImagenes.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageView = itemView.findViewById(R.id.cvImagen);
        }
    }
}
