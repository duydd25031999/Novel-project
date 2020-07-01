package com.tutorial.novelproject;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.tutorial.novelproject.ui.list.ListFragment;
import com.tutorial.novelproject.ui.listnovel.ListNovelFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        );

        drawer.addDrawerListener(toggle);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                new ListFragment()).commit();
            toolbar.setTitle("Danh sách");
            navigationView.setCheckedItem(R.id.nav_list);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_list_novel:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    new ListNovelFragment()).commit();
                toolbar.setTitle("Trang chủ");
                break;
            case R.id.nav_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    new ListFragment()).commit();
                toolbar.setTitle("Danh sách");
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
