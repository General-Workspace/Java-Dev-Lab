package IfKeywordChallenge;

public class Main {
    public static void main(String[] args) {

        CalculateScore player1Score = new CalculateScore(true, 800, 5, 100);
        var highScore = player1Score.calculateScore();
        System.out.println("Player 1 high Score is " + highScore);

        CalculateScore player2Score = new CalculateScore(true, 10000, 8, 200);
        highScore = player2Score.calculateScore();
        System.out.println("Player 2 high Score is " + highScore);

        MethodsChallenge challenge = new MethodsChallenge();

        var highScorePosition = challenge.calculateHighScorePosition(1500);
        challenge.displayHighScore("Aniebiet", highScorePosition);

        highScorePosition = challenge.calculateHighScorePosition(1000);
        challenge.displayHighScore("Yetunde", highScorePosition);

        highScorePosition = challenge.calculateHighScorePosition(500);
        challenge.displayHighScore("Bruce", highScorePosition);

        highScorePosition = challenge.calculateHighScorePosition(100);
        challenge.displayHighScore("Carly", highScorePosition);

        highScorePosition = challenge.calculateHighScorePosition(25);
        challenge.displayHighScore("James", highScorePosition);
    }
}
