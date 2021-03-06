package com.dev.nessotech.blankproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.dev.nessotech.blankproject.navfragments.MissingPageFragment;
import com.github.mikephil.charting.charts.BarChart;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //butterknife sample init
    @BindView(R.id.content_main) FrameLayout content_main;
    @BindView(R.id.bar_graph) BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //bind activity to butterknife
        ButterKnife.bind(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        String name = item.getTitleCondensed().toString().trim();
        if (name.equals("")) {
            name = item.getTitle().toString().trim();
        }
        name = name.replaceAll(" ", "");

        /*
        If item is checkable, the content is a fragment and will be displayed in the main content layout.
        Otherwise, the content is an intent or activity that can be launched separately.
        */
        if (item.isCheckable()) {
            try {
                Class<?> cls = Class.forName(getPackageName().concat(".navfragments.").concat(name.concat("Fragment")));
                Fragment fragment = (Fragment) cls.getConstructor().newInstance();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(content_main.getId(), fragment);
                ft.commit();
            } catch (ClassNotFoundException ex) {
                Fragment fragment = new MissingPageFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(content_main.getId(), fragment);
                ft.commit();
            } catch (NoSuchMethodException ex) {
                logE("No such method. " + ex.getMessage());
            } catch (Exception ex) {
                logE(ex.getMessage());
            }
        } else {
            try {
                Class<?> cls = Class.forName(getPackageName().concat(".navactivities.").concat(name.concat("Activity")));
                Intent intent = new Intent(this, cls);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                logE(ex.getMessage());
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
