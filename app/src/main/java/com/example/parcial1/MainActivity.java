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

public class MainActivity extends AppCompatActivity {
    Corte corte;
    ListView list;
    ArrayList<Materia>materias=new ArrayList<>();
    Materia materia;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MateriaAdapter materiaAdapter;
    ArrayList<Corte> cortes= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inizializarFirebase();
        traerMaterias();
        Button agregar=findViewById(R.id.btnAgregar);
        list=findViewById(R.id.listMateria);
        list.setAdapter(materiaAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(MainActivity.this);
                dialog.setTitle("Agregar materia");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.registrarmateria);
                dialog.show();
                final EditText nombre= dialog.findViewById(R.id.editNombreMateria);
                final EditText codigo= dialog.findViewById(R.id.editCodigoMateria);
                final Button agregar= dialog.findViewById(R.id.btnAgregarMateria);
                final Button cancelar=dialog.findViewById(R.id.btnCancelarMateria);

                agregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materia=new Materia(Integer.parseInt(codigo.getText().toString()),nombre.getText().toString(),0.0);
                        databaseReference.child("Materias").child(materia.toString()).setValue(materia);

                        corte=new Corte("primer corte",30,0,materia.getCodigoMateria(),
                                materia.getNombre()+materia.getCodigoMateria()+1);

                        databaseReference.child("Cortes").child(corte.getIdCorte()).setValue(corte);
                        corte=new Corte("segundo corte",30,0,materia.getCodigoMateria(),
                                materia.getNombre()+materia.getCodigoMateria()+2);
                        databaseReference.child("Cortes").child(corte.getIdCorte()).setValue(corte);
                        corte=new Corte("tercer corte",40,0,materia.getCodigoMateria(),
                                materia.getNombre()+materia.getCodigoMateria()+3);
                        databaseReference.child("Cortes").child(corte.getIdCorte()).setValue(corte);

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
    private void traerMaterias() {
        databaseReference.child("Materias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                materias.clear();
                for (DataSnapshot objSnapshot: snapshot.getChildren()){
                    Materia materia= objSnapshot.getValue(Materia.class);
                    materias.add(materia);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }


        });
        databaseReference.child("Cortes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cortes.clear();
                for (DataSnapshot objSnapshot: snapshot.getChildren()){
                    Corte corte=objSnapshot.getValue(Corte.class);
                    cortes.add(corte);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        materiaAdapter=new MateriaAdapter(this,materias,databaseReference,firebaseDatabase,cortes);
    }

    private void inizializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

}