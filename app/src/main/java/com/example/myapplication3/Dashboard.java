package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication3.fragment.AboutFragment;
import com.example.myapplication3.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    TextView result, confidence;
    ImageView imageView;
    Button picture;
    int imageSize = 224;

    //camera initialize
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        replaceHomeFragment();
        bottomNavigation = findViewById(R.id.bottomnavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (item.getItemId() == R.id.about) {
                    replaceAboutFragment();

                } else if (item.getItemId() == R.id.home) {
                    replaceHomeFragment();

                }
                return false;
            }
        });
    }

    // Replace HomeFragment
    private void replaceHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new HomeFragment());
        fragmentTransaction.commit();
    }

    // Replace AboutFragment
    private void replaceAboutFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new AboutFragment());
        fragmentTransaction.commit();
    }
}
