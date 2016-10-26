package com.example.ngers.cursvalute;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.ngers.cursvalute.databinding.ActivityMainBinding;
import com.example.ngers.cursvalute.presenter.MainPresenter;
import com.example.ngers.cursvalute.view.ConverterValuteFragment;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainPresenter = new MainPresenter();
        mainPresenter.create(this);

        openConverterValuteFragment();
        setupRightMenu();
    }

    void openConverterValuteFragment() {
        getFragmentManager().beginTransaction()
                .replace(binding.fragment.getId(), new ConverterValuteFragment())
                .commit();
    }

    private void setupRightMenu() {
        binding.drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                hideKeyboard(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                View view = item.getActionView();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.converter:
                        openConverterValuteFragment();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.pause();
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
