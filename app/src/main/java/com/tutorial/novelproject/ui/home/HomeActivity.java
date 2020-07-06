package com.tutorial.novelproject.ui.home;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.tutorial.novelproject.R;
import com.tutorial.novelproject.ui.download.DownloadFragment;
import com.tutorial.novelproject.ui.list.ListFragment;
import com.tutorial.novelproject.ui.home.ListNovelFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
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
            case R.id.nav_download:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    new DownloadFragment()).commit();
                toolbar.setTitle("Download");
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
