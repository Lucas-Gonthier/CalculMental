package com.example.calculmental;

public class ScoreManagement {
    private static int score = 0;

    public static void setScore(int score) {
        ScoreManagement.score = score;
    }

    public static int getScore() {return score;}
}
