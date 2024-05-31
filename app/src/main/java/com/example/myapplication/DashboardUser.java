package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardUser extends AppCompatActivity {

    DatabaseReference refUsers;
    DatabaseReference refEmpls;
    FirebaseDatabase db;
    TextView tx;
    Button btn_deco;
    Button btn_trv;
    Button btn_add;
    Button btn_ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);

        db =  FirebaseDatabase.getInstance("https://my-project-android-1d1db-default-rtdb.europe-west1.firebasedatabase.app/");
        refUsers = db.getReference("Utilisateur");
        refEmpls =  db.getReference("Employeur");
        tx = findViewById(R.id.user_details);
        btn_deco = findViewById(R.id.btn_logout);
        btn_trv = findViewById(R.id.btn_TrvOffres);
        btn_add = findViewById(R.id.btn_CnsCands);
        btn_ca = findViewById(R.id.btn_FilOffres);
        String extra_id = getIntent().getStringExtra("id");
        String role = getIntent().getStringExtra("role");

        btn_deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        refUsers.child(extra_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                utilisateur u = snapshot.getValue(utilisateur.class);
                String nom = u.getNom();
                String prenom = u.getPrenom();
                String email = u.getEmail();
                String phone = u.getPhone();
                String ville = u.getVille();
                String result = "Bienvenue"+nom +" "+prenom+" !";
                tx.setText(result);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_trv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowAllOffres.class);
                intent.putExtra("id", extra_id);
                intent.putExtra("role", "Utilisateur");
                startActivity(intent);
                finish();
            }
        });
    }
}