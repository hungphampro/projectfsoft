package com.example.root.projectfsoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.root.projectfsoft.dataBase.RealmController;
import com.example.root.projectfsoft.fragment.AboutFragment;
import com.example.root.projectfsoft.fragment.FragmentFavorite;
import com.example.root.projectfsoft.fragment.ListFamousPlaceDaNang;
import com.example.root.projectfsoft.fragment.MapPlace;
import com.example.root.projectfsoft.fragment.SettingFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import io.realm.Realm;

import static com.example.root.projectfsoft.R.id.tab_about;
import static com.example.root.projectfsoft.R.id.tab_favorite;
import static com.example.root.projectfsoft.R.id.tab_map;
import static com.example.root.projectfsoft.R.id.tab_place;
import static com.example.root.projectfsoft.R.id.tab_setting;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager;
    BottomBar bottomBar;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm= RealmController.with(this).getRealm();
        bottomBar= (BottomBar) findViewById(R.id.bottomBar);
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout,new ListFamousPlaceDaNang()).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = null;
                switch (tabId) {
                    case tab_place:
                        fragment = new ListFamousPlaceDaNang();
                        break;
                    case tab_favorite:
                        fragment = new FragmentFavorite();
                        break;
                    case tab_map:
                        fragment = new MapPlace();
                        break;
                    case tab_setting:
                        fragment = new SettingFragment();
                        break;
                    case tab_about: fragment=new AboutFragment();
                        break;
                    default:
                        fragment = new AboutFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
            }
        });
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

        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_place:
                Intent k=new Intent(getApplicationContext(),DuongDiActivity.class);
                startActivity(k);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
