package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText txtPeso, txtAltura;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);
        txtResultado = findViewById(R.id.txtResultado);

    }

    public void calcularImc(View view) {
        String peso = txtPeso.getText().toString();
        String altura = txtAltura.getText().toString();
        double nro1 = Double.parseDouble(peso);
        double nro2 = Double.parseDouble(altura);

        double operar = nro1 / (nro2 * nro2);

        DecimalFormat df = new DecimalFormat("#.##");
        String resultadoControlado = df.format(operar);

        String resultado = String.valueOf(resultadoControlado);

        if(operar >= 0 && operar <= 18.5){
            txtResultado.setText("Su Indice de Masa Corporal es: "+resultadoControlado+ " y su condición es Bajo peso");
        }

        else if (operar >= 18.6 && operar <= 24.9){
            txtResultado.setText("Su Indice de Masa Corporal es: "+resultadoControlado+ " y su condición es Peso normal");
        }

        else if(operar >= 25 && operar <= 29.9){
            txtResultado.setText("Su Indice de Masa Corporal es: "+resultadoControlado+ " y su condición es Sobrepeso");
        }

        else if(operar >= 30 && operar <= 34.9){
            txtResultado.setText("Su Indice de Masa Corporal es: "+resultadoControlado+ " y su condición es Obesidad grado 1");
        }

        else if(operar >= 35 && operar <= 39.9){
            txtResultado.setText("Su Indice de Masa Corporal es: "+resultadoControlado+ " y su condición es Obesidad grado 2");
        }

        else if (operar >= 40) {
            txtResultado.setText("Su Indice de Masa Corporal es: "+resultadoControlado+ " y su condición es Obesidad grado 3");
        }
    }
}