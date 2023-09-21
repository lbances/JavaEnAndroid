package com.cursokotlinandroid.proyecto_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_mes;
    private EditText et_dia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_mes = findViewById(R.id.et_mes);
        et_dia = findViewById(R.id.et_dia);

    }

    public void buscar (View v) {
        Intent i=new Intent(this,MainActivity2.class);
        i.putExtra("mes", et_mes.getText().toString());
        i.putExtra("dia", et_dia.getText().toString());
        startActivity(i);
    }



}