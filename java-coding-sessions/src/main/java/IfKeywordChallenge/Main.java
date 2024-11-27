package IfKeywordChallenge;

public class Main {
    public static void main(String[] args) {

        CalculateScore player1Score = new CalculateScore(true, 800, 5, 100);
        player1Score.calculateScore();

        CalculateScore player2Score = new CalculateScore(true, 10000, 8, 200);
        player2Score.calculateScore();
    }
}
