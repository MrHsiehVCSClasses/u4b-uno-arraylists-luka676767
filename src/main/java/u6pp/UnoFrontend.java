package u6pp;

import java.util.Scanner;
// class creation
public class UnoFrontend {

    private Uno game;
    private Scanner scanner;
// runs game through new game and initalizes scanner
    public UnoFrontend(int numPlayers) {
        game = new Uno(numPlayers);
        scanner = new Scanner(System.in);
    }
// no return just play
    public void play() {

        System.out.println("Welcome to UNO!");

        while (game.getWinner() == null) {

            Player current = game.getCurrentPlayer();

            System.out.println("\nTop Card: " + game.getTopDiscard());
            System.out.println(current.getName() + "'s turn.");

            printHand(current);

            System.out.println("Enter card index to play, or D to draw:");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("D")) {

                game.playCard(null, null); // draw
                continue;
            }

            try {
                int index = Integer.parseInt(input);

                if (index < 0 || index >= current.getHand().size()) {
                    System.out.println("Invalid index.");
                    continue;
                }

                Card chosen = current.getHand().get(index);

                String chosenColor = null;

                if (chosen.isWild()) {
                    System.out.println("Choose color: RED, GREEN, BLUE, YELLOW");
                    chosenColor = scanner.nextLine().trim().toUpperCase();
                }

                boolean success = game.playCard(chosen, chosenColor);

                if (!success) {
                    System.out.println("Invalid play.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("Winner: " + game.getWinner().getName());
    }
// shows player hand
    private void printHand(Player player) {

        System.out.println("Your hand:");

        for (int i = 0; i < player.getHand().size(); i++) {
            System.out.println(i + ": " + player.getHand().get(i).getColor()
                    + " " + player.getHand().get(i).getValue());
        }
    }
}