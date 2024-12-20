package com.example.pojetopdm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pojetopdm.R;

public class AmbientsActivity extends AppCompatActivity {

    Button btnCreateNewAmbient;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ambients);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCreateNewAmbient = findViewById(R.id.btnCreateNewAmbient);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(view -> {
            finish();
        });

        btnCreateNewAmbient.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewAmbientActivity.class);
            startActivity(intent);
        });
    }
}