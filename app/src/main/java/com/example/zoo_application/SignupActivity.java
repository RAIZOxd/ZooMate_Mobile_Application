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

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText txtEmail, txtPassword,txtCPassword;
    TextView btnregister,signupaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtEmail= findViewById(R.id.txtemail);
        txtPassword = findViewById(R.id.txtpassword);
        txtCPassword = findViewById(R.id.txtcpassword);
        btnregister =findViewById(R.id.btnregister);
        signupaccount= findViewById(R.id.signupaccount);

        mAuth = FirebaseAuth.getInstance();


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email= txtEmail.getText().toString();
                String Password = txtPassword.getText().toString();
                String CPassword = txtCPassword.getText().toString();

                if (Email.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter Email address!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please enter Password!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (! CPassword.equals(Password))
                {
                    Toast.makeText(getApplicationContext(), "Your password and confirmation password do not match.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(SignupActivity.this,
                        new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful())
                        {
                            Toast.makeText(SignupActivity.this,
                                    "Please check whether your email or password is correctly.", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        });
        signupaccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });



    }
}