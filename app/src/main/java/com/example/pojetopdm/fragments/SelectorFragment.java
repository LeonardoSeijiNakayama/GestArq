package com.example.pojetopdm.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pojetopdm.R;
import com.example.pojetopdm.activities.AmbientsActivity;
import com.example.pojetopdm.activities.ClientsActivity;
import com.example.pojetopdm.activities.ConfigActivity;
import com.example.pojetopdm.activities.ProjectsActivity;
import com.google.ar.core.examples.java.helloar.HelloArActivity;


public class SelectorFragment extends Fragment {

    Button btnProject, btnClients, btnAmbients, btnMeasure, btnConfig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selector, container, false);

        btnProject = view.findViewById(R.id.buttonProjects);
        btnClients = view.findViewById(R.id.buttonClients);
        btnAmbients = view.findViewById(R.id.buttonAmbients);
        btnMeasure = view.findViewById(R.id.buttonMeasure);
        btnConfig = view.findViewById(R.id.buttonConfig);

        btnProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {callProjectsActivity();}
        });

        btnClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {callClientsActivity();}
        });

        btnAmbients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {callAmbientsActivity();}
        });

        btnMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {callMeansure();}
        });

        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {callConfigurationActivity();}
        });


        return view;
    }

    public void callProjectsActivity(){
        Intent intent = new Intent(getActivity(), ProjectsActivity.class);
        startActivity(intent);
    }

    public void callClientsActivity(){
        Intent intent = new Intent(getActivity(), ClientsActivity.class);
        startActivity(intent);

    }

    public void callAmbientsActivity(){
        Intent intent = new Intent(getActivity(), AmbientsActivity.class);
        startActivity(intent);
    }

    public void callMeansure(){
        Intent cameraIntent = new Intent(getActivity(), HelloArActivity.class);
        startActivity(cameraIntent);
    }

    public void callConfigurationActivity(){
        Intent intent = new Intent(getActivity(), ConfigActivity.class);
        startActivity(intent);
    }
}

