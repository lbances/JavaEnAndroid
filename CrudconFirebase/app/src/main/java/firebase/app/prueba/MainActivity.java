package firebase.app.prueba;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.UUID;
import firebase.app.prueba.nodel.Persona;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre1, txtApellido1, txtDni1, txtCorreo1, txtContrasena1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre1 = findViewById(R.id.txtNombre1);
        txtApellido1 = findViewById(R.id.txtApellido1);
        txtDni1 = findViewById(R.id.txtDni1);
        txtCorreo1 = findViewById(R.id.txtCorreo1);
        txtContrasena1 = findViewById(R.id.txtContrasena1);

        inicializarFirebase();

    }

    private void inicializarFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){



        return true;
    }

    public void registrar(View view){
        try {
            validacion();
            String nombrep = txtNombre1.getText().toString();
            String apellidop = txtApellido1.getText().toString();
            String dnip = txtDni1.getText().toString();
            String correop = txtCorreo1.getText().toString();
            String passp = txtContrasena1.getText().toString();

            Persona p = new Persona();
            p.setUid(UUID.randomUUID().toString());
            p.setNombre(nombrep);
            p.setApellido(apellidop);
            p.setDni(dnip);
            p.setCorreo(correop);
            p.setContrasena(passp);
            databaseReference.child("Persona").child(p.getUid()).setValue(p);

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

            limpiar();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Registro", "Excepción al intentar registrar", e);
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
        }
    }
    private void validacion() {
        String nombrep = txtNombre1.getText().toString();
        String apellidop = txtApellido1.getText().toString();
        String dnip = txtDni1.getText().toString();
        String correop = txtCorreo1.getText().toString();
        String passp = txtContrasena1.getText().toString();

        if (nombrep.equals("")){
            txtNombre1.setError("El campo es requerido");
        }

        else if (apellidop.equals("")){
            txtApellido1.setError("El campo es requerido");
        }

        else if (dnip.equals("")){
            txtDni1.setError("El campo es requerido");
        }

        else if (correop.equals("")){
            txtCorreo1.setError("El campo es requerido");
        }

        else if (passp.equals("")){
            txtContrasena1.setError("El campo es requerido");
        }
    }

    private void limpiar() {
        txtNombre1.setText("");
        txtApellido1.setText("");
        txtDni1.setText("");
        txtCorreo1.setText("");
        txtContrasena1.setText("");
    }


    public void lstPersonas(View view) {
        Intent i = new Intent(this, MainActivity2.class );
        startActivity(i);
    }
}