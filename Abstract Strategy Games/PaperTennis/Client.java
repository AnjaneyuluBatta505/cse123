import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AbstractStrategyGame game = new PaperTennis();
        System.out.println(game.instructions());

        while (!game.isGameOver()) {
            System.out.println(game.toString());
            int nextPlayer = game.getNextPlayer();
            System.out.printf("Player %d's turn. Enter your move: ", nextPlayer);
            try {
                game.makeMove(scanner);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(game.toString()); // Show final game state
        int winner = game.getWinner();
        if (winner > 0) {
            System.out.printf("Player %d wins!\n", winner);
        }
        scanner.close();
    }
}
