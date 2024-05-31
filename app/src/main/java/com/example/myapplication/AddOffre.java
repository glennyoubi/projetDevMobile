package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AddOffre extends AppCompatActivity {

    TextInputEditText nomPoste, local, dStart, dEnd, salary, nomEntp, descrip;
    TextView tx_dash;
    DatabaseReference refUsers;
    DatabaseReference refEmpls;
    DatabaseReference refOffres;
    FirebaseDatabase db;
    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offre);

        db =  FirebaseDatabase.getInstance("https://my-project-android-1d1db-default-rtdb.europe-west1.firebasedatabase.app/");
        refUsers = db.getReference("Utilisateur");
        refEmpls =  db.getReference("Employeur");
        refOffres =  db.getReference("Offres");
        tx_dash = findViewById(R.id.backtodashboard);
        btn_add = findViewById(R.id.btn_register);

        nomPoste = findViewById(R.id.nom);
        local = findViewById(R.id.local);
        dStart = findViewById(R.id.dStart);
        dEnd = findViewById(R.id.dEnd);
        salary = findViewById(R.id.salary);
        nomEntp = findViewById(R.id.nomEntp);
        descrip = findViewById(R.id.desc);
        String extra_id = getIntent().getStringExtra("id");
        String role = getIntent().getStringExtra("role");
        /*
        tx_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poste, localisation, dDebut, dFin, rem, nomEnt, desc;
                poste = String.valueOf(nomPoste.getText());
                localisation = String.valueOf(local.getText());
                dDebut = String.valueOf(dStart.getText());
                dFin = String.valueOf(dEnd.getText());
                rem = String.valueOf(salary.getText());
                nomEnt = String.valueOf(nomEntp.getText());
                desc = String.valueOf(descrip.getText());

                if(TextUtils.isEmpty(poste)){
                    Toast.makeText(AddOffre.this, "Entrez l'adresse email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(localisation)){
                    Toast.makeText(AddOffre.this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(dDebut)){
                    Toast.makeText(AddOffre.this, "Entrez l'adresse email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(dFin)){
                    Toast.makeText(AddOffre.this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(rem)){
                    Toast.makeText(AddOffre.this, "Entrez l'adresse email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(nomEnt)){
                    Toast.makeText(AddOffre.this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(desc)){
                    Toast.makeText(AddOffre.this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(AddOffre.this, "Votre offre a été ajoutée", Toast.LENGTH_SHORT).show();

                final Boolean[] trouve = {true};
                String id_employeur = extra_id;
                String id = refOffres.push().getKey();
                Offre offre = new Offre(poste, localisation, dDebut, dFin, rem, nomEnt, id_employeur,id);
                refOffres.child(id).setValue(offre);
                Intent intent = new Intent(getApplicationContext(), DashboardEmp.class);
                intent.putExtra("id", extra_id);
                intent.putExtra("role", "employeur");
                startActivity(intent);
                finish();

                if(trouve[0])
                    Toast.makeText(AddOffre.this, "Vérifier vos données", Toast.LENGTH_SHORT).show();

            }
        });

        tx_dash.setOnClickListener(new View.OnClickListener() {
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
}