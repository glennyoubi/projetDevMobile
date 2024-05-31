package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class JobSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);

        ImageView back = findViewById(R.id.left);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Revenez à l'activité principale ici
                Intent otherActivity = new Intent(getApplicationContext(), Accueil.class);
                startActivity(otherActivity);
                finish();
            }
        });
        Button rechercher = findViewById(R.id.buttonS);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowAllOffresNonRegisterUser.class));
            }
        });
    }
}