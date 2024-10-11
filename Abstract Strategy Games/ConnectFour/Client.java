import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AbstractStrategyGame game = new ConnectFour(); // Create ConnectFour game instance

        System.out.println(game.instructions());

        while (!game.isGameOver()) {
            System.out.println(game.toString());
            int nextPlayer = game.getNextPlayer();
            System.out.printf("Player %d's turn. Enter your move: ", nextPlayer);

            try {
                game.makeMove(scanner); // Take player's move
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Display final board state
        System.out.println(game.toString());

        // Announce the winner
        int winner = game.getWinner();
        if (winner > 0) {
            System.out.printf("Player %d wins!\n", winner);
        } else {
            System.out.println("The game ended with no winner.");
        }

        scanner.close();
    }
}
