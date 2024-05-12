package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChoiceInscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_inscription);


        ImageView buttonBack = findViewById(R.id.left);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Revenez à la MainActivity ici
                Intent otherActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(otherActivity);
                finish();
            }
        });
        Button employeur = findViewById(R.id.employeur);
        employeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Revenez à la MainActivity ici
                Intent otherActivity = new Intent(getApplicationContext(), EmployerInscription.class);
                startActivity(otherActivity);
                finish();
            }
        });

        Button asker = findViewById(R.id.asker);
        asker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Revenez à la MainActivity ici
                Intent otherActivity = new Intent(getApplicationContext(), AskerInscription.class);
                startActivity(otherActivity);
                finish();
            }
        });



    }
}