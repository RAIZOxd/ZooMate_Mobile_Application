package com.example.zoo_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zoo_application.VA.CharacterView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class ElephantIActivity extends AppCompatActivity {
    TextView information,animalname;
    ImageView chatbot,backbtn,sharebtn;
    private CharacterView characterView;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elephant_iactivity);

        information = findViewById(R.id.informationanimal);
        animalname= findViewById(R.id.animalname);
        backbtn= findViewById(R.id.backbtn);
        chatbot= findViewById(R.id.chatbot);
        sharebtn = findViewById(R.id.sharebtn);
        characterView = findViewById(R.id.avatar_view);
        floatingActionButton = findViewById(R.id.say_it);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElephantIActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElephantIActivity.this,ChatbotActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final int[] checkedItem = {-1};
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ElephantIActivity.this);
        alertDialog.setTitle("Choose your age category ?");
        final String[] listItems = new String[]{"Kids", "Adults"};

        alertDialog.setSingleChoiceItems(listItems, checkedItem[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                checkedItem[0] = which;

                if(listItems[which]=="Kids")
                {
                    information.setText(R.string.kidselephant);

                    floatingActionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            floatingActionButton.setEnabled(false);
                            new TextToSpeechAsync(characterView, floatingActionButton,information.getText().toString()).execute();
                        }
                    });
                    sharebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT,information.getText().toString());
                            intent.setType("text/plain");
                            startActivity(intent);
                        }
                    });

                }
                if(listItems[which]=="Adults")
                {
                    information.setText(R.string.adultselephant);
                    floatingActionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            floatingActionButton.setEnabled(false);
                            new TextToSpeechAsync(characterView, floatingActionButton,information.getText().toString()).execute();
                        }
                    });
                    sharebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_TEXT,information.getText().toString());
                            intent.setType("text/plain");
                            startActivity(intent);
                        }
                    });

                }

                dialog.dismiss();
            }
        });

        alertDialog.setNegativeButton("Cacel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog customAlertDialog = alertDialog.create();
        customAlertDialog.show();

        animalname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                customAlertDialog.show();
                return false;
            }
        });
    }

}