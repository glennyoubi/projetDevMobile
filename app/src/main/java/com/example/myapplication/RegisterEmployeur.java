package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterEmployeur extends AppCompatActivity {

    TextInputEditText editTextMail, editTextPassword, editTextNomEnt, editTextPhone, editTextVille;
    Button btn_register;
    FirebaseDatabase db;
    DatabaseReference ref;
    TextView tx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employeur);

        db =  FirebaseDatabase.getInstance("https://my-project-android-1d1db-default-rtdb.europe-west1.firebasedatabase.app/");
        ref = db.getReference("Employeur");
        editTextMail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextNomEnt = findViewById(R.id.nomEnt);
        editTextPhone = findViewById(R.id.phone);
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
                String email, password, nomEnt, phone, ville;
                email = String.valueOf(editTextMail.getText());
                password = String.valueOf(editTextPassword.getText());
                nomEnt = String.valueOf(editTextNomEnt.getText());
                phone = String.valueOf(editTextPhone.getText());
                ville = String.valueOf(editTextVille.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterEmployeur.this, "Entrez l'adresse email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterEmployeur.this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(nomEnt)){
                    Toast.makeText(RegisterEmployeur.this, "Entrez votre nom d'entrepise", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(RegisterEmployeur.this, "Entrez votre numéro de téléphone", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(ville)){
                    Toast.makeText(RegisterEmployeur.this, "Entrez votre ville de résidence", Toast.LENGTH_SHORT).show();
                    return;
                }

                String id = ref.push().getKey();
                employeur e = new employeur(email, password, nomEnt, phone, ville, id);
                ref.child(id).setValue(e);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }
}