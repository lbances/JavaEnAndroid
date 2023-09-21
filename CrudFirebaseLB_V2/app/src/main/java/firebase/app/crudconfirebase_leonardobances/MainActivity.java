package firebase.app.crudconfirebase_leonardobances;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText txtDni, txtNombre, txtApellido, txtCorreo, txtContrasena;
    Button btnRegistrar, btnListado, btnModifcar, btnEliminar, btnCamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDni = (EditText)findViewById(R.id.txtDni);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtContrasena = (EditText)findViewById(R.id.txtContrasena);
        btnRegistrar  = (Button)findViewById(R.id.btnRegistrar);
        btnListado  = (Button)findViewById(R.id.btnListado);
        btnModifcar  = (Button)findViewById(R.id.btnModifcar);

        registrar();
        limpiarCampos();
        limpiarCursor();
        ocultarTeclado();
        listadoAlumnos();
        modificarAlumno();

    }

    private void registrar() {

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtDni.getText().toString().trim().isEmpty() && txtNombre.getText().toString().trim().isEmpty() && txtApellido.getText().toString().trim().isEmpty() && txtCorreo.getText().toString().trim().isEmpty() && txtContrasena.getText().toString().trim().isEmpty()){
                    AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
                    a.setCancelable(false);
                    a.setTitle("Ocurrió un error");
                    a.setMessage("Para registrar un alumno debe completar todos campos.");

                    a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    a.show();
                }

                else if (txtDni.getText().toString().trim().isEmpty()) {
                    ocultarTeclado();
                    txtDni.setError("El campo es requerido");
                }

                else if (txtNombre.getText().toString().trim().isEmpty()) {
                    ocultarTeclado();
                    txtNombre.setError("El campo es requerido");
                }

                else if (txtApellido.getText().toString().trim().isEmpty()) {
                    ocultarTeclado();
                    txtApellido.setError("El campo es requerido");
                }

                else if (txtCorreo.getText().toString().trim().isEmpty()) {
                    ocultarTeclado();
                    txtCorreo.setError("El campo es requerido");
                }

                else if (txtContrasena.getText().toString().trim().isEmpty()) {
                    ocultarTeclado();
                    txtContrasena.setError("El campo es requerido");
                }

                else {
                    int dniM = Integer.parseInt(txtDni.getText().toString());
                    String nombreM = txtNombre.getText().toString();
                    String apellidoM = txtApellido.getText().toString();
                    String correoM = txtCorreo.getText().toString();
                    String contrasenaM = txtContrasena.getText().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Alumno.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            boolean validacion = false;
                            String dniE = Integer.toString(dniM);

                            for (DataSnapshot x : snapshot.getChildren()){
                                if (x.child("dni").getValue().toString().equalsIgnoreCase(dniE)){
                                    validacion = true;
                                    ocultarTeclado();

                                    AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
                                    a.setCancelable(false);
                                    a.setTitle("Ocurrió un error");
                                    a.setMessage("El alumno con DNI: " + dniE + " ya se ecuentra registrado.");

                                    a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                                    a.show();
                                    break;
                                }
                            }

                            if (validacion == false){
                                Alumno alumno = new Alumno(dniM, nombreM, apellidoM, correoM, contrasenaM);
                                dbref.push().setValue(alumno);
                                ocultarTeclado();AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
                                a.setCancelable(false);
                                a.setTitle("Notificación");
                                a.setMessage("Los datos del alumno han sido registrados con éxito.");

                                a.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                a.show();
                                limpiarCampos();
                                limpiarCursor();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

            }
        });
    }

    private void limpiarCampos(){

        txtDni.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");

    }

    private void limpiarCursor(){

        txtDni.clearFocus();
        txtNombre.clearFocus();
        txtApellido.clearFocus();
        txtCorreo.clearFocus();
        txtContrasena.clearFocus();

    }

    private void ocultarTeclado(){

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void listadoAlumnos(){

        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listadoDeAlumnos.class);
                startActivity(intent);
            }
        });

    }

    private void modificarAlumno(){

        btnModifcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, modificarAlumno.class);
                startActivity(intent);
            }
        });

    }
}