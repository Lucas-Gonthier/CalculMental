package com.example.calculmental;

import android.annotation.SuppressLint;
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
    @SuppressLint("SetTextI18n")
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
        txtQuestion = findViewById(R.id.txt_question);
        txtQuestion.setText("Question n°");
        txtCalcul = findViewById(R.id.txt_calcul);
        txtCalcul.setText(generateRandomCalculation());
        boutonReponseUn = findViewById(R.id.btn_reponse1);
        boutonReponseUn.setText("Réponse 1");
        boutonReponseDeux = findViewById(R.id.btn_reponse2);
        boutonReponseDeux.setText("Réponse 2");
        boutonReponseTrois = findViewById(R.id.btn_reponse3);
        boutonReponseTrois.setText("Réponse 3");
        boutonReponseQuatre = findViewById(R.id.btn_reponse4);
        boutonReponseQuatre.setText("Réponse 4");

        boutonReponseUn.setOnClickListener(view -> appuyerBoutonReponse(boutonReponseUn));
        boutonReponseDeux.setOnClickListener(view -> appuyerBoutonReponse(boutonReponseDeux));
        boutonReponseTrois.setOnClickListener(view -> appuyerBoutonReponse(boutonReponseTrois));
        boutonReponseQuatre.setOnClickListener(view -> appuyerBoutonReponse(boutonReponseQuatre));

    }

    @SuppressLint("SetTextI18n")
    private void appuyerBoutonReponse(Button bouton) {
        if (isCorrectAnswer(bouton.getText().toString())) {
            txtQuestion.setText("Bonne réponse !");
            txtCalcul.setText(generateRandomCalculation());
        } else {
            txtQuestion.setText("Mauvaise réponse !");
        }
    }

    private boolean isCorrectAnswer(String string) {
        return string.equals("Réponse 1");
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
}