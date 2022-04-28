package com.example.seccion_08_navigation_drawer.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.seccion_08_navigation_drawer.Fragments.AlertsFragment;
import com.example.seccion_08_navigation_drawer.Fragments.EmailFragment;
import com.example.seccion_08_navigation_drawer.Fragments.InfoFragment;
import com.example.seccion_08_navigation_drawer.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    final int menu_mailID = R.id.menu_mail;
    final int menu_alertID = R.id.menu_alert;
    final int menu_infoID = R.id.menu_info;
    final int menu_option1ID = R.id.menu_option1;
    final int menu_option2ID = R.id.menu_option2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setToolbar();
        setFragmentByDefault();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                Toast.makeText(MainActivity.this, "Drawer Opened",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Toast.makeText(MainActivity.this, "Drawer Closed",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case menu_mailID:
                        fragment = new EmailFragment();
                        fragmentTransaction = true;
                        break;
                    case menu_alertID:
                        fragment = new AlertsFragment();
                        fragmentTransaction = true;
                        break;
                    case menu_infoID:
                        fragment = new InfoFragment();
                        fragmentTransaction = true;
                        break;
                    case menu_option1ID:
                        Toast.makeText(MainActivity.this, "Has clickeado la Opción 1",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case menu_option2ID:
                        Toast.makeText(MainActivity.this, "Has clickeado la Opción 2",
                                Toast.LENGTH_SHORT).show();
                        break;
                }

                if (fragmentTransaction) {
                    changeFragment(fragment, item);
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    private void setToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Para habilitar el click del Boton Hamburguesa

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Abrir el menu lateral
                //START PORQUE ABRE DESDE LA DERECHA
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFragmentByDefault() {
        changeFragment(new EmailFragment(), navigationView.getMenu().getItem(0));
    }

    private void changeFragment(Fragment fragment, MenuItem item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());

    }
}