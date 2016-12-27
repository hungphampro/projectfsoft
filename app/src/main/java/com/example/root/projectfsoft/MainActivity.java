package com.example.root.projectfsoft;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.root.projectfsoft.dataBase.RealmController;
import com.example.root.projectfsoft.fragment.FragmentFavorite;
import com.example.root.projectfsoft.fragment.ListFamousPlaceDaNang;
import com.example.root.projectfsoft.fragment.MapPlace;
import com.example.root.projectfsoft.model.PlaceFavorite;
import com.example.root.projectfsoft.model.ReviewPlace;
import com.example.root.projectfsoft.model.ShowImage;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm= RealmController.with(this).getRealm();
        AlertDialog.Builder chido=new AlertDialog.Builder(this);
        chido.setIcon(android.R.drawable.btn_star);
        chido.setMessage("Bạn Có muốn xóa hết danh sách các địa điểm ở trong danh sách yêu thích không");
        chido.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                realm.beginTransaction();
                realm.clear(PlaceFavorite.class);
                realm.commitTransaction();
                realm.beginTransaction();
                realm.clear(ShowImage.class);
                realm.commitTransaction();
                realm.beginTransaction();
                realm.clear(ReviewPlace.class);
                realm.commitTransaction();
            }
        });
        chido.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        chido.show();
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout,new ListFamousPlaceDaNang()).commit();
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
        Fragment fragment=null;
        switch (item.getItemId()){
            case R.id.nav_place:
                fragment=new MapPlace();
                break;
            case R.id.nav_favorite: fragment=new FragmentFavorite();
                break;
            case R.id.nav_map: break;
            case R.id.nav_about:break;
            case R.id.nav_setting: break;
            default: fragment=new ListFamousPlaceDaNang();
        }
        fragmentManager.beginTransaction().replace(R.id.framelayout,fragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
