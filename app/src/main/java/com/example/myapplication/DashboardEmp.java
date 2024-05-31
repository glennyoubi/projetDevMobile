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

public class DashboardEmp extends AppCompatActivity {

    DatabaseReference refUsers;
    DatabaseReference refEmpls;
    FirebaseDatabase db;
    TextView tx;
    Button btn_deco;
    Button btn_cvo;
    Button btn_add;
    Button btn_ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_emp);

        db =  FirebaseDatabase.getInstance("https://my-project-android-1d1db-default-rtdb.europe-west1.firebasedatabase.app/");
        refUsers = db.getReference("Utilisateur");
        refEmpls =  db.getReference("Employeur");
        tx = findViewById(R.id.user_details);
        btn_deco = findViewById(R.id.btn_logout);
        btn_cvo = findViewById(R.id.btn_consoffres);
        btn_add = findViewById(R.id.btn_addoffre);
        btn_ca = findViewById(R.id.btn_candaccep);
        String extra_id = getIntent().getStringExtra("id");
        String role = getIntent().getStringExtra("role");

        // Greetings message
        refEmpls.child(extra_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                employeur emp = snapshot.getValue(employeur.class);
                String nomEnt = emp.getNom();
                String email = emp.getEmail();
                String phone = emp.getPhone();
                String ville = emp.getVille();
                String result = "Bienvenue dans votre dashboard "+nomEnt+"!";
                tx.setText(result);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Logout
        btn_deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        // Add offre
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddOffre.class);
                intent.putExtra("id", extra_id);
                intent.putExtra("role", "employeur");
                startActivity(intent);
                finish();
            }
        });

        btn_cvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowOffresEmpl.class);
                intent.putExtra("id", extra_id);
                intent.putExtra("role", "employeir");
                startActivity(intent);
                finish();
            }
        });
    }
}