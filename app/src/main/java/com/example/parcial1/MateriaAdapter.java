package com.example.parcial1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MateriaAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Materia> materias=new ArrayList<>();
    private static LayoutInflater layoutInflater=null;
    Materia materia;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ArrayList<Corte> cortes=new ArrayList<>();

    public MateriaAdapter(Activity context, ArrayList<Materia> materias, DatabaseReference databaseReference,
                          FirebaseDatabase firebaseDatabase, ArrayList<Corte> cortes) {
        this.context = context;
        this.materias = materias;
        this.databaseReference = databaseReference;
        this.firebaseDatabase = firebaseDatabase;
        this.cortes=cortes;
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return materias.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        materia=materias.get(position);
        final View view=layoutInflater.inflate(R.layout.materia_lista,null);
        TextView nombre = view.findViewById(R.id.textNombre);
        TextView codigo= view.findViewById(R.id.textCodigo);
        TextView definitiva= view.findViewById(R.id.textNota);
        Button editar=view.findViewById(R.id.btnEditarMateria);
        Button siguiente=view.findViewById(R.id.btnSigiente);
        final Button eliminar=view.findViewById(R.id.btnEliminarMateria);
        nombre.setText(materia.getNombre());
        codigo.setText(""+materia.getCodigoMateria());
        definitiva.setText(""+materia.getDefinitiva());
        editar.setTag(position);
        eliminar.setTag(position);
        siguiente.setTag(position);


        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=Integer.parseInt(v.getTag().toString());
                final Dialog dialog=new Dialog(context);
                dialog.setTitle("Editar materia");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.registrarmateria);
                dialog.show();
                final EditText nombre= dialog.findViewById(R.id.editNombreMateria);
                final EditText codigo= dialog.findViewById(R.id.editCodigoMateria);
                final Button agregar= dialog.findViewById(R.id.btnAgregarMateria);
                final Button cancelar=dialog.findViewById(R.id.btnCancelarMateria);
                agregar.setText("Guardar");
                materia=materias.get(pos);
                nombre.setText(materia.getNombre());
                codigo.setText(""+materia.getCodigoMateria());
                agregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Materia nuevamateria=new Materia();
                        nuevamateria.setNombre(nombre.getText().toString());
                        nuevamateria.setCodigoMateria(materia.getCodigoMateria());
                        nuevamateria.setDefinitiva(materia.getDefinitiva());
                        databaseReference.child("Materias").child(nuevamateria.toString()).setValue(nuevamateria);
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
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=Integer.parseInt(v.getTag().toString());
                materia=materias.get(pos);
                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setMessage("Desea eliminar la materia?");
                alert.setCancelable(false);
                alert.setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0;i<cortes.size();i++){
                            Corte corte= cortes.get(i);
                            if(corte.getIdMateria()==materia.getCodigoMateria()){
                                databaseReference.child("Cortes").child(corte.getIdCorte()).removeValue();
                            }

                        }
                        databaseReference.child("Materias").child(materia.toString()).removeValue();
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=Integer.parseInt(v.getTag().toString());
                materia=materias.get(pos);
                Intent intent= new Intent(context,CorteActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("codigo",materia.getCodigoMateria());
                bundle.putString("materia",materia.getNombre());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
