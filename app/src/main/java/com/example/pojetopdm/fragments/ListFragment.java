package com.example.pojetopdm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pojetopdm.R;
import com.example.pojetopdm.activities.ProjectDetailsActivity;
import com.example.pojetopdm.adapters.ProjectListAdapter;
import com.example.pojetopdm.classes.Project;
import com.example.pojetopdm.classes.UserHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class ListFragment extends Fragment implements ProjectListAdapter.onItemClickListener, ProjectListAdapter.onItemLongClickListener {

    private RecyclerView recyclerView;
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        setupRecyclerView(view);
        setupSpinner(view);

        return view;
    }


    private void setupRecyclerView(View view) {
        ProjectListAdapter adapter = new ProjectListAdapter(this, this);
        recyclerView = view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }


    private void setupSpinner(View view) {
        spinner = view.findViewById(R.id.spinnerFilter);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.ProjectFilters,
                android.R.layout.simple_spinner_item
        );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applyFilter(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Não faz nada
            }
        });
    }


    private void applyFilter(int position) {
        switch (position) {
            case 0:
                Collections.sort(UserHandler.getUser().getProjects(), Comparator.comparing(Project::getName));
                break;
            case 1:
                Collections.sort(UserHandler.getUser().getProjects(), Comparator.comparing(project -> project.getClient().toString()));
                break;
            case 2:
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Collections.sort(UserHandler.getUser().getProjects(), (project1, project2) -> {
                    try {
                        Date date1 = sdf.parse(project1.getStartDate());
                        Date date2 = sdf.parse(project2.getStartDate());
                        return date2.compareTo(date1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return 0;
                    }
                });
                break;
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Project project = UserHandler.getUser().getProjects().get(position);

        Intent intent = new Intent(requireContext(), ProjectDetailsActivity.class);
        intent.putExtra("project", project);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        Project project = UserHandler.getUser().getProjects().get(position);
        showDeleteConfirmationDialog(project, position);
    }


    private void showDeleteConfirmationDialog(Project project, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage(getString(R.string.WishToDeleteProject) + " " + project.getName() + "?")
                .setPositiveButton(getString(R.string.Yes), (dialog, which) -> {
                    UserHandler.getUser().getProjects().remove(position);
                    recyclerView.getAdapter().notifyItemRemoved(position);
                })
                .setNegativeButton(getString(R.string.No), (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

        // Alterar a cor dos botões
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
    }
}
