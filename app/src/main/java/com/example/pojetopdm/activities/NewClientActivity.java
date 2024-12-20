package com.example.pojetopdm.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pojetopdm.R;
import com.example.pojetopdm.classes.Client;
import com.example.pojetopdm.classes.UserHandler;

public class NewClientActivity extends AppCompatActivity {

    EditText edtxtName, edtxtEmail, edtxtPhoneNumber, edtxtAddress, edtxtAdditionalNotes;
    ImageButton btnBack;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_client);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtxtName = findViewById(R.id.edtxtName);
        edtxtEmail = findViewById(R.id.edtxtEmail);
        edtxtPhoneNumber = findViewById(R.id.edtxtPhoneNumber);
        edtxtAddress = findViewById(R.id.edtxtAddress);
        edtxtAdditionalNotes = findViewById(R.id.edtxtAdditionalNotes);
        btnBack = findViewById(R.id.btnBack);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnSubmit.setOnClickListener(v ->{
            BuildNewClient();
        });
    }

    public void BuildNewClient(){
        String name, email, phoneNumber, address, additionalNotes;
        name = edtxtName.getText().toString();
        email = edtxtEmail.getText().toString();
        phoneNumber = edtxtPhoneNumber.getText().toString();
        address = edtxtAddress.getText().toString();
        additionalNotes = edtxtAdditionalNotes.getText().toString();

        if(!name.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty() && !address.isEmpty()
                && !additionalNotes.isEmpty()){
            for(Client c : UserHandler.getUser().getClients()){
                if(c.getName().equals(name)){
                    Toast.makeText(this, R.string.ClientAlreadyExists, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            Client client = new Client(name, email, phoneNumber, address, additionalNotes);
            UserHandler.getUser().addClient(client);
            Toast.makeText(this, R.string.ClientCreated, Toast.LENGTH_SHORT).show();
            finish();

        }else{
            Toast.makeText(this, R.string.FillAllFields, Toast.LENGTH_SHORT).show();
        }

    }

}