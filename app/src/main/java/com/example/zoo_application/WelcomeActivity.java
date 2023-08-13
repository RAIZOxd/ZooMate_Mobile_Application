package com.example.zoo_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView btnenjoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        btnenjoy = findViewById(R.id.btnenjoy);
        btnenjoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
    }
    private void startAnimation() {

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim);
        btnenjoy.startAnimation(animation);
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);

    }

}