package firebase.app.crudconfirebase_leonardobances;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class modificarAlumno extends AppCompatActivity {

    EditText txtDni, txtNombre, txtApellido, txtCorreo, txtContrasena;
    Button btnBuscar, btnModifcar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_alumno);

        txtDni = (EditText)findViewById(R.id.txtDni);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtContrasena = (EditText)findViewById(R.id.txtContrasena);
        btnBuscar  = (Button)findViewById(R.id.btnBuscar);
        btnModifcar  = (Button)findViewById(R.id.btnModifcar);
        btnEliminar  = (Button)findViewById(R.id.btnEliminar);

        buscar();
        modificar();
        eliminar();
        limpiarCampos();
        limpiarCursor();
        ocultarTeclado();


        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtContrasena.setEnabled(false);
        btnModifcar.setEnabled(false);
        btnEliminar.setEnabled(false);

    }

    private void buscar(){

        btnBuscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (txtDni.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    txtDni.setError("El campo es requerido");
                }
                else {
                    int dniM = Integer.parseInt(txtDni.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Alumno.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String dniE = Integer.toString(dniM);
                            boolean validacion = false;

                            for (DataSnapshot x : snapshot.getChildren()) {
                                if (dniE.equalsIgnoreCase(x.child("dni").getValue().toString())) {
                                    validacion = true;

                                    txtDni.setEnabled(false);
                                    btnBuscar.setEnabled(false);

                                    txtNombre.setEnabled(true);
                                    txtApellido.setEnabled(true);
                                    txtCorreo.setEnabled(true);
                                    txtContrasena.setEnabled(true);

                                    btnModifcar.setEnabled(true);
                                    btnEliminar.setEnabled(true);

                                    ocultarTeclado();
                                    txtNombre.setText(x.child("nombre").getValue().toString());
                                    txtApellido.setText(x.child("apellido").getValue().toString());
                                    txtCorreo.setText(x.child("correo").getValue().toString());
                                    txtContrasena.setText(x.child("contrasema").getValue().toString());
                                    break;
                                }
                            }

                            if (validacion == false) {
                                ocultarTeclado();
                                AlertDialog.Builder a = new AlertDialog.Builder(modificarAlumno.this);
                                a.setCancelable(false);
                                a.setTitle("Ocurrió un error");
                                a.setMessage("El DNI "+ dniM + " no se encuentra registrado.");

                                a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        txtDni.setEnabled(true);
                                        btnBuscar.setEnabled(true);
                                        txtNombre.setEnabled(false);
                                        txtApellido.setEnabled(false);
                                        txtCorreo.setEnabled(false);
                                        txtContrasena.setEnabled(false);
                                        btnModifcar.setEnabled(false);
                                        btnEliminar.setEnabled(false);
                                    }
                                });
                                a.show();
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

    private void modificar(){

        btnModifcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtNombre.getText().toString().trim().isEmpty() && txtApellido.getText().toString().trim().isEmpty() && txtCorreo.getText().toString().trim().isEmpty() && txtContrasena.getText().toString().trim().isEmpty()){
                    AlertDialog.Builder a = new AlertDialog.Builder(modificarAlumno.this);
                    a.setCancelable(false);
                    a.setTitle("Ocurrió un error");
                    a.setMessage("Para modificar los datos de un alumno debe completar todos campos.");

                    a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    a.show();
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

                            for (DataSnapshot x : snapshot.getChildren()) {
                                if (x.child("dni").getValue().toString().equalsIgnoreCase(dniE)) {
                                    AlertDialog.Builder a = new AlertDialog.Builder(modificarAlumno.this);
                                    a.setCancelable(false);
                                    a.setTitle("Advertencia");
                                    a.setMessage("¿Está seguro(a) de modificar los datos del alumno?");

                                    a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ocultarTeclado();
                                            x.getRef().child("nombre").setValue(nombreM);
                                            x.getRef().child("apellido").setValue(apellidoM);
                                            x.getRef().child("correo").setValue(correoM);
                                            x.getRef().child("contrasema").setValue(contrasenaM);
                                            limpiarCampos();
                                            limpiarCursor();

                                            AlertDialog.Builder a = new AlertDialog.Builder(modificarAlumno.this);
                                            a.setCancelable(false);
                                            a.setTitle("Notificación");
                                            a.setMessage("Los datos del alumno han sido modificados con éxito.");

                                            a.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    txtDni.setEnabled(true);
                                                    btnBuscar.setEnabled(true);
                                                    txtNombre.setEnabled(false);
                                                    txtApellido.setEnabled(false);
                                                    txtCorreo.setEnabled(false);
                                                    txtContrasena.setEnabled(false);
                                                    btnModifcar.setEnabled(false);
                                                    btnEliminar.setEnabled(false);
                                                }
                                            });
                                            a.show();

                                        }
                                    });

                                    a.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                                    a.show();
                                }
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

    private void eliminar(){

        btnEliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (txtDni.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                }
                else {
                    int dniM = Integer.parseInt(txtDni.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Alumno.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String dniE = Integer.toString(dniM);
                            final boolean[] validacion = {false};

                            for (DataSnapshot x : snapshot.getChildren()) {
                                if (dniE.equalsIgnoreCase(x.child("dni").getValue().toString())) {

                                    AlertDialog.Builder a = new AlertDialog.Builder(modificarAlumno.this);
                                    a.setCancelable(false);
                                    a.setTitle("Advertencia");
                                    a.setMessage("Está seguro(a) de eliminar el registro del alumno?");

                                    a.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });

                                    a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            validacion[0] = true;
                                            x.getRef().removeValue();
                                            ocultarTeclado();
                                            limpiarCampos();
                                            limpiarCursor();

                                            AlertDialog.Builder a = new AlertDialog.Builder(modificarAlumno.this);
                                            a.setCancelable(false);
                                            a.setTitle("Notificación");
                                            a.setMessage("Los datos del alumno han sido eliminados con éxito.");

                                            a.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    txtDni.setEnabled(true);
                                                    btnBuscar.setEnabled(true);
                                                    txtNombre.setEnabled(false);
                                                    txtApellido.setEnabled(false);
                                                    txtCorreo.setEnabled(false);
                                                    txtContrasena.setEnabled(false);
                                                    btnModifcar.setEnabled(false);
                                                    btnEliminar.setEnabled(false);
                                                }
                                            });
                                            a.show();

                                        }

                                    });

                                    a.show();
                                    break;

                                }
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

}