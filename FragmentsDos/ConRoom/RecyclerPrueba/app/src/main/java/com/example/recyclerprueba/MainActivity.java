package com.example.recyclerprueba;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Android", "Android Android"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Audio", "Audio Audio"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Sun", "Sun Sun"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Android", "Android Android"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Audio", "Audio Audio"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Sun", "Sun Sun"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Android", "Android Android"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Audio", "Audio Audio"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Sun", "Sun Sun"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Android", "Android Android"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Audio", "Audio Audio"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Sun", "Sun Sun"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Android", "Android Android"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Audio", "Audio Audio"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Sun", "Sun Sun"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Android", "Android Android"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Audio", "Audio Audio"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Sun", "Sun Sun"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Android", "Android Android"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Audio", "Audio Audio"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Sun", "Sun Sun"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Android", "Android Android"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Audio", "Audio Audio"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Sun", "Sun Sun"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Android", "Android Android"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Audio", "Audio Audio"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Sun", "Sun Sun"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Android", "Android Android"));
        exampleList.add(new ExampleItem(R.drawable.ic_audio, "Audio", "Audio Audio"));
        exampleList.add(new ExampleItem(R.drawable.ic_sun, "Sun", "Sun Sun"));

        this.mRecyclerView = findViewById(R.id.myrecyclerview);
        this.mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this); //, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                exampleList.remove(viewHolder.getAdapterPosition());
                mAdapter = new ExampleAdapter(exampleList);
                mRecyclerView.setAdapter(mAdapter);
                Toast.makeText(getApplicationContext(), "borrado", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mRecyclerView);

    }
}
