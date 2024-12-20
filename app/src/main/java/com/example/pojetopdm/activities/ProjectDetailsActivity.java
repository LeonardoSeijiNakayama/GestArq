package com.example.pojetopdm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pojetopdm.R;
import com.example.pojetopdm.classes.Project;

import java.io.Serializable;

public class ProjectDetailsActivity extends AppCompatActivity {
    ImageButton btnBack;
    TextView txtName, txtClient, txtDate, txtTerm, txtBriefing, txtStatus;
    Button btnAmbientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_project_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Project project = (Project) getIntent().getSerializableExtra("project");

        txtName = findViewById(R.id.txtName);
        txtClient = findViewById(R.id.txtClient);
        txtDate = findViewById(R.id.txtDate);
        txtTerm = findViewById(R.id.txtTerm);
        btnBack = findViewById(R.id.btnBack);
        txtBriefing = findViewById(R.id.txtBriefing);
        txtStatus = findViewById(R.id.txtStatus);
        btnAmbientsList = findViewById(R.id.btnAmbientsList);

        txtName.setText(project.getName());
        txtClient.setText(project.getClient().getName());
        txtDate.setText(project.getStartDate());
        txtTerm.setText(project.getTerm());
        txtBriefing.setText(project.getBriefing());
        txtStatus.setText(project.getStatus());

        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnAmbientsList.setOnClickListener(v -> {
            Intent intent = new Intent(this, AmbientsListActivity.class);
            intent.putExtra("project", project);
            startActivity(intent);
        });

    }
}