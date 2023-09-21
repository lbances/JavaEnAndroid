package firebase.app.crudconfirebase_leonardobances;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class listadoDeAlumnos extends AppCompatActivity {

    ListView lstAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_de_alumnos);

        lstAlumnos = (ListView) findViewById(R.id.lstAlumnos);

        listaAlumno();

    }

    private void listaAlumno(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbref = db.getReference(Alumno.class.getSimpleName());

        ArrayList<Alumno> listAlumno = new ArrayList<>();
        ArrayAdapter<Alumno> adaptador = new ArrayAdapter<Alumno>(listadoDeAlumnos.this, R.layout.list_item_alumno, listAlumno) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_alumno, parent, false);
                }

                TextView txtNombreApellido = convertView.findViewById(R.id.textNombreApellido);
                Alumno alumno = getItem(position);
                txtNombreApellido.setText(alumno.getNombre() + " " + alumno.getApellido());

                return convertView;
            }
        };
        lstAlumnos.setAdapter(adaptador);

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Alumno alumno = snapshot.getValue(Alumno.class);
                listAlumno.add(alumno);
                adaptador.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                adaptador.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lstAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Alumno alumno = listAlumno.get(position);
                AlertDialog.Builder a = new AlertDialog.Builder(listadoDeAlumnos.this);
                a.setCancelable(true);
                a.setTitle("Alumno");

                String msg = "DNI: "+ alumno.getDni() + "\n\n";
                msg += "Nombre: "+ alumno.getNombre() + "\n\n";
                msg += "Apellido: "+ alumno.getApellido() + "\n\n";
                msg += "Correo: "+ alumno.getCorreo() + "\n\n";

                a.setMessage(msg);
                a.show();

            }
        });

    }
}