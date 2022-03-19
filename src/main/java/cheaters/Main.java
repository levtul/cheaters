package cheaters;

import cheaters.croupier.Croupier;
import cheaters.manager.GameManager;

public class Main {
    public static void main(String[] args) {
        GameManager manager = new GameManager();
        manager.play();
    }
}
