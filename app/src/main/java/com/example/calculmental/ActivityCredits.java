package com.example.calculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityCredits extends AppCompatActivity {
    private Button btnHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        btnHome = findViewById(R.id.btn_home);
        btnHome.setOnClickListener(view -> {
            finish();
        });
    }
}
