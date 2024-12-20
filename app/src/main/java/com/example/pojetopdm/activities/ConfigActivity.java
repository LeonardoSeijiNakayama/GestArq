package com.example.pojetopdm.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pojetopdm.R;

import java.util.Locale;

public class ConfigActivity extends AppCompatActivity {

    private Spinner spinnerLanguage;
    private ImageButton btnBack;
    private Button btnApplyChanges;
    private String selectedLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_config);

        spinnerLanguage = findViewById(R.id.languageSpinner);
        btnBack = findViewById(R.id.btnBack);
        btnApplyChanges = findViewById(R.id.btnApplyChanges);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Languages, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);

        // Definindo o ouvinte de seleção para o Spinner
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //Define a linguagem com base na seleção do usuário
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                switch (position) {
                    case 0: // Português
                        selectedLanguage = "pt";
                        break;
                    case 1: // Inglês
                        selectedLanguage = "en";
                        break;
                    case 2: // Espanhol
                        selectedLanguage = "esp";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Não faz nada se nao tiver nada selecionado
            }
        });


        btnBack.setOnClickListener(v -> finish());

        btnApplyChanges.setOnClickListener(v -> {
            if(!selectedLanguage.isEmpty()){
                changeLanguage(selectedLanguage);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void changeLanguage(String languageCode) {
        // Corrigido o código para espanhol para "es" ao invés de "esp"
        if (languageCode.equals("esp")) {
            languageCode = "es";
        }

        // Armazenando a preferência de idioma no SharedPreferences
        SharedPreferences preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", languageCode);
        editor.apply();

        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        // Usar createConfigurationContext para versões mais recentes do Android
        Context context = createConfigurationContext(config);

        // Atualizar os recursos da aplicação
        getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());

        // Reiniciar a activity para aplicar a mudança de idioma
        Intent intent = new Intent(this, MainActivity.class); // Mude para a Activity principal
        startActivity(intent);
        finish();
    }
}
