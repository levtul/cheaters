package cheaters.manager;

import cheaters.croupier.Croupier;

import java.util.Scanner;

/**
 * Class that gives console interface to our game
 */
public class GameManager {
    private final Scanner reader = new Scanner(System.in);
    int fairPlayersCount;
    int cheatersCount;

    /**
     * Method reads fair players and cheaters counts from console
     */
    private void readArgs() {
        do {
            System.out.println("Please, enter fair players count, integer number in [1; 1000]");
            if (reader.hasNextInt()) {
                fairPlayersCount = reader.nextInt();
            }
            reader.nextLine();
        } while (fairPlayersCount <= 0 || fairPlayersCount > 1000);
        do {
            System.out.println("Please, enter cheaters count, integer number in [0; 1000]");
            if (reader.hasNextInt()) {
                cheatersCount = reader.nextInt();
            }
            reader.nextLine();
        } while (cheatersCount < 0 || cheatersCount > 1000);
    }

    /**
     * Method suggests to user to restart a game with new or same player counts, or to quit game
     */
    private void restart() {
        System.out.println("Game is over, do you want to play again? Answer y/n");
        while (true) {
            String reply = reader.nextLine();
            if (reply.equals("y")) {
                System.out.println("Nice, do you want to change players counts? Answer y/n");
                while (true) {
                    reply = reader.nextLine();
                    if (reply.equals("y")) {
                        play();
                        return;
                    } else if (reply.equals("n")) {
                        playWithoutReading();
                        return;
                    }
                    System.out.println("Sorry, I don't understand, please type y or n to answer");
                }
            } else if (reply.equals("n")) {
                System.out.println("Goodbye!");
                return;
            }
            System.out.println("Sorry, I don't understand, please type y or n to answer");
        }
    }

    /**
     * Read counts and play whole game
     */
    public void play() {
        readArgs();
        Croupier.startGame(fairPlayersCount, cheatersCount);
        restart();
    }

    /**
     * Play game without reading counts
     * <br> Note: they must be initialized before, so use this method only when restarting game
     */
    private void playWithoutReading() {
        Croupier.startGame(fairPlayersCount, cheatersCount);
        restart();
    }
}
