package com.example.calculmental;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculmental.db.entities.Score;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityGame extends AppCompatActivity {
    private static final String[] OPERATORS = {"+", "-", "*", "/"};
    private Button boutonReponseUn;
    private Button boutonReponseDeux;
    private Button boutonReponseTrois;
    private Button boutonReponseQuatre;
    private TextView txtCalcul;
    private TextView txtQuestion;
    private Button boutonSaveName;
    private Button boutonHome;
    private TextInputEditText txtInputName;
    private int score = 0;
    private int life = 3;
    private int questionNumber = 1;
    private MenuItem txt_lives;
    private MenuItem txt_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        boutonSaveName = findViewById(R.id.btn_saveName);
        boutonHome = findViewById(R.id.btn_home);
        txtInputName = findViewById(R.id.txtinput_name);

        boutonSaveName.setVisibility(View.GONE);
        boutonHome.setVisibility(View.GONE);
        txtInputName.setVisibility(View.GONE);

        boutonSaveName.setText("Enregistrer");
        boutonHome.setText("Accueil");

        setup();
    }

    private void setup() {
        txtQuestion = findViewById(R.id.txt_question);
        txtQuestion.setText("Question n°" + questionNumber);

        txtCalcul = findViewById(R.id.txt_calcul);
        txtCalcul.setText(generateRandomCalculation());
        String calculation = txtCalcul.getText().toString();
        double answer = performCalculation(calculation);
        boutonReponseUn = findViewById(R.id.btn_reponse1);
        boutonReponseDeux = findViewById(R.id.btn_reponse2);
        boutonReponseTrois = findViewById(R.id.btn_reponse3);
        boutonReponseQuatre = findViewById(R.id.btn_reponse4);

        Random random = new Random();
        int goodAnswer = random.nextInt(4) + 1;
        switch (goodAnswer){
            case 1:
                boutonReponseUn.setText(String.valueOf(answer));
                boutonReponseDeux.setText(String.valueOf(wrongAnswer(calculation)));
                boutonReponseTrois.setText(String.valueOf(wrongAnswer(calculation)));
                boutonReponseQuatre.setText(String.valueOf(wrongAnswer(calculation)));

                break;
            case 2:
                boutonReponseUn.setText(String.valueOf(wrongAnswer(calculation)));
                boutonReponseDeux.setText(String.valueOf(answer));
                boutonReponseTrois.setText(String.valueOf(wrongAnswer(calculation)));
                boutonReponseQuatre.setText(String.valueOf(wrongAnswer(calculation)));
                break;
            case 3:
                boutonReponseUn.setText(String.valueOf(wrongAnswer(calculation)));
                boutonReponseDeux.setText(String.valueOf(wrongAnswer(calculation)));
                boutonReponseTrois.setText(String.valueOf(answer));
                boutonReponseQuatre.setText(String.valueOf(wrongAnswer(calculation)));
                break;
            case 4:
                boutonReponseUn.setText(String.valueOf(wrongAnswer(calculation)));
                boutonReponseDeux.setText(String.valueOf(wrongAnswer(calculation)));
                boutonReponseTrois.setText(String.valueOf(wrongAnswer(calculation)));
                boutonReponseQuatre.setText(String.valueOf(answer));
                break;
            default:
                break;
        }

        boutonReponseUn.setOnClickListener(view -> appuyerBoutonReponse(boutonReponseUn, answer));
        boutonReponseDeux.setOnClickListener(view -> appuyerBoutonReponse(boutonReponseDeux, answer));
        boutonReponseTrois.setOnClickListener(view -> appuyerBoutonReponse(boutonReponseTrois, answer));
        boutonReponseQuatre.setOnClickListener(view -> appuyerBoutonReponse(boutonReponseQuatre, answer));


    }
    private void appuyerBoutonReponse(Button bouton, double answer) {
        if (isCorrectAnswer(bouton.getText().toString(), String.valueOf(answer))) {
            txtQuestion.setText("Bonne réponse !");
            score++;
            questionNumber++;
            setup();
        } else {
            txtQuestion.setText("Mauvaise réponse !");
            questionNumber++;
            life--;
            if (life == 0) {
                txtQuestion.setText("Game Over !");
                txtCalcul.setText("Score : " + score);
                boutonReponseUn.setVisibility(View.GONE);
                boutonReponseDeux.setVisibility(View.GONE);
                boutonReponseTrois.setVisibility(View.GONE);
                boutonReponseQuatre.setVisibility(View.GONE);
                boutonSaveName.setVisibility(View.VISIBLE);
                boutonHome.setVisibility(View.VISIBLE);
                txtInputName.setVisibility(View.VISIBLE);
            } else {
                setup();
            }
        }
        updateMenuItems();
    }

    private boolean isCorrectAnswer(String string, String answer) {
        return string.equals(answer);
    }

    public static String generateRandomCalculation() {
        Random random = new Random();
        int number1 = random.nextInt(100); // Génère un nombre aléatoire entre 0 et 99
        int number2 = random.nextInt(100); // Génère un nombre aléatoire entre 0 et 99
        String operator = OPERATORS[random.nextInt(OPERATORS.length)]; // Choisis un opérateur aléatoire
        if(operator.equals("/")) {
            while(number2 == 0) {
                number2 = random.nextInt(100);
            }
        }
        // Retourne le calcul sous forme de chaîne de caractères
        return number1 + " " + operator + " " + number2;
    }

    public static double performCalculation(String calculation) {
        String[] parts = calculation.split(" ");
        int number1 = Integer.parseInt(parts[0]);
        String operator = parts[1];
        int number2 = Integer.parseInt(parts[2]);

        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                return (double) number1 / number2;
            default:
                break;
        }
        return 0;
    }

    public static double wrongAnswer(String calculation) {
        Random random = new Random();
        double answer = performCalculation(calculation);
        int min = (int) answer - 20;
        int max = (int) answer + 20;
        double fakeAnswer = random.nextInt((max - min) + 1) + min;
        while (answer == fakeAnswer)
            fakeAnswer = random.nextInt((max - min) + 1) + min;
        return fakeAnswer;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menugame, menu);

        txt_lives = menu.findItem(R.id.txt_lives);
        txt_score = menu.findItem(R.id.txt_score);

        updateMenuItems();

        return super.onCreateOptionsMenu(menu);
    }

    private void updateMenuItems() {
        if (txt_lives != null && txt_score != null) {
            txt_lives.setTitle("Vies : " + life);
            txt_score.setTitle("Score : " + score);
        }
    }
}