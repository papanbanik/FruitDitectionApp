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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText SignUpEmail, SignUpPassword; // Declare EditText variables
    private Button SignUpButton;
    private TextView SignInText;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up Activity");

        mAuth = FirebaseAuth.getInstance();
        progressbar = findViewById(R.id.progressbar);
        SignUpEmail = findViewById(R.id.SignUpEmail);
        SignUpPassword = findViewById(R.id.SignUpPassword);
        SignUpButton = findViewById(R.id.SignUpButton); // Initialize the button
        SignInText = findViewById(R.id.SignInText);
        SignInText.setOnClickListener(this);
        SignUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.SignUpButton) {
            userRegister();
        } else if (v.getId() == R.id.SignInText) {
            Intent intent = new Intent(SignUp.this, Login.class);
            startActivity(intent);
        }
    }

    private void userRegister() {
        String email = SignUpEmail.getText().toString().trim();
        String password = SignUpPassword.getText().toString().trim();

        if (email.isEmpty()) {
            SignUpEmail.setError("Enter an email address");
            SignUpEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            SignUpEmail.setError("Enter a valid email address");
            SignUpEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            SignUpPassword.setError("Enter a password");
            SignUpPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            SignUpPassword.setError("Minimum length should be 6");
            SignUpPassword.requestFocus();
            return;
        }

        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Registration is successful", Toast.LENGTH_SHORT).show();
                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"User is already registered", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Error :" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        // Your further registration logic goes here
    }
}
