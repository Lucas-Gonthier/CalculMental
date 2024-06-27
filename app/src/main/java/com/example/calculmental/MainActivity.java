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
    private Button boutonCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonJouer = findViewById(R.id.btn_understood);
        boutonJouer.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityRules.class);
            startActivity(intent);
        });
        boutonRecords = findViewById(R.id.btn_highscore);
        boutonRecords.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityHighscores.class);
            startActivity(intent);
        });
        boutonCredits = findViewById(R.id.btn_credits);
        boutonCredits.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityCredits.class);
            startActivity(intent);
        });
        MediaPlayerManager.mediaPlayer.start();
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
            if (MediaPlayerManager.mediaPlayer.isPlaying()) {
                MediaPlayerManager.mediaPlayer.pause();
                item.setIcon(R.drawable.musicoff);
            } else {
                MediaPlayerManager.mediaPlayer.start();
                item.setIcon(R.drawable.musicon);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (MediaPlayerManager.mediaPlayer != null) {
            if (MediaPlayerManager.mediaPlayer.isPlaying()) {
                MediaPlayerManager.mediaPlayer.stop();
            }
            MediaPlayerManager.mediaPlayer.release();
            MediaPlayerManager.mediaPlayer = null;
        }
    }
}