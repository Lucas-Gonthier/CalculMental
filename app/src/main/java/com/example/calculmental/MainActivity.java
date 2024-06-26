package com.example.calculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button boutonJouer;
    private Button boutonRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonJouer = findViewById(R.id.btn_game);
        boutonJouer.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityGame.class);
            startActivity(intent);
        });
        boutonRecords = findViewById(R.id.btn_highscore);
        boutonRecords.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityHighscores.class);
            startActivity(intent);
        });
    }
}