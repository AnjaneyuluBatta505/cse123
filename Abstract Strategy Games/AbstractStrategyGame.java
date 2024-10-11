import java.util.Scanner;

public interface AbstractStrategyGame {

    /**
     * Returns a string describing how to play the game. 
     * Must include how to read the game state returned by toString(), 
     * how to make a move (as used by makeMove()), 
     * how the game ends, and who wins.
     */
    String instructions();

    /**
     * Constructs and returns a string representation of the current game state. 
     * This representation should contain all information that should be known 
     * to players at any point in the game, including board state (if any) 
     * and scores (if any).
     */
    String toString();

    /**
     * Returns true if the game has ended, and false otherwise.
     */
    boolean isGameOver();

    /**
     * Returns the (1-based) index of the player who has won the game, 
     * or -1 if the game is not over. If the game ended in a tie, returns 0.
     */
    int getWinner();

    /**
     * Returns the (1-based) index of the player who will take the next turn. 
     * If the game is over, returns -1.
     */
    int getNextPlayer();

    /**
     * Takes input from the parameter to specify the move the player with 
     * the next turn wishes to make, then executes that move. If any part 
     * of the move is illegal, throws an IllegalArgumentException.
     */
    void makeMove(Scanner input);
}
