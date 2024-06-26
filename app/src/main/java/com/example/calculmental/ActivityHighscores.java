package com.example.calculmental;

import static androidx.core.content.ContentProviderCompat.requireContext;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calculmental.db.database.ScoreBaseHelper;
import com.example.calculmental.db.database.ScoreDao;
import com.example.calculmental.db.entities.Score;

import java.util.List;

public class ActivityHighscores {
        private ScoreDao scoreDao;
        private ListView listView;

        @Nullable
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.game_records, container, false);
            listView = view.findViewById(R.id.list_high_scores);
            return view;
        }

        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            Context context = requireContext();
            scoreDao = new ScoreDao(new ScoreBaseHelper(context,"BDD",1));

            List<Score> topScores = scoreDao.getTopScores(10);
            HighscoreAdapter adapter = new HighscoreAdapter(getContext(), topScores);
            listView.setAdapter(adapter);
        }
}

