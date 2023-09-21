package com.cursokotlinandroid.clase_012;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_usuario;
    private EditText et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_usuario = findViewById(R.id.et_usuario);
        et_pass = findViewById(R.id.et_pass);
    }

    public void Validar(View view){
        String usuario = et_usuario.getText().toString().toLowerCase();
        int password = Integer.parseInt(et_pass.getText().toString());

        if(usuario.equals("idat") && password == 123){
            Intent i=new Intent(this, MainActivity2.class);
            startActivity(i);
        }else{
            Toast notificacion = Toast.makeText(this,"Error en el usuario o password", Toast.LENGTH_LONG);
            notificacion.show();
        }
    }
}