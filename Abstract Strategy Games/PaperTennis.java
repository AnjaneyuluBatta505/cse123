public class PaperTennisGame implements AbstractStrategyGame {
    private boolean gameOver;
    private int ballPosition;  // 0 for player 1, 1 for player 2
    private int currentPlayer;

    public PaperTennisGame() {
        gameOver = false;
        ballPosition = 0;  // Ball starts with player 1
        currentPlayer = 1;
    }

    @Override
    public String instructions() {
        return "Paper Tennis: Players take turns hitting the ball back and forth. " +
               "If a player misses, the game ends.";
    }

    @Override
    public String toString() {
        if (gameOver) {
            return "Game Over! Player " + (ballPosition == 0 ? "2" : "1") + " wins!";
        }
        return "Player " + currentPlayer + "'s turn. Ball is with Player " + (ballPosition == 0 ? "1" : "2");
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void takeTurn(String player, String action) {
        if (gameOver) {
            System.out.println("The game is already over.");
            return;
        }

        // Determine the current player based on ball position
        int playerNum = player.equals("Player 1") ? 1 : 2;

        // Validate the current player
        if (playerNum != currentPlayer) {
            System.out.println("It's not your turn.");
            return;
        }

        if (action.equalsIgnoreCase("hit")) {
            // Switch ball position and current player
            ballPosition = 1 - ballPosition;
            currentPlayer = ballPosition == 0 ? 1 : 2;
        } else if (action.equalsIgnoreCase("miss")) {
            // End the game
            gameOver = true;
        } else {
            System.out.println("Invalid action. Please choose 'hit' or 'miss'.");
        }
    }

    @Override
    public String currentPlayer() {
        return "Player " + currentPlayer;
    }
}
