package com.example.inputcontrolls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar sb = (SeekBar) findViewById(R.id.testSeekBar);
        ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton);
        RadioButton rb1 = (RadioButton) findViewById(R.id.male);
        RadioButton rb2 = (RadioButton) findViewById(R.id.female);
        RadioButton rb3 = (RadioButton) findViewById(R.id.other);

            int v,max;
            v = sb.getProgress();
            max = sb.getMax();
        TextView tv = (TextView) findViewById(R.id.value);
        tv.setText(String.valueOf(v));
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv.setText("Adjusting volume...");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv.setText(progress + "/" + seekBar.getMax());

            }
        });

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rb1.isChecked()){
                    Toast.makeText(MainActivity.this, "Male selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rb2.isChecked()){
                    Toast.makeText(MainActivity.this, "Female selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rb3.isChecked()){
                    Toast.makeText(MainActivity.this, "Other selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AutoCompleteTextView tv1 = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        String [] countries = getResources().getStringArray(R.array.country_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1,countries);
        tv1.setAdapter(adapter);
    }
}