package com.iesnervion.jobando.slidethekana;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class pruebaSlide extends AppCompatActivity {

    private static final int SWIPE_MIN_DISTANCE = 150;  //120
    private static final int SWIPE_MAX_OFF_PATH = 500;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    View touchPanel;
    TextView a, b, c, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_slide);


        


        touchPanel = findViewById(R.id.touchPanel);
        a = findViewById(R.id.opcion1);
        b = findViewById(R.id.opcion2);
        c = findViewById(R.id.opcion3);
        resultado = findViewById(R.id.pregunta);

        // Gesture detection
        gestureDetector = new GestureDetector(this, new MyGestureDetector());

        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };

        touchPanel.setOnTouchListener(gestureListener);
    }



        class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                boolean cierto = false;



                try {


                    // Izquierda
                    if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                        //Toast.makeText(pruebaSlide.this, "Left Swipe", Toast.LENGTH_SHORT).show();

                            resultado.setText(a.getText());

                            cierto = true;


                    //Derecha
                    } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                        //Toast.makeText(pruebaSlide.this, "Right Swipe", Toast.LENGTH_SHORT).show();

                            resultado.setText(c.getText());

                            cierto = true;

                    //Arriba
                    } else if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {

                        //Toast.makeText(pruebaSlide.this, "Top Swipe", Toast.LENGTH_SHORT).show();

                        resultado.setText(b.getText());

                        cierto = true;

                    //Abajo
                    } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {

                        //Toast.makeText(pruebaSlide.this, "Down Swipe", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {

                    // nothing
                }

                return cierto;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }


        }



    }


