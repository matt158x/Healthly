package com.example.healthly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_menu_item:
                        // jestem tutaj wiec nie musze nic robic
                        break;
                    case R.id.dishes_menu_item:
                        // dodaj kod do obsługi kliknięcia w pozycję menu "Dishes"
                        break;
                    case R.id.workout_menu_item:
                        // dodaj kod do obsługi kliknięcia w pozycję menu "Workout"
                        break;
                    case R.id.others_menu_item:
                        // dodaj kod do obsługi kliknięcia w pozycję menu "Others"
                        break;
                    case R.id.profile_menu_item:
                        Intent intent = new Intent(MainActivity.this, ProfileBoardActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });


    }




}