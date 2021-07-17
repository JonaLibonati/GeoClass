package com.example.geoclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ActivityInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        }

    public void aastho (View view) {
        Intent i = new Intent(this, ActivityAastho.class);
        startActivity(i);
    }

    public void sucs (View view) {
        Intent i = new Intent(this, ActivitySucs.class);
        startActivity(i);
    }

    public void linkein (View view) {

        String url = "https://www.linkedin.com/in/jonathan-libonati/";
        Uri uri = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }
}
