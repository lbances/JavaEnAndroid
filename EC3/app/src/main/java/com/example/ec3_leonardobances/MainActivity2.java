package com.example.ec3_leonardobances;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ListView lstCuidades;
    private ArrayList<String> listaDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lstCuidades = (ListView)findViewById(R.id.lstCuidades);
        listaDatos = new ArrayList<>();



        AdminDb admin = new AdminDb(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select id_cuidad, cuidad, latitud, longitud from cuidades ", null);

        if(fila.moveToFirst()){
            do{
                String dato = fila.getString(0) + " - " + fila.getString(1) + " - " + fila.getString(2) + " - " + fila.getString(3);
                listaDatos.add(dato);
            }while(fila.moveToNext());
        }
        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDatos);
        lstCuidades.setAdapter(adapter);

        lstCuidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                abrirMapsActivity(position); // Llama a la función para abrir MapsActivity con la posición de la lista seleccionada
            }
        });
    }

    private void abrirMapsActivity(int position) {
        Intent i = new Intent(this, MapsActivity.class);

        // Obtén la posición seleccionada de la lista y extrae los datos de latitud y longitud
        String datoSeleccionado = listaDatos.get(position);
        String[] partes = datoSeleccionado.split(" - ");
        if (partes.length >= 4) {
            double latitud = Double.parseDouble(partes[2]);
            double longitud = Double.parseDouble(partes[3]);

            // Envía los valores de latitud y longitud como extras en el intent
            i.putExtra("latitud", latitud);
            i.putExtra("longitud", longitud);
            startActivity(i);
        }
    }

    public void mapas(View view) {
        Intent i = new Intent(this, MapsActivity.class );
        startActivity(i);
    }
}