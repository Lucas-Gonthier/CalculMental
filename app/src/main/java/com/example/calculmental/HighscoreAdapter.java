package com.example.calculmental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.calculmental.entities.Score;

import java.util.List;

public class HighscoreAdapter extends ArrayAdapter<Score> {
    private final int resource;

    public HighscoreAdapter(Context context, List<Score> scores) {
        super(context, R.layout.list_item, scores);
        this.resource = R.layout.list_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(resource, null);
        }

        Score score = getItem(position);

        if (score != null) {
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvScore = view.findViewById(R.id.tvScore);

            String medalEmoji = "";
            switch (position) {
                case 0:
                    medalEmoji = " \uD83E\uDD47"; // Emoji de médaille d'or
                    break;
                case 1:
                    medalEmoji = " \uD83E\uDD48"; // Emoji de médaille d'argent
                    break;
                case 2:
                    medalEmoji = " \uD83E\uDD49"; // Emoji de médaille de bronze
                    break;
            }

            if (tvName != null) {
                tvName.setText(score.getUserName() + medalEmoji);
            }

            if (tvScore != null) {
                tvScore.setText(String.valueOf(score.getScore()));
            }
        }

        return view;
    }
}