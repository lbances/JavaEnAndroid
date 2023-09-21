package com.example.proyectomapa02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
    }

    public void RUTA (View v) {
        Intent i=new Intent(this,MapsActivity.class);
        i.putExtra("latitud", et1.getText().toString());
        i.putExtra("longitud", et2.getText().toString());
        startActivity(i);
    }



}