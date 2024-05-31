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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    TextInputEditText editTextMail, editTextPassword, editTextNom, editTextPrenom, editTextPhone, editTextNat, editTextVille;
    Button btn_register;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference ref;
    TextView tx;

    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth =  FirebaseAuth.getInstance();
        db =  FirebaseDatabase.getInstance("https://my-project-android-1d1db-default-rtdb.europe-west1.firebasedatabase.app/");
        ref = db.getReference("Utilisateur");
        editTextMail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextNom = findViewById(R.id.nom);
        editTextPrenom = findViewById(R.id.prenom);
        editTextPhone = findViewById(R.id.phone);
        editTextNat = findViewById(R.id.nat);
        editTextVille = findViewById(R.id.ville);
        btn_register = findViewById(R.id.btn_register);
        tx = findViewById(R.id.loginNow);

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, nom, prenom, phone, nat, ville;
                email = String.valueOf(editTextMail.getText());
                password = String.valueOf(editTextPassword.getText());
                nom = String.valueOf(editTextNom.getText());
                prenom = String.valueOf(editTextPrenom.getText());
                phone = String.valueOf(editTextPhone.getText());
                nat = String.valueOf(editTextNat.getText());
                ville = String.valueOf(editTextVille.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Entrez l'adresse email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(nom)){
                    Toast.makeText(Register.this, "Entrez votre nom", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(prenom)){
                    Toast.makeText(Register.this, "Entrez votre prenom", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(Register.this, "Entrez votre numéro de téléphone", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(nat)){
                    Toast.makeText(Register.this, "Entrez votre nationalité", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(ville)){
                    Toast.makeText(Register.this, "Entrez votre ville de résidence", Toast.LENGTH_SHORT).show();
                    return;
                }

                String id = ref.push().getKey();
                utilisateur u = new utilisateur(email, password, nom, prenom, phone, nat, ville, id);
                ref.child(id).setValue(u);
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
                /*
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    // Connexion réussie
                                    Toast.makeText(Register.this, "Inscription réussi", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
                */
            }

        });//function
    } // oncreate
} // classe