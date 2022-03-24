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
        int[] position = display.choosePawn(board.getFields(), Color.CYAN, "▼");
        System.out.println(Arrays.toString(board.getFields()[position[0]][position[1]].getAvailableMoves(board.getFields())));
        System.out.println(board.getFields()[position[0]][position[1]].getColor());
        System.out.println(board.getFields()[7][1].getColor());
        System.out.println(board.getFields()[2][2].getColor());
//        System.out.println(Arrays.toString(board.getFields()[7][1].getPosition()));
//        System.out.println(Arrays.deepToString(board.getFields()));
//        System.out.println(Arrays.toString(board.getFields()[7][1].getAvailableMoves(board.getFields())));
    }
}
