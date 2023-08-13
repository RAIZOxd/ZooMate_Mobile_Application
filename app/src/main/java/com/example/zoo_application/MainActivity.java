package com.example.zoo_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zoo_application.VA.CharacterView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NfcAdapter AdapterNFC;
    PendingIntent pendingIntent;
    IntentFilter writingTagFilters [];
    Tag Tag;
    Context context;
    boolean writeMode;
    Button button;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    private CharacterView characterView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Nav Bar
        drawerLayout= findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        characterView = findViewById(R.id.avatar_view);
        floatingActionButton = findViewById(R.id.say_it);
        floatingActionButton.setVisibility(View.INVISIBLE);
        onSayIt(characterView);

        button = findViewById(R.id.cancel_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "If you want to an information please Tap the animal name plate !", Toast.LENGTH_SHORT).show();
            }
        });
        //NFC
        context = this;
        AdapterNFC = NfcAdapter.getDefaultAdapter(this);
        readFromIntent(getIntent());
        pendingIntent = PendingIntent.getActivity(this,0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writingTagFilters = new IntentFilter[] {tagDetected};

        //Slide Show 01
        ImageView slideshow1 = findViewById(R.id.slideshow1);
        AnimationDrawable animationDrawable = (AnimationDrawable) slideshow1.getDrawable();
        animationDrawable.start();
        //Slide Show 02
        ImageView slideshow2 = findViewById(R.id.slideshow2);
        AnimationDrawable animationDrawable2 = (AnimationDrawable) slideshow2.getDrawable();
        animationDrawable2.start();
    }
    private void readFromIntent (Intent intent)
    {
        String s = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(s)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(s)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(s))
        {
            Parcelable[] arrayExtra = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgMessages = null;
            if (arrayExtra != null)
            {
                msgMessages = new NdefMessage[arrayExtra.length];
                for (int i=0; i<arrayExtra.length; i++)
                {
                    msgMessages[i] = (NdefMessage) arrayExtra[i];
                }
            }
            buildTagViews(msgMessages);
        }
    }
    private void buildTagViews (NdefMessage[] msgMessages)
    {
        if (msgMessages == null || msgMessages.length == 0) return;

        String text = "";
        byte[] payload = msgMessages[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
        int LanguageCodeLength = payload[0] & 0063;

        try
        {
            text = new String(payload, LanguageCodeLength + 1, payload.length - LanguageCodeLength - 1, textEncoding);
        }
        catch (UnsupportedEncodingException e)
        {
            Log.e("UnsupportedEncoding", e.toString());
        }

        if (text.equals("Lion"))
        {
            startActivity(new Intent(MainActivity.this, LionVActivity.class));
        }
        if (text.equals("Elephant"))
        {
            startActivity(new Intent(MainActivity.this, ElephantVActivity.class));
        }
        if (text.equals("Tiger"))
        {
            startActivity(new Intent(MainActivity.this, TigerVActivity.class));
        }
        if (text.equals("Deer"))
        {
            startActivity(new Intent(MainActivity.this, DeerVActivity.class));
        }
        if (text.equals("Pelican"))
        {
            startActivity(new Intent(MainActivity.this, PelicanVActivity.class));
        }

    }

    @Override
    protected void onNewIntent (Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
        readFromIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction()))
        {
            Tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        WriteModeOff();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        WriteModeOn();
    }

    private void WriteModeOn()
    {
        writeMode = false;
        AdapterNFC.enableForegroundDispatch(this, pendingIntent, writingTagFilters, null);
    }

    private void WriteModeOff()
    {
        writeMode = false;
        AdapterNFC.disableForegroundDispatch(this);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.nav_home:
                break;
            case R.id.bot:
                startActivity(new Intent(MainActivity.this,ChatbotActivity.class));
                break;
            case R.id.nav_logout:
                logout();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(" Are you sure want to Log Out ?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog dialog =  builder.create();
        dialog.show();

    }

    public void onSayIt(View view)
    {
        String text = "Hi there. Have a nice day. I'm your zoo Assistant. If you want know information about animals, please tap your mobile phone on the animal name plate. I will Provide the information.";
        new TextToSpeechAsync(characterView, floatingActionButton, text).execute();
    }
}