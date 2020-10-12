package com.example.parcial1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActividadAdapter extends BaseAdapter {

    ArrayList<Actividad> actividades=new ArrayList<>();
    Actividad actividad;
    Activity activity;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private static LayoutInflater inflater=null;

    public ActividadAdapter(ArrayList<Actividad> actividades, Activity activity, FirebaseDatabase firebaseDatabase,
                            DatabaseReference databaseReference) {
        this.actividades = actividades;
        this.activity = activity;
        this.firebaseDatabase = firebaseDatabase;
        this.databaseReference = databaseReference;
        inflater=(LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
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
        actividad=actividades.get(position);
        final  View view=inflater.inflate(R.layout.corte_item,null);
        TextView nombre= view.findViewById(R.id.m_nombre);
        TextView nota=view.findViewById(R.id.m_nota);
        TextView porcentaje=view.findViewById(R.id.m_porcentaje);
        Button editar= view.findViewById(R.id.m_editar);
        Button eliminar= view.findViewById(R.id.m_eliminar);
        nombre.setText(actividad.getNombre());
        nota.setText(""+actividad.getNota());
        porcentaje.setText(""+actividad.getPorcentaje());
        editar.setTag(position);
        eliminar.setTag(position);
        return view;

    }
}
