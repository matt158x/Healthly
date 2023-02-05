package com.example.healthly;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstLogin extends AppCompatActivity {

    private EditText nameEditText;
    private EditText weightEditText;
    private EditText tallEditText;
    private Button SaveButton;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        nameEditText = findViewById(R.id.name);
        weightEditText = findViewById(R.id.weight);
        tallEditText = findViewById(R.id.tall);
        SaveButton = findViewById(R.id.save_btn);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                myRef = database.getReference("Users").child(userId).child("Informations");
                String name = nameEditText.getText().toString();
                String weight = weightEditText.getText().toString();
                String tall = tallEditText.getText().toString();

                myRef.child("Name").setValue(name);
                myRef.child("Weight").setValue(weight);
                myRef.child("Tall").setValue(tall);

                Toast.makeText(FirstLogin.this, "User data has been saved", Toast.LENGTH_SHORT).show();
                Intent mainIntent = new Intent(FirstLogin.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}