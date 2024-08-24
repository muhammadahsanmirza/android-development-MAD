package com.example.cv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayData extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        // Retrieve intent extras
        intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String skill = intent.getStringExtra("skill");
        String gender = intent.getStringExtra("gender");
        String country = intent.getStringExtra("country");
        String age = intent.getStringExtra("age");

        TextView nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText(new StringBuilder().append("Name: ").append(name).toString());

        TextView emailTextView = findViewById(R.id.emailTextView);
        emailTextView.setText(new StringBuilder().append("Email: ").append(email).toString());

        TextView skillTextView = findViewById(R.id.skillTextView);
        skillTextView.setText(new StringBuilder().append("Skills: ").append(skill).toString());

        TextView genderTextView = findViewById(R.id.genderTextView);
        genderTextView.setText(new StringBuilder().append("Gender: ").append(gender).toString());

        TextView countryTextView = findViewById(R.id.countryTextView);
        countryTextView.setText(new StringBuilder().append("Country: ").append(country).toString());

        TextView ageTextView = findViewById(R.id.ageTextView);
        ageTextView.setText(new StringBuilder().append("Age: ").append(age).toString());
    }
}