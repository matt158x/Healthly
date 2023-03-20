package com.example.healthly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mauth;
    private EditText editText_email,editText_password, editText_name, editText_weight, editText_tall, editText_age;
    private Button register_btn;
    private TextView login_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mauth=FirebaseAuth.getInstance();
        editText_email=findViewById(R.id.reg_mail);
        editText_password=findViewById(R.id.reg_password);
        register_btn=findViewById(R.id.register_btn);
        login_text=findViewById(R.id.login_text);
        editText_name = findViewById(R.id.name);
        editText_weight = findViewById(R.id.weight);
        editText_tall = findViewById(R.id.tall);
        editText_age = findViewById(R.id.age);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();

            }
        });
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void Register() {


        String user= editText_email.getText().toString().trim();
        String pass= editText_password.getText().toString().trim();
        String email = editText_email.getText().toString().trim();
        String name = editText_name.getText().toString().trim();
        String weight = editText_weight.getText().toString().trim();
        String tall = editText_tall.getText().toString().trim();
        String age = editText_age.getText().toString().trim();

        if (user.isEmpty()){
            editText_email.setError("Email can not be empty..");

        }if (pass.isEmpty()){
            editText_password.setError("Password can not be empty..");
        }
        else{
            mauth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        User user = new User(email, name, weight, tall, age);

                        FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(RegisterActivity.this,ProfileBoardActivity.class));
                                        } else{
                                            Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });





                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Failed!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}