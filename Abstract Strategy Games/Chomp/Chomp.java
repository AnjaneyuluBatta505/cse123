import java.util.Scanner;

public class Chomp implements AbstractStrategyGame {
    private int[][][] board; // 3D board to represent layers
    private int currentPlayer;
    private boolean gameOver;
    private final int layers = 3; // Number of layers
    private final int rows = 4;   // Rows in the game grid
    private final int cols = 4;   // Columns in the game grid

    public Chomp() {
        board = new int[layers][rows][cols]; // 3 layers of 4x4 grid
        currentPlayer = 1;
        gameOver = false;

        // Initialize all layers with 1 to indicate unchomped squares
        for (int layer = 0; layer < layers; layer++) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    board[layer][i][j] = 1; // 1 means square is present
                }
            }
        }
    }

    @Override
    public String instructions() {
        return "Instructions for Chomp:\n" +
               "The game is played on a 4x4 grid with 3 layers.\n" +
               "Players take turns choosing a square, and all squares to the right and below the chosen square\n" +
               "on the same layer will be chomped (removed).\n" +
               "Once a layer is cleared, the layer below it becomes visible.\n" +
               "The player who chomps the top-leftmost square on the final layer loses the game.\n" +
               "To make a move, enter the row and column of the square to chomp (0-based index).\n";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int layer = 0; layer < layers; layer++) {
            sb.append("Layer ").append(layer + 1).append(":\n");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sb.append(board[layer][i][j] == 1 ? "O " : "X ");
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public int getWinner() {
        if (!gameOver) return -1;
        return currentPlayer == 1 ? 2 : 1; // The other player wins
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

        System.out.print("Enter row and column to chomp (0-based index): ");
        int row = input.nextInt();
        int col = input.nextInt();
        int layer = getTopLayer(row, col);

        // Validate move
        if (row < 0 || row >= rows || col < 0 || col >= cols || layer == -1 || board[layer][row][col] == 0) {
            throw new IllegalArgumentException("Invalid move. Square is already chomped or out of bounds.");
        }

        // Chomp squares on the topmost visible layer
        chompLayer(layer, row, col);

        // Check if the move chomps the top-leftmost square on the final layer
        if (layer == layers - 1 && row == 0 && col == 0) {
            gameOver = true;
        }

        if (!gameOver) {
            // Switch to the next player
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
        }
    }

    // Find the topmost visible layer at a specific row and column
    private int getTopLayer(int row, int col) {
        for (int layer = 0; layer < layers; layer++) {
            if (board[layer][row][col] == 1) {
                return layer;
            }
        }
        return -1; // This should not happen if a valid move is made
    }

    // Chomp the current layer starting from (row, col) and everything to the right and below
    private void chompLayer(int layer, int row, int col) {
        for (int i = row; i < rows; i++) {
            for (int j = col; j < cols; j++) {
                board[layer][i][j] = 0; // Mark as chomped
            }
        }
    }
}
