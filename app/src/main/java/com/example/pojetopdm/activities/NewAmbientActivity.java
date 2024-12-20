package com.example.pojetopdm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pojetopdm.R;
import com.example.pojetopdm.classes.Ambient;
import com.example.pojetopdm.classes.Client;
import com.example.pojetopdm.classes.Project;
import com.example.pojetopdm.classes.UserHandler;

public class NewAmbientActivity extends AppCompatActivity {
    EditText edtxtName, edtxtArea, edtxtExistentMaterials, edtxtProposedMaterials;
    Spinner spnProjects;
    ImageButton btnBack;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_ambient);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtxtName = findViewById(R.id.edtxtName);
        edtxtArea = findViewById(R.id.edtxtArea);
        edtxtExistentMaterials = findViewById(R.id.edtxtExistentMaterials);
        edtxtProposedMaterials = findViewById(R.id.edtxtProposedMaterials);
        spnProjects = findViewById(R.id.spnProject);
        btnBack = findViewById(R.id.btnBack);
        btnSubmit = findViewById(R.id.btnSubmit);

        if(!UserHandler.getUser().getProjects().isEmpty()){

            ArrayAdapter<Project> adapter = new ArrayAdapter<>(
                    this,
                    R.layout.spinner_item,
                    UserHandler.getUser().getProjects());

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spnProjects.setAdapter(adapter);

        }

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        btnSubmit.setOnClickListener(v -> {
            buildNewAmbient();
        });

    }

    public void buildNewAmbient(){
        String name, area, openings, existentMaterials, proposedMaterials;
        Project project = null;
        boolean flag = false;

        if(!edtxtName.getText().toString().isEmpty() && !edtxtArea.getText().toString().isEmpty()
            && !edtxtExistentMaterials.getText().toString().isEmpty()
                && !edtxtProposedMaterials.getText().toString().isEmpty()){

            name = edtxtName.getText().toString();
            area = edtxtArea.getText().toString();
            existentMaterials = edtxtExistentMaterials.getText().toString();
            proposedMaterials = edtxtProposedMaterials.getText().toString();

            for (Project p : UserHandler.getUser().getProjects()){
                if(p.getName().equals(spnProjects.getSelectedItem().toString())){
                    project = p;
                    flag = true;
                }
            }

            if(!flag){
                Toast.makeText(this, R.string.SelectAProject, Toast.LENGTH_SHORT).show();
                return;
            }

            Ambient ambient = new Ambient(name, area, proposedMaterials, existentMaterials);
            for(Project p : UserHandler.getUser().getProjects()){
                if(p.getName().equals(spnProjects.getSelectedItem().toString())){
                    p.addAmbient(ambient);
                    Toast.makeText(this, R.string.AmbientCreated, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }


        }else{
            Toast.makeText(this, R.string.FillAllFields, Toast.LENGTH_SHORT).show();
        }
    }
}