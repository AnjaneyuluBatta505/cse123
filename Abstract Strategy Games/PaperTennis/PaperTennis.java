import java.util.Scanner;

public class PaperTennis implements AbstractStrategyGame {
    private int[] scores;
    private int currentPlayer;
    private boolean gameOver;

    public PaperTennis() {
        scores = new int[2];
        currentPlayer = 1;
        gameOver = false;
    }

    @Override
    public String instructions() {
        return "Instructions for Paper Tennis:\n" +
               "Players take turns to score points by making moves.\n" +
               "To make a move, type 'score x', where 'x' is the number of points to score.\n" +
               "The first player to reach 10 points wins.\n" +
               "The current scores are displayed after each turn.\n";
    }

    @Override
    public String toString() {
        return "Current scores:\n" +
               "Player 1: " + scores[0] + "\n" +
               "Player 2: " + scores[1] + "\n";
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public int getWinner() {
        if (!gameOver) return -1;
        if (scores[0] >= 10) return 1;
        if (scores[1] >= 10) return 2;
        return 0; // This case should not occur, but just for safety.
    }

    @Override
    public int getNextPlayer() {
        return isGameOver() ? -1 : currentPlayer;
    }

    @Override
    public void makeMove(Scanner input) {
        if (isGameOver()) {
            throw new IllegalArgumentException("Game is over. No more moves can be made.");
        }

        String command = input.next();
        int points;

        if (!command.equals("score") || !input.hasNextInt()) {
            throw new IllegalArgumentException("Invalid move. Use the format 'score x' where x is the points.");
        }

        points = input.nextInt();
        if (points <= 0) {
            throw new IllegalArgumentException("Points must be a positive number.");
        }

        scores[currentPlayer - 1] += points;

        if (scores[currentPlayer - 1] >= 10) {
            gameOver = true;
        }

        // Switch players
        currentPlayer = (currentPlayer == 1) ? 2 : 1; // Switch to the other player
    }
}
