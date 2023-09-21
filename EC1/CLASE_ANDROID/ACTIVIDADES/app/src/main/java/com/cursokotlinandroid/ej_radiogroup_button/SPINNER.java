package com.cursokotlinandroid.ej_radiogroup_button;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SPINNER extends AppCompatActivity {
    private EditText et1, et2;
    private Spinner spinner1;
    private TextView tv1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        tv1 = findViewById(R.id.tv1);

        spinner1 = findViewById(R.id.spinner1);
        String []opciones={"sumar","restar","dividir","multiplicar"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);
    }

    public void operar(View view){
        int nro1 = Integer.parseInt(et1.getText().toString());
        int nro2 = Integer.parseInt(et2.getText().toString());
        String selec = spinner1.getSelectedItem().toString();
        if(selec.equals("sumar")){
            int suma = nro1 + nro2;
            String resul = String.valueOf(suma);
            tv1.setText(resul);
        }else if(selec.equals("restar")){
            int resta = nro1 - nro2;
            String resul = String.valueOf(resta);
            tv1.setText(resul);
        }else if(selec.equals("dividir")){
            int dividir = nro1 / nro2;
            String resul = String.valueOf(dividir);
            tv1.setText(resul);
        }else if(selec.equals("multiplicar")){
            int mult = nro1 * nro2;
            String resul = String.valueOf(mult);
            tv1.setText(resul);
        }

    }
}