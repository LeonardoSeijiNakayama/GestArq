package com.example.pojetopdm.activities;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pojetopdm.R;
import com.example.pojetopdm.adapters.AmbientListAdapter;
import com.example.pojetopdm.classes.Project;

public class AmbientsListActivity extends AppCompatActivity implements AmbientListAdapter.onItemClickListener, AmbientListAdapter.onItemLongClickListener{

    ImageButton btbBack;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ambients_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btbBack = findViewById(R.id.btnBack);
        recyclerView = findViewById(R.id.recyclerView);

        btbBack.setOnClickListener(v -> {
            finish();
        });

        Project project = (Project) getIntent().getSerializableExtra("project");

        AmbientListAdapter adapter = new AmbientListAdapter(project,this, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {

    }
}