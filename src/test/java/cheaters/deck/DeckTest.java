package cheaters.deck;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void getCard() {
        for (int i = 0; i < 50; i++) {
            int card = Deck.getCard();
            assertTrue(card > 0 && card < 11, "should be in range 1-10");
        }
    }
}