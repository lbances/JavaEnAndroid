package com.cursokotlinandroid.ej_radiogroup_button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class CHEXBOX extends AppCompatActivity {
    private EditText et_v1,et_v2;
    private CheckBox cb_suma,cb_resta;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chexbox);
        et_v1 = findViewById(R.id.et_v1);
        et_v2 = findViewById(R.id.et_v2);
        cb_suma = findViewById(R.id.cb_suma);
        cb_resta = findViewById(R.id.cb_resta);
        tv_result = findViewById(R.id.tv_result);
    }
    public void Operar(View view){
        int valor1 = Integer.parseInt(et_v1.getText().toString());
        int valor2 = Integer.parseInt(et_v2.getText().toString());
        int suma = valor1 + valor2;
        int resta = valor1 - valor2;
        if(cb_suma.isChecked()==true){
            String result = String.valueOf(suma);
            tv_result.setText(result);
        }else if(cb_resta.isChecked()==true){
            String result = String.valueOf(resta);
            tv_result.setText(result);
        }
    }
}