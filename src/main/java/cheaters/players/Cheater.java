package cheaters.players;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Cheater is a player, who can steal FairPlayers' cards
 * (and only theirs, cheater can't steal from other cheaters)
 */
public final class Cheater extends Player {
    List<FairPlayer> players;

    public Cheater(String name) {
        super(name);
        players = new ArrayList<>();
    }

    /**
     * @param name cheater name
     * @param players fair players who can be robbed by this cheater
     */
    public Cheater(String name, List<FairPlayer> players) {
        super(name);
        this.players = players;
    }

    /**
     * Method chooses random fair player and tries to rob him
     */
    private void stealCard() {
        FairPlayer player = players.get(ThreadLocalRandom.current().nextInt(players.size()));
        updateScore(player.getRobbed(ThreadLocalRandom.current().nextInt(0, 9)));
    }

    /**
     * Method implements cheater behaviour in game
     */
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                if (ThreadLocalRandom.current().nextDouble(0, 1) < 0.4) {
                    stealCard();
                    Thread.sleep(ThreadLocalRandom.current().nextInt(180, 301));
                } else {
                    pickCard();
                    Thread.sleep(ThreadLocalRandom.current().nextInt(100, 201));
                }
            }
        } catch (InterruptedException ignored) {
        }
    }
}
