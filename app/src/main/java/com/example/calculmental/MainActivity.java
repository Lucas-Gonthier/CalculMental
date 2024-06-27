package com.example.calculmental;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private Button boutonJouer;
    private Button boutonRecords;
    private MediaPlayer mediaPlayer;

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
        mediaPlayer = MediaPlayer.create(this, R.raw.aliensong);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menugame, menu);
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.getItemId() != R.id.btn_son) {
                item.setVisible(false);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.btn_son) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                item.setIcon(R.drawable.musicoff);
            } else {
                mediaPlayer.start();
                item.setIcon(R.drawable.musicon);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer resources when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}