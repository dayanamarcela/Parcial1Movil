package com.example.parcial1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActividadActivity extends AppCompatActivity {

    Actividad actividad;
    ArrayList<Actividad> actividades=new ArrayList<>();
    ActividadAdapter actividadAdapter;
    ListView list;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);
        Bundle bundle=this.getIntent().getExtras();
        if (bundle!=null){
            id=bundle.getString("id");
        }
        inizializarFirebase();
        traerActividades();
        Button agregar= findViewById(R.id.a_agregar);
        list=findViewById(R.id.a_lista);
        list.setAdapter(actividadAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(ActividadActivity.this);
                dialog.setTitle("Agregar actividad");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.registraractividad);
                dialog.show();
                final EditText nombre=dialog.findViewById(R.id.nombreActividad);
                final EditText porcentaje=dialog.findViewById(R.id.porcentajeActividad);
                final EditText nota=dialog.findViewById(R.id.notaActividad);
                final Button agregar=dialog.findViewById(R.id.agregarActividad);
                final Button cancelar=dialog.findViewById(R.id.cancelarActividad);
                agregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actividad=new Actividad(nombre.getText().toString(),Integer.parseInt(porcentaje.getText().toString()),
                                nombre.getText().toString()+id,Double.parseDouble(nota.getText().toString()),id);
                        databaseReference.child("Actividades").child(actividad.getIdActividad()).setValue(actividad);
                        dialog.dismiss();
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    private void inizializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    private void traerActividades(){
        databaseReference.child("Actividades").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                actividades.clear();
                for (DataSnapshot objSnapshot: snapshot.getChildren()){
                    Actividad actividad= objSnapshot.getValue(Actividad.class);
                    actividades.add(actividad);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        actividadAdapter=new ActividadAdapter(actividades,ActividadActivity.this,firebaseDatabase,databaseReference);
    }
}