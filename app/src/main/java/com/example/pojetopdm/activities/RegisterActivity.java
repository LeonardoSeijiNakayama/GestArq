package com.example.pojetopdm.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pojetopdm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText telefone;
    private Button botaoCadastro;

    String[] mensagens = {
            "Preencha todos os campos",
            "Cadastro realizado com sucesso",
            "Erro"
    };

    String usuarioId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        try {
            IniciarComponentes();
            Log.d("DEBUG", "Componentes inicializados com sucesso");

            botaoCadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nomeEntrada = nome.getText().toString();
                    String emailEntrada = email.getText().toString();
                    String telefoneEntrada = telefone.getText().toString();
                    String senhaEntrada = senha.getText().toString();

                    if (nomeEntrada.isEmpty() || senhaEntrada.isEmpty() || emailEntrada.isEmpty() || telefoneEntrada.isEmpty()) {
                        Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();
                    } else {
                        CadastrarUsuario(v);
                    }
                }
            });
        } catch (Exception e) {
            Log.e("DEBUG", "Erro no onCreate", e);
        }
    }


    private void CadastrarUsuario(View v){

        String emailEntrada = email.getText().toString();
        String nomeEntrada = nome.getText().toString();
        String senhaEntrada = senha.getText().toString();
        String telefoneEntrada = telefone.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEntrada, senhaEntrada).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    SalvarDadosUsuario();
                    Snackbar snackbar = Snackbar.make(v,mensagens[1],Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.show();
                }else{
                    String erro;
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha com no mínimo 6 caracteres";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "Esta conta já foi cadastrada";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mail inválido";
                    }catch(Exception e){
                        erro = "Erro ao cadastrar usuário";
                    }

                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.show();

                }
            }
        });

    }

    private void SalvarDadosUsuario(){
        String nomeEntrada = nome.getText().toString();
        String numTelefone = telefone.getText().toString();


        FirebaseFirestore db = FirebaseFirestore.getInstance(); //instancia do banco de dados

        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome", nomeEntrada);
        usuarios.put("telefone", numTelefone);

        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference  docReference = db.collection("Usuarios").document(usuarioId);
        docReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("db", "Sucesso ao salvar os dados");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("dv_error", "Erro ao salvar os dados" + e.toString());

                    }
                });
    }
    private void IniciarComponentes(){
        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        telefone = findViewById(R.id.telefone);
        botaoCadastro = findViewById(R.id.botaoCadastro);
    }
}