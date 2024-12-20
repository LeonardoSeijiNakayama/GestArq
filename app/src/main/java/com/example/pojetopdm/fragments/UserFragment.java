package com.example.pojetopdm.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pojetopdm.R;
import com.example.pojetopdm.activities.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserFragment extends Fragment {

    private TextView email;
    private Button botaoLogout;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String usuarioId;

    // O método `onCreateView` é usado para inflar o layout do fragmento
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        iniciarComponentes(view);

        botaoLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut(); // Sair da sessão de login
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            if (getActivity() != null) {
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        String emailUsuario = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioId);
        documentReference.addSnapshotListener((documentSnapshot, error) -> {
            if (documentSnapshot != null) {
                email.setText(emailUsuario);
            }
        });
    }

    private void iniciarComponentes(View view) {
        email = view.findViewById(R.id.nomeEmail);
        botaoLogout = view.findViewById(R.id.botaoLogout);
    }
}
