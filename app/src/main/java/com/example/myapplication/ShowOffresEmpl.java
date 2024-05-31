package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowOffresEmpl extends AppCompatActivity{

    TextView btn_dash;
    ArrayList<Offre> offresFromDb;
    RecyclerView offresRecycler;
    offreAdapter offresAdapter;
    DatabaseReference refOffres;
    FirebaseDatabase db;
    String extra_id;
    String role;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycl_offres_vu_par_employeur);
        // id connexion
        extra_id = getIntent().getStringExtra("id");
        role = getIntent().getStringExtra("role");
        // Interface
        btn_dash = findViewById(R.id.routodashboard);
        offresRecycler = findViewById(R.id.recyclerView);
        // database
        db =  FirebaseDatabase.getInstance("https://my-project-android-1d1db-default-rtdb.europe-west1.firebasedatabase.app/");
        refOffres =  db.getReference("Offres");
        // list to store offres
        offresFromDb = new ArrayList<>();
        // on récupère les données
        // getOffresFromDatabase();
        getOffresFromDatabaseFromCurrentEmpl();

        // adapter init
        offresAdapter = new offreAdapter(offresFromDb);

        offresRecycler.setAdapter(offresAdapter);
        offresRecycler.setLayoutManager(new LinearLayoutManager(this));

        btn_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardEmp.class);
                intent.putExtra("id", extra_id);
                intent.putExtra("role", "employeur");
                startActivity(intent);
                finish();
            }
        });
    }

    private void getOffresFromDatabase() {
        refOffres.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                offresFromDb.clear();
                for (DataSnapshot offreSnapshot : dataSnapshot.getChildren()) {
                    Offre offre = offreSnapshot.getValue(Offre.class);
                    offresFromDb.add(offre);
                }
                offresAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Gérer les erreurs éventuelles
                Toast.makeText(ShowOffresEmpl.this, "Failed to load missions.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getOffresFromDatabaseFromCurrentEmpl() {
        refOffres.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                offresFromDb.clear();
                for (DataSnapshot offreSnapshot : dataSnapshot.getChildren()) {
                    Offre offre = offreSnapshot.getValue(Offre.class);
                    if(offre.getid_employeur().equals(extra_id)){
                        offresFromDb.add(offre);
                    }
                }
                offresAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Gérer les erreurs éventuelles
                Toast.makeText(ShowOffresEmpl.this, "Failed to load missions.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
