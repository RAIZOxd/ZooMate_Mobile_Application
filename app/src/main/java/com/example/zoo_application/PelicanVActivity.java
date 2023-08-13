package com.example.zoo_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PelicanVActivity extends AppCompatActivity {
    TextView taptoread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelican_vactivity);
        taptoread=findViewById(R.id.taptoreadbtn);
        taptoread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PelicanVActivity.this,PelicanIActivity.class);
                startActivity(intent);
                finish();


            }
        });
    }
}