package polishDraughts.controllers;

import polishDraughts.models.Board;
import polishDraughts.views.Display;

public class Game {
    private final Display display;
    private Board board;
    private int boardSize;

    public Game(Display display) {
        this.display = display;
    }

    public void start(){
        board = Board.getInstance(display.getBoardSize());
    }
}
