package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AskerInscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asker_inscription);
        ImageView back = findViewById(R.id.left);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Revenez à l'activité principale ici
                Intent otherActivity = new Intent(getApplicationContext(), ChoiceInscription.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }
}