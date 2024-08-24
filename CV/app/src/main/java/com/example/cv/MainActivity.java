package com.example.cv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etEmail;
    CheckBox cbDesign;
    CheckBox cbSoftwareEngineering;
    CheckBox cbProgramming;
    CheckBox cbWebAppDevelopment;
    RadioGroup rgGender;
    Spinner countrySpinner;
    TextView tvAgeNumber;
    SeekBar sbSetAge;
    Button submitBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        cbDesign = findViewById(R.id.design);
        cbSoftwareEngineering = findViewById(R.id.softwareEngineering);
        cbProgramming = findViewById(R.id.programming);
        cbWebAppDevelopment = findViewById(R.id.webAppDevelopment);
        rgGender = findViewById(R.id.gender);
        countrySpinner = findViewById(R.id.countrySpinner);
        tvAgeNumber = findViewById(R.id.ageNumber);
        sbSetAge = findViewById(R.id.setAge);
        submitBtn = findViewById(R.id.submit);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.country_names)
        );

        countrySpinner.setAdapter(adapter);
        sbSetAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAgeNumber.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    intent = new Intent(MainActivity.this, DisplayData.class);

                    intent.putExtra("name", etName.getText().toString());
                    intent.putExtra("email", etEmail.getText().toString());

                    // Create a StringBuilder to accumulate selected skills
                    StringBuilder skillsBuilder = new StringBuilder();
                    if (cbDesign.isChecked()) {
                        skillsBuilder.append("Designer, ");
                    }
                    if (cbSoftwareEngineering.isChecked()) {
                        skillsBuilder.append("Software Engineer, ");
                    }
                    if (cbProgramming.isChecked()) {
                        skillsBuilder.append("Programming, ");
                    }
                    if (cbWebAppDevelopment.isChecked()) {
                        skillsBuilder.append("Web App Developer, ");
                    }

                    // Remove the trailing comma and space
                    String selectedSkills = skillsBuilder.toString().replaceAll(", $", "");
                    intent.putExtra("skill", selectedSkills);

                    RadioButton selectedRadioButton = findViewById(rgGender.getCheckedRadioButtonId());
                    String genderText = "";
                    if (selectedRadioButton != null) {
                        genderText = selectedRadioButton.getText().toString();
                    }
                    intent.putExtra("gender", genderText);

                    String[] countryNames = getResources().getStringArray(R.array.country_names);
                    String selectedCountry = "";
                    int selectedPosition = countrySpinner.getSelectedItemPosition();
                    if (selectedPosition != AdapterView.INVALID_POSITION && selectedPosition < countryNames.length) {
                        selectedCountry = countryNames[selectedPosition];
                    }
                    intent.putExtra("country", String.valueOf(selectedCountry));


                    intent.putExtra("age", String.valueOf(sbSetAge.getProgress()));

                    startActivity(intent);
                }
            }
        });
    }
    public boolean validate() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        boolean check = cbDesign.isChecked() || cbSoftwareEngineering.isChecked() || cbProgramming.isChecked() || cbWebAppDevelopment.isChecked();
        int genderRadioButtonId = rgGender.getCheckedRadioButtonId();
        if (name.isEmpty()) {
            etName.setError("Please enter your Name");
            return false;
        }
        if (email.isEmpty()) {
            etEmail.setError("Please enter your Email");
            return false;
        }
        if (!check) {
            Toast.makeText(MainActivity.this, "Please select at least one skill", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (genderRadioButtonId == -1) {
            // No radio button selected
            Toast.makeText(MainActivity.this, "Please select gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
