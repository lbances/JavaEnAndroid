package com.cursokotlinandroid.ej_radiogroup_button;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class RADIOGROUPandBUTTON extends AppCompatActivity {
    private EditText et_nm1;
    private EditText et_nm2;
    private TextView tv_1;
    private RadioButton r1,r2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiogroupand_button);
        et_nm1 = findViewById(R.id.et_nm1);
        et_nm2 = findViewById(R.id.et_nm2);
        tv_1 = findViewById(R.id.tv_1);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
    }
    public void Operar(View view){
        int valor1 = Integer.parseInt(et_nm1.getText().toString());
        int valor2 = Integer.parseInt(et_nm2.getText().toString());
        int suma = valor1 + valor2;
        int resta = valor1-valor2;
        if(r1.isChecked()==true){
            String result = String.valueOf(suma);
            tv_1.setText(result);
        }else if(r2.isChecked()==true){
            String result = String.valueOf(resta);
            tv_1.setText(result);
        }

    }
}