package com.example.hamburgermenu;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        //Poner nuestro toolbar como actionbar
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AlertFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_alert);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.nav_alert:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new AlertFragment())
                        .commit();
                break;

            case R.id.nav_cart:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CartFragment())
                        .commit();
                break;

            case R.id.nav_favorite:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FavoriteFragment())
                        .commit();
                break;

            case R.id.nav_pizza:
                Toast.makeText(this,"Esta rica",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_pedido:
                Toast.makeText(this,"No tienes un duro",Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}
