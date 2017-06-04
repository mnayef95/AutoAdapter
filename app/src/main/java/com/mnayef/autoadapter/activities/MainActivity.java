package com.mnayef.autoadapter.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mnayef.autoadapter.R;
import com.mnayef.autoadapter.fragments.AllFragment;
import com.mnayef.autoadapter.fragments.FrescoFragment;
import com.mnayef.autoadapter.fragments.GifFragment;
import com.mnayef.autoadapter.fragments.LinkPreviewFragment;
import com.mnayef.autoadapter.fragments.MixFragment;
import com.mnayef.autoadapter.fragments.PFWithoutProgressFragment;
import com.mnayef.autoadapter.fragments.PicassoFragment;
import com.mnayef.autoadapter.fragments.PicassoFrescoFragment;
import com.mnayef.autoadapter.fragments.VideoFragment;
import com.mnayef.autoadapter.utils.AppConstants;
import com.mnayef.autoadapter.utils.PermissionsUtils;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.nav_fresco).setChecked(true);

        if (PermissionsUtils.checkPermission(this, AppConstants.READ_STORAGE, AppConstants.WRITE_STORAGE)) {
            try {
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, FrescoFragment.class.newInstance()).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            PermissionsUtils.request(this, 100, AppConstants.READ_STORAGE, AppConstants.WRITE_STORAGE);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (PermissionsUtils.checkPermission(this, AppConstants.READ_STORAGE, AppConstants.WRITE_STORAGE)) {
            Class<? extends Fragment> fragmentClass;
            switch (item.getItemId()) {
                case R.id.nav_fresco:
                    fragmentClass = FrescoFragment.class;
                    break;
                case R.id.nav_picasso:
                    fragmentClass = PicassoFragment.class;
                    break;
                case R.id.nav_f_and_p:
                    fragmentClass = PicassoFrescoFragment.class;
                    break;
                case R.id.nav_f_and_p_without_progress:
                    fragmentClass = PFWithoutProgressFragment.class;
                    break;
                case R.id.nav_gif:
                    fragmentClass = GifFragment.class;
                    break;
                case R.id.nav_video:
                    fragmentClass = VideoFragment.class;
                    break;
                case R.id.nav_all:
                    fragmentClass = AllFragment.class;
                    break;
                case R.id.nav_links:
                    fragmentClass = LinkPreviewFragment.class;
                    break;
                default:
                    fragmentClass = MixFragment.class;
                    break;
            }
            try {
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragmentClass.newInstance()).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            drawer.closeDrawer(GravityCompat.START);
        } else {
            PermissionsUtils.request(this, 100, AppConstants.READ_STORAGE, AppConstants.WRITE_STORAGE);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionsUtils.checkPermission(this, AppConstants.READ_STORAGE, AppConstants.WRITE_STORAGE)) {
            try {
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, FrescoFragment.class.newInstance()).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
