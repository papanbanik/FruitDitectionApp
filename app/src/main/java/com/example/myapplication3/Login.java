package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText SignInEmail, SignInPassword;
    private TextView SignUpText;
    private Button SignInButton;
    ProgressBar progressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setTitle("Sign In Activity");
        mAuth=FirebaseAuth.getInstance();
        SignInEmail = findViewById(R.id.SignInEmail);
        SignInPassword = findViewById(R.id.SignInPassword);
        Button signInButton = findViewById(R.id.SignInButton); // Initialize the button
        TextView signUpText = findViewById(R.id.SignUpText);
        progressbar=findViewById(R.id.progressbar);
        signUpText.setOnClickListener(this);
        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.SignInButton) {
            userLogin();
        }
        else if (v.getId() == R.id.SignUpText) {
            Intent intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);
        }
    }

    private void userLogin() {
        String email = SignInEmail.getText().toString().trim();
        String password = SignInPassword.getText().toString().trim();

        if (email.isEmpty()) {
            SignInEmail.setError("Enter an email address");
            SignInEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            SignInEmail.setError("Enter a valid email address");
            SignInEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            SignInPassword.setError("Enter a password");
            SignInPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            SignInPassword.setError("Minimum length should be 6");
            SignInPassword.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Login Unsuccessfull", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}