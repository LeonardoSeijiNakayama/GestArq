package com.example.pojetopdm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pojetopdm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText usuario;
    private EditText senha;
    private TextView cadastro;
    private Button botaoLogin;
    String[] mensagens ={
            "Preencha todos os campos",
            "Login efetuado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        InicarComponentes();


        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoUsuario = usuario.getText().toString();
                String textoSenha = senha.getText().toString();
                if (textoUsuario.isEmpty()|| textoSenha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(getColor(R.color.darkGray));
                    snackbar.setTextColor(getColor(R.color.white));
                    snackbar.show();
                }else{
                    AutenticarUsuario(v);
                }

            }
        });
    }

    private void AutenticarUsuario(View v){
        String entradaUsuario = usuario.getText().toString();
        String entradaSenha = senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(entradaUsuario, entradaSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    TelaPrincipal();
                }else{
                    String erro;
                    try {
                        throw task.getException()
                                ;
                    }catch (Exception e){
                        erro = "Erro ao logar usuário";
                    }
                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(getColor(R.color.darkGray));
                    snackbar.setTextColor(getColor(R.color.white));
                    snackbar.show();
                }
            }
        });
    }
    protected void OnStart(){
        super.onStart();
        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser(); // pegando o usuário atual logado
        if(usuarioAtual!= null){
            TelaPrincipal();
        }
        FirebaseAuth.getInstance().signOut();
    }

    private void TelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void InicarComponentes(){
        usuario = findViewById(R.id.usuario);
        senha = findViewById(R.id.senha);
        botaoLogin = findViewById(R.id.botaoLogin);
        cadastro = findViewById(R.id.cadastreseTexto);
    }
}