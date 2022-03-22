package polishDraughts.controllers;

import polishDraughts.models.Board;
import polishDraughts.utils.Color;
import polishDraughts.views.Display;

import java.util.Arrays;

public class Game {
    private final Display display;
    private Board board;
    private int boardSize;

    public Game(Display display) {
        this.display = display;
    }

    public void start(){
        board = Board.getInstance(display.getBoardSize());
        board.initBoard();
        display.printBoard(board.getFields());
        System.out.println(Arrays.toString(display.choosePawn(board.getFields(), Color.CYAN, "▼")));
    }
}
