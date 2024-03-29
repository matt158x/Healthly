package com.example.healthly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editText_mail,editText_password;
    private Button login_btn;
    private TextView reg_text;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth=FirebaseAuth.getInstance();
        editText_mail=findViewById(R.id.login_mail);
        editText_password=findViewById(R.id.login_password);
        login_btn=findViewById(R.id.login_btn);
        reg_text=findViewById(R.id.register_text);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        reg_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

            }
        });
    }
    private void login() {
        String email= editText_mail.getText().toString().trim();
        String pass= editText_password.getText().toString().trim();
        if (email.isEmpty()){
            editText_mail.setError("Email can not be empty..");

        }if (pass.isEmpty()){
            editText_password.setError("Password can not be empty..");
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login Successfully..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, ProfileBoardActivity.class));


                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Login Failed!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}