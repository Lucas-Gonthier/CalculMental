package com.example.calculmental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculmental.database.ScoreBaseHelper;
import com.example.calculmental.database.ScoreDao;
import com.example.calculmental.entities.Score;

import java.util.List;

public class ActivityHighscores extends AppCompatActivity {
    private ScoreDao scoreDao;
    private ListView listView;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_records); // Utilisez game_records.xml
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.list_high_scores); // Récupérez la référence à la ListView
        scoreDao = new ScoreDao(new ScoreBaseHelper(this,"score.db",1));
        long nombreScores = scoreDao.count();
        if(nombreScores > 0){
            List<Score> topScores = scoreDao.getTopScores(10); // Récupérez les 10 meilleurs scores

            // Créez un adaptateur pour la ListView et définissez-le
            HighscoreAdapter adapter = new HighscoreAdapter(this, topScores);
            listView.setAdapter(adapter);
        }

        btnHome = findViewById(R.id.btn_home);
        btnHome.setOnClickListener(view -> {
            finish();
        });
    }
}

