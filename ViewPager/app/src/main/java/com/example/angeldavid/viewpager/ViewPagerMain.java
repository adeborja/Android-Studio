package com.example.angeldavid.viewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewPagerMain extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = (ViewPager)findViewById(R.id.vwpViewPager);
        miAdaptador = new SlideAdapter(this);

        viewPager.setAdapter(miAdaptador);

    }
}
