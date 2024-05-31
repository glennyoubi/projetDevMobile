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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputEditText editTextMail, editTextPassword;
    Button btn_login;
    FirebaseAuth auth;
    DatabaseReference refUsers;
    DatabaseReference refEmpls;
    FirebaseDatabase db;
    TextView tx;
    TextView tx2;

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        /*
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
         */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth =  FirebaseAuth.getInstance();
        editTextMail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        tx = findViewById(R.id.registerNow);
        tx2 = findViewById(R.id.registerNowEmployeur);

        db =  FirebaseDatabase.getInstance("https://my-project-android-1d1db-default-rtdb.europe-west1.firebasedatabase.app/");
        refUsers = db.getReference("Utilisateur");
        refEmpls =  db.getReference("Employeur");




        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterEmployeur.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(editTextMail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Entrez l'adresse email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }
                System.out.println("BTN PRESS NOT REFS");
                final Boolean[] trouve = {false};
                if(!trouve[0]){
                    refUsers.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            System.out.println("BTN PRESS IN REFS");
                            for (DataSnapshot ds: snapshot.getChildren()
                            ) {
                                utilisateur u = ds.getValue(utilisateur.class);
                                if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
                                    System.out.println("HERE");
                                    trouve[0] = true;
                                    Toast.makeText(Login.this, "Connexion reussie", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), DashboardUser.class);
                                    intent.putExtra("id", u.getId());
                                    intent.putExtra("role", "utilisateur");
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                if(!trouve[0]){
                    refEmpls.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds: snapshot.getChildren()
                            ) {
                                employeur u = ds.getValue(employeur.class);
                                if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
                                    System.out.println("HERE");
                                    trouve[0] = true;
                                    Toast.makeText(Login.this, "Connexion reussie", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), DashboardEmp.class);
                                    intent.putExtra("id", u.getId());
                                    intent.putExtra("role", "employeur");
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                if(trouve[0])
                    Toast.makeText(Login.this, "Vérifier vos identifiants", Toast.LENGTH_SHORT).show();

                /*
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Login.this, "Inscription réussi", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/

            }
        });
    }
}