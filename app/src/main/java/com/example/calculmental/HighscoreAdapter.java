package com.example.calculmental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calculmental.db.entities.Score;

import java.security.AccessControlContext;
import java.util.List;

public class HighscoreAdapter extends ArrayAdapter<Score> {
    private final LayoutInflater inflater;

    public HighscoreAdapter(@NonNull AccessControlContext context, @NonNull List<Score> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_records, parent, false);
        }

        Score score = getItem(position);
        TextView usernameView = convertView.findViewById(R.id.text_username);
        TextView scoreView = convertView.findViewById(R.id.text_score);

        usernameView.setText(score.getUserName());
        scoreView.setText(String.valueOf(score.getScore()));

        return convertView;
    }
}
