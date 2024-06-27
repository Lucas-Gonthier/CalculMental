package com.example.calculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityRules extends AppCompatActivity {
    private Button btnUnderstood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        btnUnderstood = findViewById(R.id.btn_understood);
        btnUnderstood.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityGame.class);
            startActivity(intent);
        });
    }
}
