package com.example.zoo_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DeerVActivity extends AppCompatActivity {
    TextView taptoread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deer_vactivity);
        taptoread=findViewById(R.id.taptoreadbtn);
        taptoread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeerVActivity.this,DeerIActivity.class);
                startActivity(intent);
                finish();


            }
        });
    }
}