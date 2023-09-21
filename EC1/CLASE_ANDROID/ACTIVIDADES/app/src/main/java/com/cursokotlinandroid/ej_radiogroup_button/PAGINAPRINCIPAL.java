package com.cursokotlinandroid.ej_radiogroup_button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class PAGINAPRINCIPAL extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void chexboc(View view){
        Intent i=new Intent(this, CHEXBOX.class);
        startActivity(i);
    }
    public void lisview(View view){
        Intent i=new Intent(this, LISTVIEW.class);
        startActivity(i);
    }
    public void radiobutton(View view){
        Intent i=new Intent(this, RADIOGROUPandBUTTON.class);
        startActivity(i);
    }
    public void spinner(View view){
        Intent i=new Intent(this, SPINNER.class);
        startActivity(i);
    }

}