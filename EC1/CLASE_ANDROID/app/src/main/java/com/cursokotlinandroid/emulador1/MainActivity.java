package com.cursokotlinandroid.emulador1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp=MediaPlayer.create(this,R.raw.musica1);
        mp.start();
    }

    public void Inicio(View view)
    {
        mp.stop();
        Intent i=new Intent(this,MainActivity2.class);
        startActivity(i);
    }
}