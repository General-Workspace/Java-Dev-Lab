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

    public int calculateScore() {
        int finalScore = this.score;

        if (this.gameOver) {
            finalScore += (this.levelCompleted * this.bonus);
            finalScore += 1000;
        }

        return finalScore;
    }
}
