package com.example.calculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculmental.database.ScoreBaseHelper;
import com.example.calculmental.database.ScoreDao;
import com.example.calculmental.entities.Score;

public class ActivitySaveScore extends AppCompatActivity {

    private EditText editName;
    private Button btnSaveScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_score);

        Intent intent = getIntent();
        int playerScore = intent.getIntExtra("player_score", 0); // '0' est la valeur par défaut si "player_score" n'existe pas

        btnSaveScore = findViewById(R.id.btn_save_score);
        editName = findViewById(R.id.edit_name);

        btnSaveScore.setOnClickListener(view -> {
            if(!editName.getText().toString().isEmpty()) {
                Score score = new Score();
                score.setUserName(editName.getText().toString());
                score.setScore(playerScore);
                ScoreDao scoreDao = new ScoreDao(new ScoreBaseHelper(this, "score.db", 1));
                scoreDao.create(score);

                Intent intentHighscores = new Intent(this, ActivityHighscores.class);
                startActivity(intentHighscores);
            } else {
                editName.setError(getString(R.string.username_error));
            }
        });

    }
}
