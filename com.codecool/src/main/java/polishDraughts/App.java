package polishDraughts;

import polishDraughts.controllers.Game;
import polishDraughts.views.Display;

public class App {
    public static void main(String[] args) {
        Display display = new Display();
        Game game = new Game(display);
        game.start();
    }
}
