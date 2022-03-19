package cheaters.croupier;

import cheaters.players.Cheater;
import cheaters.players.FairPlayer;
import cheaters.players.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class that manages one game: initializes players, starts threads, interrupts and joins threads
 */
public class Croupier {

    /**
     * Methods initializes players
     * @param fairPlayersCount count of fair players
     * @param cheatersCount count of cheaters
     * @return list of all game players
     */
    private static ArrayList<Player> init(int fairPlayersCount, int cheatersCount) {
        System.out.printf("""
                Preparing the game:
                Fair players count - %d
                Cheaters count     - %d
                """, fairPlayersCount, cheatersCount);
        ArrayList<FairPlayer> fairPlayers = new ArrayList<>();
        for (int i = 0; i < fairPlayersCount; i++) {
            fairPlayers.add(new FairPlayer(String.valueOf(i)));
        }
        ArrayList<Cheater> cheaters = new ArrayList<>();
        for (int i = 0; i < cheatersCount; i++) {
            cheaters.add(new Cheater(String.valueOf(i + fairPlayersCount), fairPlayers));
        }
        ArrayList<Player> players = new ArrayList<>(fairPlayers);
        players.addAll(cheaters);
        return players;
    }

    /**
     * Method for starting game by starting threads
     * @param players list of game players
     */
    private static void play(ArrayList<Player> players) {
        System.out.println("Starting game!");
        for (Player player : players) {
            player.start();
        }
    }

    /**
     * Interrupts all players and after that joins threads to current thread
     * @param players list of game players
     */
    private static void stop(ArrayList<Player> players) {
        for (Player player : players) {
            player.interrupt();
        }
        try {
            for (Player player : players) {
                player.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method prints players scores and prints game winners
     * @param players list of game players
     */
    private static void printResults(ArrayList<Player> players) {
        int max = players.get(0).getScore();
        for (Player player : players) {
            System.out.printf("%s. %s: score=%d\n", player.getPlayerName(),
                    player.getClass().getSimpleName(), player.getScore());
            if (player.getScore() > max) {
                max = player.getScore();
            }
        }
        int finalMax = max;
        // selecting players with max score, they will be winners
        List<Player> winners = players.stream().filter((p1) -> p1.getScore() == finalMax).toList();
        if (winners.size() == 1) {
            System.out.print("Winner is player ");
        } else {
            System.out.print("Draw! Winners are players ");
        }
        for (int i = 0; i < winners.size(); i++) {
            System.out.print(winners.get(i).getPlayerName());
            if (i != winners.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    /**
     * Static method used to run whole game
     * @param fairPlayersCount count of fair players
     * @param cheatersCount count of cheaters
     */
    public static void startGame(int fairPlayersCount, int cheatersCount) {
        ArrayList<Player> players = init(fairPlayersCount, cheatersCount);
        play(players);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stop(players);
        printResults(players);
    }
}
