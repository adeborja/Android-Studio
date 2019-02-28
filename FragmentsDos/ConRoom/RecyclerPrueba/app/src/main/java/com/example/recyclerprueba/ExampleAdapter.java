package com.example.recyclerprueba;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>
{
    private ArrayList<ExampleItem> exampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView imageView;
        public TextView text1, text2;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            text1 = itemView.findViewById(R.id.txvLine1);
            text2 = itemView.findViewById(R.id.txvLine2);
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> list)
    {
        this.exampleList = list;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_item, viewGroup, false);

        ExampleViewHolder evh = new ExampleViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ExampleItem currentItem = exampleList.get(i);

        exampleViewHolder.imageView.setImageResource(currentItem.getmImageResource());
        exampleViewHolder.text1.setText(currentItem.getmText1());
        exampleViewHolder.text2.setText(currentItem.getmText2());
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    public ExampleItem getItemAt(int posicion)
    {
        return this.exampleList.get(posicion);
    }

    public void removeItem(int pos)
    {
        this.exampleList.remove(pos);
    }
}
