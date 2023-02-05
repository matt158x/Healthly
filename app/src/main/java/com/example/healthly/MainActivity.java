package com.example.healthly;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mauth;
    private Button logout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        logout_btn=findViewById(R.id.logout_btn);
        mauth=FirebaseAuth.getInstance();
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();

            }
        });
    }

    @Override
    public  void onStart(){
        super.onStart();
        FirebaseUser currentUser=mauth.getCurrentUser();
        if (currentUser==null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }
}