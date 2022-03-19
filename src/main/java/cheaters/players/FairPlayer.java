package cheaters.players;

import cheaters.deck.Deck;

/**
 * Fair player acts like simple player and can be robbed by cheater
 */
public final class FairPlayer extends Player {
    public FairPlayer(String name) {
        super(name);
    }

    /**
     * Synchronized method that allows cheater steal some points from current player
     * @param value count of points that cheater tries to steal
     * @return stolen count of points
     */
    public synchronized int getRobbed(int value) {
        value = Math.min(value, getScore());
        updateScore(-value);
        return value;
    }
}
