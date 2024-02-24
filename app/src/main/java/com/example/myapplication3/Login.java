package com.example.myapplication3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication3.fragment.AboutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication3.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    //BottomNavigationView bottomNavigation;

    private EditText usernameId, onlypasswordId;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            usernameId = findViewById(R.id.usernameId);
            onlypasswordId = findViewById(R.id.onlypasswordId);
            button = findViewById(R.id.button); // Initialize the button

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String username = usernameId.getText().toString();
                        String password = onlypasswordId.getText().toString();
                        if (username.equals("admin") && password.equals("AppLab2024")) {
                            Intent intent = new Intent(Login.this, Dashboard.class);
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


}