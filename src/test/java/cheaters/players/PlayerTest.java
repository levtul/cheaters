package cheaters.players;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getPlayerName() {
        Player player = new Player("abcd");
        assertEquals("abcd", player.getPlayerName(), "name should be the same");
    }

    @Test
    void pickCard() {
        Player player = new Player("");
        assertEquals(0, player.getScore(), "initial score should be 0");
        player.pickCard();
        assertTrue(player.getScore() > 0, "should pick non-null card and increase balance");
    }

    @Test
    void updateScore() {
        Player player = new Player("");
        assertEquals(0, player.getScore(), "initial score should be 0");
        player.updateScore(10);
        assertEquals(10, player.getScore(), "should add 10");
        player.updateScore(100);
        assertEquals(110, player.getScore(), "should add 100");
        player.updateScore(-20);
        assertEquals(90, player.getScore(), "should subtract 10");
    }
}