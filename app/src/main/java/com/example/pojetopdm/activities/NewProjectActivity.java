package com.example.pojetopdm.activities;

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
import com.example.pojetopdm.classes.Client;
import com.example.pojetopdm.classes.Project;
import com.example.pojetopdm.classes.UserHandler;

public class NewProjectActivity extends AppCompatActivity {

    ImageButton btnBack;
    EditText edtxtName, edtxtStartDate,
            edtxtTerm, edtxtBriefing;
    Spinner spnClient, spnStatus;

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_project);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBack = findViewById(R.id.btnBack);
        edtxtName = findViewById(R.id.edtxtProjectName);
        spnStatus = findViewById(R.id.spnStatus);
        edtxtStartDate = findViewById(R.id.edtxtProjectStartDate);
        edtxtTerm = findViewById(R.id.edtxtProjectTerm);
        edtxtBriefing = findViewById(R.id.edtxtProjectBriefing);
        btnSubmit = findViewById(R.id.btnSubmit);
        spnClient = findViewById(R.id.spinnerProjectType);

        if(!UserHandler.getUser().getClients().isEmpty()){

            ArrayAdapter<Client> adapter = new ArrayAdapter<>(
                    this,
                    R.layout.spinner_item,
                    UserHandler.getUser().getClients());

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spnClient.setAdapter(adapter);

        }

        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                getResources().getStringArray(R.array.StatusOptions)
        );

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnStatus.setAdapter(adapter2);


        btnBack.setOnClickListener(view -> {
            finish();
        });

        btnSubmit.setOnClickListener(view -> {
            buildNewProject();

        });

    }

    public void buildNewProject(){
        String name, status, startDate, term, briefing;
        Client client = null;
        boolean flag = false;
        boolean flag2 = false;

        if(!edtxtName.getText().toString().isEmpty() && !edtxtStartDate.getText().toString().isEmpty()
                && !edtxtTerm.getText().toString().isEmpty() && !spnClient.getSelectedItem().toString().isEmpty()
                && !edtxtBriefing.getText().toString().isEmpty()){

            name = edtxtName.getText().toString();
            startDate = edtxtStartDate.getText().toString();
            status = spnStatus.getSelectedItem().toString();
            term = edtxtTerm.getText().toString();
            briefing = edtxtBriefing.getText().toString();

            for (Client c : UserHandler.getUser().getClients()){
                if(c.getName().equals(spnClient.getSelectedItem().toString())){
                    client = c;
                    flag = true;
                }
            }

            if(!flag){
                Toast.makeText(this, R.string.SelectAClient, Toast.LENGTH_SHORT).show();
                return;
            }

            Project project = new Project(name, status, startDate, briefing, client, term);

            UserHandler.getUser().addProject(project);
            Toast.makeText(this, R.string.ProjectCreated, Toast.LENGTH_SHORT).show();
            finish();

        }else{
            Toast.makeText(this, R.string.FillAllFields, Toast.LENGTH_SHORT).show();
        }

    }
}