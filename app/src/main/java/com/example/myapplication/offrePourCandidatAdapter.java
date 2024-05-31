package com.example.myapplication;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.util.ArrayList;

public class offrePourCandidatAdapter extends RecyclerView.Adapter<offrePourCandidatAdapter.MyView> {

    private ArrayList<Offre> listOffres = new ArrayList<>();
    public offrePourCandidatAdapter(ArrayList<Offre> list) {
        this.listOffres = list;
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offre_pour_candidater,parent,false);
        MyView mvh = new MyView(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.titleView.setText(listOffres.get(position).getPoste());
        holder.localisationView.setText( listOffres.get(position).getLocalisation());
        holder.salaireView.setText( listOffres.get(position).getRem());
        holder.nomEnt.setText(listOffres.get(position).getNomEnt());
        holder.dDebut.setText( listOffres.get(position).getdDebut());
        holder.dFin.setText(listOffres.get(position).getdFin());

    }

    @Override
    public int getItemCount() {
        return listOffres.size();
    }

    class MyView extends RecyclerView.ViewHolder{

        TextView titleView;
        TextView localisationView;
        TextView salaireView;
        TextView nomEnt;
        TextView dDebut;
        TextView dFin;


        public MyView(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titre);
            localisationView = itemView.findViewById(R.id.lieu);
            nomEnt = itemView.findViewById(R.id.ent);
            salaireView = itemView.findViewById(R.id.salaire);
            dDebut = itemView.findViewById(R.id.debut);
            dFin = itemView.findViewById(R.id.fin);

        }
    }
}
