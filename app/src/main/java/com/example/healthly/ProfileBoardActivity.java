package com.example.healthly;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileBoardActivity extends AppCompatActivity {

    private Button logout_btn;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userID;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_board);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_menu_item:
                        Intent intent = new Intent(ProfileBoardActivity.this, MainActivity.class);
                        startActivity(intent);
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
                        // już jesteś w ProfileBoardActivity, więc nie musisz tu nic robić
                        break;
                }
                return true;
            }
        });





    logout_btn=findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();

            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        final TextView nameTextView = (TextView) findViewById(R.id.name_text_view);
        final TextView weightTextView = (TextView) findViewById(R.id.weight_text_view);
        final TextView tallTextView = (TextView) findViewById(R.id.tall_text_view);

;
        Toast.makeText(ProfileBoardActivity.this, "TEST2", Toast.LENGTH_LONG);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(ProfileBoardActivity.this, "TEST1", Toast.LENGTH_LONG);

                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null) {
                    Toast.makeText(ProfileBoardActivity.this, "TEST", Toast.LENGTH_LONG);
                    String name = userProfile.name;
                    String email = userProfile.mail;
                    String age = userProfile.age;
                    String weight = userProfile.weight;
                    String tall = userProfile.tall;


                    nameTextView.setText(name);
                    weightTextView.setText(weight);
                    tallTextView.setText(tall);


                    // Obliczenie BMI
                    double weightValue = Double.parseDouble(weight);
                    double tallValue = Double.parseDouble(tall) / 100.0;
                    double bmiValue = weightValue / (tallValue * tallValue);
                    String bmiString = String.format("%.1f", bmiValue);

                    TextView bmiTextView = findViewById(R.id.bmi_text_view);
                    bmiTextView.setText("BMI: " + bmiString);

                    // Określenie jakości BMI i wyświetlenie komentarza
                    String bmiQuality;
                    String bmiComment;
                    if(bmiValue < 18.5) {
                        bmiQuality = "Niedowaga";
                        bmiComment = "Twoja waga jest zbyt niska. Skontaktuj się z lekarzem, aby uzyskać poradę dotyczącą zdrowego przyrostu masy ciała.";
                    } else if(bmiValue < 25) {
                        bmiQuality = "Prawidłowa waga";
                        bmiComment = "Twoje BMI wskazuje na prawidłową masę ciała. Zachowaj zdrowy styl życia, aby utrzymać swoją wagę na tym poziomie.";
                    } else if(bmiValue < 30) {
                        bmiQuality = "Nadwaga";
                        bmiComment = "Twoja waga jest zbyt wysoka. Spróbuj zmniejszyć ilość spożywanych kalorii i zwiększyć aktywność fizyczną, aby schudnąć.";
                    } else {
                        bmiQuality = "Otyłość";
                        bmiComment = "Twoja waga jest znacznie zbyt wysoka. Skontaktuj się z lekarzem, aby uzyskać poradę dotyczącą zdrowego sposobu na utratę wagi.";
                    }

                    TextView bmiInfoTextView = findViewById(R.id.bmi_info_text_view);
                    bmiInfoTextView.setText(bmiQuality + ": " + bmiComment);
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileBoardActivity.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
            }
        });

    }




    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(ProfileBoardActivity.this, LoginActivity.class));
    }


}

