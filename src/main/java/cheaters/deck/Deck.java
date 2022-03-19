package cheaters.deck;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that is used for pickCard synchronization
 * <br> Note: you don't need to create Deck object, getCard method synchronizes
 * on whole class because it's static
 */
public class Deck {
    /**
     * @return card from the top of the deck with value from 1 to 10
     */
    public static synchronized int getCard() {
        return ThreadLocalRandom.current().nextInt(1, 11);
    }
}
