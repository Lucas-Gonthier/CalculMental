package com.example.calculmental;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityGame extends AppCompatActivity {
    private static final String[] OPERATORS = {"+", "-", "*", "/"};
    private Button boutonReponseUn;
    private Button boutonReponseDeux;
    private Button boutonReponseTrois;
    private Button boutonReponseQuatre;
    private TextView txtCalcul;
    private TextView txtQuestion;
    private int score = 0;
    private int questionNumber = 1;
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
            setup();
        }
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
}