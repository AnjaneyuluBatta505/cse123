import java.util.Scanner;

public class ConnectFour implements AbstractStrategyGame {
    private final int rows = 6;
    private final int cols = 7;
    private final char[][] board;
    private int currentPlayer;
    private boolean gameOver;
    
    public ConnectFour() {
        board = new char[rows][cols];
        currentPlayer = 1;
        gameOver = false;
        
        // Initialize the board with empty spaces
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
    }

    @Override
    public String instructions() {
        return "Instructions for Connect Four:\n" +
               "The board is a 7x6 grid. Players take turns selecting a column to place a token.\n" +
               "The token will fall to the lowest available space in that column.\n" +
               "Players win by placing four of their tokens in a row horizontally, vertically, or diagonally.\n" +
               "Players can also choose to remove one of their tokens from the bottom of a column.\n" +
               "To make a move, type 'place x' where x is the column number (0-based index) to place a token, \n" +
               "or 'remove x' to remove your token from the bottom of a column.\n";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append("| ");
            for (int j = 0; j < cols; j++) {
                sb.append(board[i][j]).append(" | ");
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
        return currentPlayer;
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

        System.out.print("Enter your move ('place x' or 'remove x'): ");
        String command = input.next();
        int col = input.nextInt();

        if (col < 0 || col >= cols) {
            throw new IllegalArgumentException("Invalid column. Choose a column between 0 and 6.");
        }

        if (command.equals("place")) {
            placeToken(col);
        } else if (command.equals("remove")) {
            removeToken(col);
        } else {
            throw new IllegalArgumentException("Invalid command. Use 'place' or 'remove'.");
        }

        if (checkWin()) {
            gameOver = true;
        } else {
            currentPlayer = (currentPlayer == 1) ? 2 : 1; // Switch player
        }
    }

    private void placeToken(int col) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][col] == ' ') {
                board[i][col] = (currentPlayer == 1) ? 'X' : 'O'; // 'X' for Player 1, 'O' for Player 2
                return;
            }
        }
        throw new IllegalArgumentException("Column is full. Choose a different column.");
    }

    private void removeToken(int col) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][col] == (currentPlayer == 1 ? 'X' : 'O')) {
                // Shift all tokens in the column down by one
                for (int j = i; j > 0; j--) {
                    board[j][col] = board[j - 1][col];
                }
                board[0][col] = ' '; // Empty the topmost position
                return;
            }
        }
        throw new IllegalArgumentException("No token to remove in the bottom of the column.");
    }

    // Check if the current player has won the game
    private boolean checkWin() {
        char token = (currentPlayer == 1) ? 'X' : 'O';

        // Check horizontal, vertical, and diagonal lines
        return checkHorizontal(token) || checkVertical(token) || checkDiagonal(token);
    }

    // Check horizontal win condition
    private boolean checkHorizontal(char token) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                if (board[i][j] == token && board[i][j + 1] == token && board[i][j + 2] == token && board[i][j + 3] == token) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check vertical win condition
    private boolean checkVertical(char token) {
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i <= rows - 4; i++) {
                if (board[i][j] == token && board[i + 1][j] == token && board[i + 2][j] == token && board[i + 3][j] == token) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check diagonal win condition
    private boolean checkDiagonal(char token) {
        // Check downward diagonal
        for (int i = 0; i <= rows - 4; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                if (board[i][j] == token && board[i + 1][j + 1] == token && board[i + 2][j + 2] == token && board[i + 3][j + 3] == token) {
                    return true;
                }
            }
        }

        // Check upward diagonal
        for (int i = 3; i < rows; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                if (board[i][j] == token && board[i - 1][j + 1] == token && board[i - 2][j + 2] == token && board[i - 3][j + 3] == token) {
                    return true;
                }
            }
        }

        return false;
    }
}
