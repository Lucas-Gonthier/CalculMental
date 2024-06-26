package com.example.calculmental;

import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.calculmental.databinding.GameSaveScoreBinding;
import com.example.calculmental.db.database.ScoreBaseHelper;
import com.example.calculmental.db.database.ScoreDao;
import com.example.calculmental.db.entities.Score;

public class SaveScore extends Fragment{
    GameSaveScoreBinding binding;

    private ScoreDao scoreDao;

    @Override
    public View onCreateView(
            android.view.LayoutInflater inflater, android.view.ViewGroup container,
            android.os.Bundle savedInstanceState
    ) {

        binding = GameSaveScoreBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(android.view.View view, android.os.Bundle savedInstanceState) {

        scoreDao = new ScoreDao(new ScoreBaseHelper(getContext(),"BDD",1));

        int currentScore = ScoreManagement.getScore();
        super.onViewCreated(view, savedInstanceState);
        binding.textScore.setText(String.valueOf(currentScore));

        binding.btnSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.editName.getText().toString();
                if (username.isEmpty()) {
                    Toast.makeText(getActivity(), R.string.username_error, Toast.LENGTH_SHORT).show();
                } else {
                    Score score = new Score();
                    score.setScore(currentScore);
                    score.setUserName(username);
                    scoreDao.create(score);

                    NavController navController = Navigation.findNavController(v);
                    navController.popBackStack(R.id.main, false);
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
