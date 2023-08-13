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
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText textEmail;
    TextView pwdreset, goback, account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        textEmail= findViewById(R.id.txtusername);
        pwdreset = findViewById(R.id.pwdreset);
        goback = findViewById(R.id.goback);
        account = findViewById(R.id.account);

        mAuth = FirebaseAuth.getInstance();

        pwdreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = textEmail.getText().toString().trim();

                if (Email.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),
                            "Enter the email address associated with your account",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            account.setText("We have sent you instructions to reset your password!");
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),
                                    "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}