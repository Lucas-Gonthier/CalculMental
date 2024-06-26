package com.example.calculmental;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculmental.database.ScoreBaseHelper;
import com.example.calculmental.database.ScoreDao;
import com.example.calculmental.entities.Score;

public class ActivityHighscores extends AppCompatActivity {
        private ScoreDao scoreDao;
        private TextView txt_userName;
        private TextView txt_score;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                EdgeToEdge.enable(this);
                setContentView(R.layout.activity_records);
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                        return insets;
                });
                txt_userName = findViewById(R.id.text_username);
                txt_score = findViewById(R.id.text_score);
                scoreDao = new ScoreDao(new ScoreBaseHelper(this,"score.db",1));
                long nombreScores = scoreDao.count();
                if(nombreScores > 0){
                        Score MeilleurScore = scoreDao.getTopScores(1).get(0);
                        txt_userName.setText((MeilleurScore.getUserName()));
                        txt_score.setText(String.valueOf(MeilleurScore.getScore()));
                }else {
                        txt_userName.setText("Aucun score enregistré");
                        txt_score.setText("0");
                }

        }
}

