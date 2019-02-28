package com.example.recyclerprueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ExampleItem> exampleList = new ArrayList<>();
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

    }
}
