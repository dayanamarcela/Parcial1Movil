package com.example.parcial1;

import android.app.Activity;
import android.app.Dialog;
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

public class CorteAdapter extends BaseAdapter {

Activity activity;
ArrayList<Corte> cortes= new ArrayList<>();
Corte corte;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
private static LayoutInflater inflater=null;
Actividad actividad;

    public CorteAdapter(Activity activity, ArrayList<Corte> cortes, FirebaseDatabase firebaseDatabase, DatabaseReference databaseReference) {
        this.activity = activity;
        this.cortes = cortes;
        this.firebaseDatabase = firebaseDatabase;
        this.databaseReference = databaseReference;
        inflater=(LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
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

        corte=cortes.get(position);
        final View view = inflater.inflate(R.layout.corte_item,null);
        TextView nombre= view.findViewById(R.id.c_nombre);
        TextView nota=view.findViewById(R.id.c_nota);
        TextView porcentaje=view.findViewById(R.id.c_porcentaje);
        Button editar= view.findViewById(R.id.c_editar);
        nombre.setText(corte.getNombre());
        nota.setText(""+corte.getNota());
        porcentaje.setText(""+corte.getPorcentaje());
        editar.setTag(position);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=Integer.parseInt(v.getTag().toString());
                corte=cortes.get(pos);
                final Dialog dialog=new Dialog(activity);
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
                        actividad=new Actividad(nombre.getText().toString(),Integer.parseInt(porcentaje.getText().toString()
                        ),nombre.getText().toString()+corte.getIdCorte(),Double.parseDouble(nota.getText().toString()),
                                corte.getIdCorte());
                        databaseReference.child("Actividades").child(actividad.getIdActividad()).setValue(actividad);
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

        return view;
    }
}
