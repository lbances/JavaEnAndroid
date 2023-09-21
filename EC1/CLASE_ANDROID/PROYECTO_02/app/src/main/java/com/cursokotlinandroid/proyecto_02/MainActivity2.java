package com.cursokotlinandroid.proyecto_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView tv_signo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_signo = findViewById(R.id.tv_signo);


        Bundle bundle = getIntent().getExtras(); //forma de leer los valores enviados
        String n_mes = bundle.getString("mes");
        int n_dia = Integer.parseInt(bundle.getString("dia"));

        if(n_mes.equals("enero"))
        {
            if(n_dia>=1 && n_dia<=20) tv_signo.setText("capricornio");
            if(n_dia>=21 && n_dia<=31) tv_signo.setText("ACUARIO");
        }

    }

}