package com.example.ejercicio22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText txtIdAlumno, txtNombreAlumno, txtNota1, txtNota2, txtNota3, txtNota4;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtIdAlumno=findViewById(R.id.txtIdAlumno);
        txtNombreAlumno=findViewById(R.id.txtNombreAlumno);
        txtNota1=findViewById(R.id.txtNota1);
        txtNota2=findViewById(R.id.txtNota2);
        txtNota3=findViewById(R.id.txtNota3);
        txtNota4=findViewById(R.id.txtNota4);
        txtResultado=findViewById(R.id.txtResultado);

    }

    public void registro(View v) {
        bdAlumnos baseAlumnos = new bdAlumnos(this,"administracion", null, 1);
        SQLiteDatabase bd = baseAlumnos.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("idAlu", txtIdAlumno.getText().toString());
        registro.put("nomAlu", txtNombreAlumno.getText().toString());
        registro.put("nota1", txtNota1.getText().toString());
        registro.put("nota2", txtNota2.getText().toString());
        registro.put("nota3", txtNota3.getText().toString());
        registro.put("nota4", txtNota4.getText().toString());
        bd.insert("alumnos", null, registro);
        bd.close();
        limpiarCampos();
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
    }

    public void consultar(View v){
        bdAlumnos baseAlumnos = new bdAlumnos(this, "administracion", null, 1);
        SQLiteDatabase bd = baseAlumnos.getWritableDatabase();
        String codigo = txtIdAlumno.getText().toString();
        Cursor fila = bd.rawQuery("select nomAlu, nota1, nota2, nota3, nota4 from alumnos where idAlu = " + codigo, null);

        if(fila.moveToFirst()){
            txtNombreAlumno.setText(fila.getString(0));
            txtNota1.setText(fila.getString(1));
            txtNota2.setText(fila.getString(2));
            txtNota3.setText(fila.getString(3));
            txtNota4.setText(fila.getString(4));

            String name = txtNombreAlumno.toString();
            double nota1 = Double.parseDouble(fila.getString(1));
            double nota2 = Double.parseDouble(fila.getString(2));
            double nota3 = Double.parseDouble(fila.getString(3));
            double nota4 = Double.parseDouble(fila.getString(4));

            double promedio = (nota1 + nota2 + nota3 + nota4) / 4;

            DecimalFormat df = new DecimalFormat("#.##");
            String resultadoControlado = df.format(promedio);

            String resultado = String.valueOf(resultadoControlado);

            if (promedio >= 0 && promedio <= 12)
                txtResultado.setText("El promedio del alumno es "+resultadoControlado+". Entonces la condición es Desaprobado.");

            else if (promedio >= 13 && promedio <= 20)
                txtResultado.setText("El promedio del alumno es "+resultadoControlado+". Entonces la condición es Aprobado.");

        } else Toast.makeText(this, "No existe el código ingresado",Toast.LENGTH_SHORT).show();
    }

    public void eliminar(View v) {
        bdAlumnos baseAlumnos = new bdAlumnos(this, "administracion", null, 1);
        SQLiteDatabase bd = baseAlumnos.getWritableDatabase();
        String codigo = txtIdAlumno.getText().toString();
        int comando = bd.delete("alumnos", "idAlu =" + codigo, null);
        bd.close();
        limpiarCampos();
        if (comando == 1)
            Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe el código ingresado", Toast.LENGTH_SHORT).show();
    }

    public void modificar(View v) {
        bdAlumnos baseAlumnos = new bdAlumnos(this, "administracion", null, 1);
        SQLiteDatabase bd = baseAlumnos.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("idAlu", txtIdAlumno.getText().toString());
        registro.put("nomAlu", txtNombreAlumno.getText().toString());
        registro.put("nota1", txtNota1.getText().toString());
        registro.put("nota2", txtNota2.getText().toString());
        registro.put("nota3", txtNota3.getText().toString());
        registro.put("nota4", txtNota4.getText().toString());
        int comando = bd.update("alumnos",registro, "idAlu =" + txtIdAlumno.getText().toString(),null);
        bd.close();
        limpiarCampos();
        if (comando == 1)
            Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe el código ingresado", Toast.LENGTH_SHORT).show();
    }

    public void limpiarCampos(){
        txtIdAlumno.setText("");
        txtNombreAlumno.setText("");
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");
        txtNota4.setText("");
        txtResultado.setText("");
    }
}