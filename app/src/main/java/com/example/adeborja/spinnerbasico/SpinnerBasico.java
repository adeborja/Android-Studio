package com.example.adeborja.spinnerbasico;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.TextView;

public class SpinnerBasico extends Activity implements AdapterView.OnItemSelectedListener {

    private TextView texto;
    private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_basico);
    }
}
