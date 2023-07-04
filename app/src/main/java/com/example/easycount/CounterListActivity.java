package com.example.easycount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CounterListActivity extends AppCompatActivity {

    private String profileName;
    private TextView profileNameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_list);
        Intent intent = getIntent();
        profileName = intent.getStringExtra("profileName");
        setViewElements();
    }

    public void setViewElements(){
        profileNameLabel = findViewById(R.id.profile_name_label);
        profileNameLabel.setText(profileName);
    }
}