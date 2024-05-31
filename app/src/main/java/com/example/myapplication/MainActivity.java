package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView tx;
    Button btn_deco;
    FirebaseUser user;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //auth = FirebaseAuth.getInstance();
        tx = findViewById(R.id.user_details);
        btn_deco = findViewById(R.id.btn_logout);
        //user = auth.getCurrentUser();
        String extra = getIntent().getStringExtra("id");
        String role = getIntent().getStringExtra("role");

        DatabaseReference refUsers;
        DatabaseReference refEmpls;
        FirebaseDatabase db;

        db =  FirebaseDatabase.getInstance("https://my-project-android-1d1db-default-rtdb.europe-west1.firebasedatabase.app/");
        refUsers = db.getReference("Utilisateur");
        refEmpls =  db.getReference("Employeur");
        /*
        if(role.equals("utilisateur")){
            tx.setText("je suis:"+extra+". Je suis un "+role);
        }
        if(role.equals("employeur")){
            tx.setText("je suis:"+extra+". Je suis un "+role);
        }
        */
        if(role.equals("utilisateur")){
            refUsers.child(extra).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    utilisateur u = snapshot.getValue(utilisateur.class);
                    String nom = u.getNom();
                    String prenom = u.getPrenom();
                    String email = u.getEmail();
                    String phone = u.getPhone();
                    String ville = u.getVille();
                    String result = "Bienvenue"+nom +" "+prenom+" "+" "+email+" "+phone+" "+ville+"!";
                    tx.setText(result);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        if(role.equals("employeur")){
            refEmpls.child(extra).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    employeur emp = snapshot.getValue(employeur.class);
                    String nomEnt = emp.getNom();
                    String email = emp.getEmail();
                    String phone = emp.getPhone();
                    String ville = emp.getVille();
                    String result = "Bienvenue dans votre dashboard "+nomEnt;
                    tx.setText(result);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }



        /*
        if(user == null){
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }
        else {
            tx.setText(user.getEmail());
        }*/

        btn_deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}