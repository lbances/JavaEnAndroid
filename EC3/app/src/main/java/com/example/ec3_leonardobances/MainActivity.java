package com.example.ec3_leonardobances;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtId, txtCiudad, txtLatitud, txtlongitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = findViewById(R.id.txtId);
        txtCiudad = findViewById(R.id.txtCiudad);
        txtLatitud = findViewById(R.id.txtLatitud);
        txtlongitud = findViewById(R.id.txtlongitud);

    }

    public void registrar(View v) {
        AdminDb admin = new AdminDb(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id_cuidad", txtId.getText().toString());
        registro.put("cuidad", txtCiudad.getText().toString());
        registro.put("latitud", txtLatitud.getText().toString());
        registro.put("longitud", txtlongitud.getText().toString());
        bd.insert("cuidades", null, registro);
        bd.close();
        txtId.setText("");
        txtCiudad.setText("");
        txtLatitud.setText("");
        txtlongitud.setText("");
        Toast.makeText(this, "Se registró la cuidad correctamente", Toast.LENGTH_SHORT).show();
    }

    public void consultar(View v) {
        AdminDb admin = new AdminDb(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = txtId.getText().toString();
        Cursor fila = bd.rawQuery(
                "select cuidad, latitud, longitud from cuidades where id_cuidad=" + id, null);
        if (fila.moveToFirst()) {
            txtCiudad.setText(fila.getString(0));
            txtLatitud.setText(fila.getString(1));
            txtlongitud.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una ciodad con dicho código", Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void segundoAct(View view) {
        Intent i = new Intent(this, MainActivity2.class );
        startActivity(i);
    }
}