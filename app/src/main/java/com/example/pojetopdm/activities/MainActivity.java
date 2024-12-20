package com.example.pojetopdm.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pojetopdm.R;
import com.example.pojetopdm.fragments.ListFragment;
import com.example.pojetopdm.fragments.SelectorFragment;
import com.example.pojetopdm.classes.Client;
import com.example.pojetopdm.classes.Material;
import com.example.pojetopdm.classes.Project;
import com.example.pojetopdm.classes.UserHandler;
import com.example.pojetopdm.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements  BottomNavigationView
        .OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    SelectorFragment selectorFragment = new SelectorFragment();
    ListFragment listFragment = new ListFragment();

    UserFragment userFragment = new UserFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Carregar o idioma preferido do SharedPreferences
        SharedPreferences preferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String languageCode = preferences.getString("language", "pt"); // Padrão é português

        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        // Usar createConfigurationContext para versões mais recentes do Android
        Context context = createConfigurationContext(config);

        // Atualizar os recursos da aplicação
        getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main); // Ou a sua Activity principal



        loadUser();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.functionalities);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        if(item.getItemId() == R.id.functionalities){
            selectedFragment = selectorFragment;
        }
        if(item.getItemId() == R.id.profile){
            selectedFragment = userFragment;
        }

        if(item.getItemId() == R.id.list){
            selectedFragment = listFragment;
        }

        if(selectedFragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, selectedFragment)
                    .commit();
            return true;
        }
        return false;

    }

    public void loadUser(){

        ArrayList<Client> clientsTest = new ArrayList<>();
        ArrayList<Project> projectsTest = new ArrayList<>();
        ArrayList<Material> materialsTest = new ArrayList<>();

        clientsTest.add(new Client("joao", "joao@opa", "123123",
                "endereco", "coisas"));
        clientsTest.add(new Client("leonardo", "leo@opa", "123456",
                "ali", "coisinhas"));
        clientsTest.add(new Client("antonio", "antonio@opa", "654321",
                "aqui", "coisinhazinhas"));

        UserHandler.buildUser("Leonardo", "leonardonakayama99@gmail.com", projectsTest, clientsTest, materialsTest);


    }

}