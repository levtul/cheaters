package cheaters.croupier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CroupierTest {

    @Test
    void startSimpleGame() {
        Croupier.startGame(1, 2);
    }
}