package IfKeywordChallenge;

public class CalculateScore {
    private final boolean gameOver;
    private final int score;
    private final int levelCompleted;
    private final int bonus;

    public CalculateScore(boolean gameOver, int score, int levelCompleted, int bonus) {
        this.gameOver = gameOver;
        this.score = score;
        this. levelCompleted = levelCompleted;
        this.bonus = bonus;
    }

    public void calculateScore() {
        int finalScore = score;

        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            finalScore += 1000;
            System.out.printf("Your final score is %d%n", finalScore);
        }
    }
}
