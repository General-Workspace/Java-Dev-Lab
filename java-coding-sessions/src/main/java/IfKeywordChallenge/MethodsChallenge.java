package IfKeywordChallenge;

public class MethodsChallenge {
    public void displayHighScore(String playerName, int playerPosition) {
        System.out.printf("%s managed to get into position %d on the high score list.%n", playerName, playerPosition);
    }

    public int calculateHighScorePosition(int playerScore) {
        int result;
        if (playerScore >= 1000) {
            result = 1;
        } else if (playerScore >= 500) {
            result = 2;
        } else if (playerScore >= 100) {
            result = 3;
        } else {
            result = 4;
        }

        return result;
    }
}
