package cheaters.players;

import cheaters.deck.Deck;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Base Player class who acts like simple fair player who can not be robbed by cheater
 */
public class Player extends Thread {
    private int score;
    private final String name;

    public Player(String name) {
        this.name = name;
        score = 0;
    }

    /**
     * @return players name
     */
    public String getPlayerName() {
        return name;
    }

    /**
     * Method that picks card from deck and updates player balance
     * <br>Note: getCard is static synchronized method, so players will getCards one by one
     */
    protected synchronized void pickCard() {
        updateScore(Deck.getCard());
    }

    /**
     * Player tries to pick card until the thread is stopped
     * <br> Synchronization is guaranteed because pickCard synchronizes on Deck
     */
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                pickCard();
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 201));
            }
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Synchronized method that returns player's score
     * @return player's current score
     */
    public synchronized int getScore() {
        return score;
    }

    /**
     * Synchronized method used to update player score while picking card or being robbed
     * @param delta value added to current player's score
     */
    protected synchronized void updateScore(int delta) {
        score += delta;
    }
}
