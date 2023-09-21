package com.cursokotlinandroid.emulador1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private EditText etNombre;
    private ImageView imageView;

    private String[] nombresPokemons = {"PIKACHU", "CHARIZARD", "BULBASAUR", "METAPOD", "SQUIRTLE"};
    private String[] imagenesPokemons = {"pikachu", "charizard", "bulbasaur", "metapod", "squirtle"};
    private String[] imagenesPokemonsColor = {"pikachu2", "charizard2", "bulbasaur2", "metapod2", "squirtle2"};
    private int index = 0;
    private boolean mostrarImagenColor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etNombre = findViewById(R.id.etNombre);
        imageView = findViewById(R.id.imageView);

        mostrarPokemonActual();
    }

    private void mostrarPokemonActual() {
        if (index < nombresPokemons.length) {
            int resourceId = getResourceId(imagenesPokemons[index]);
            if (mostrarImagenColor) {
                resourceId = getResourceId(imagenesPokemonsColor[index]);
            }
            imageView.setImageResource(resourceId);
        }
    }

    public void ayuda(View view) {
        Intent i = new Intent(this, MainActivity3.class);
        startActivity(i);
    }

    public void aceptar(View view) {
        String nombreIngresado = etNombre.getText().toString().toUpperCase();

        if (nombreIngresado.equals(nombresPokemons[index])) {
            mostrarImagenColor = true;
            mostrarPokemonActual();
        }
    }

    public void siguiente(View view) {
        String nombreIngresado = etNombre.getText().toString().toUpperCase();

        if (nombreIngresado.equals(nombresPokemons[index]) && mostrarImagenColor) {
            mostrarImagenColor = false;
            etNombre.setText(""); // Eliminar el texto del EditText
            index++;
            mostrarPokemonActual();
        }
    }

    private int getResourceId(String resourceName) {
        return getResources().getIdentifier(resourceName, "drawable", getPackageName());
    }

    private void reiniciarJuego() {
        index = 0;
        mostrarPokemonActual();
    }
}
