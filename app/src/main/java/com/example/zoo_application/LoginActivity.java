package com.example.zoo_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText textEmail, textPassword;
    TextView btnLogin, btnFPassword, account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(LoginActivity.this, WelcomeActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        textEmail= findViewById(R.id.txtusername);
        textPassword = findViewById(R.id.txtpassword);
        btnLogin = findViewById(R.id.btnlogin);
        btnFPassword = findViewById(R.id.FPassword);
        account = findViewById(R.id.account);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                final String password = textPassword.getText().toString();
                if (email.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Email Address!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Password!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                        {
                                Toast.makeText(LoginActivity.this, "Please check whether your email or password is correctly.", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });

        btnFPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        account.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }
}