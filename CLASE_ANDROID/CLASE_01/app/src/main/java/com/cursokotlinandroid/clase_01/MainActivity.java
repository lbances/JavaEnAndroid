package com.cursokotlinandroid.clase_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et_n1;
    private EditText et_n2;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_n1 = findViewById(R.id.et_n1);
        et_n2 = findViewById(R.id.et_n2);
        tv_result = findViewById(R.id.tv_result);
    }
    public void Sumar(View view) {
        int nro1=Integer.parseInt(et_n1.getText().toString());
        int nro2=Integer.parseInt(et_n2.getText().toString());
        int suma=nro1+nro2;
        String resul=String.valueOf(suma);
        tv_result.setText(resul);
    }
}