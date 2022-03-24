package polishDraughts.controllers;

import polishDraughts.models.Board;
import polishDraughts.models.Pawn;
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
        Color winner= turn(Color.CYAN);
        display.displayWinner(winner);
    }

    private Color turn(Color player){
        display.printBoard(board.getFields(),new int[] {-1,-1});
        if(board.checkForLose(player)){
            if(player == Color.RED){
                return Color.CYAN;
            }
            else{
                return Color.RED;
            }
        }
        int[][] move = playerMove(player);
        boolean hasJumped = board.movePawn(move[0], move[1]);
        if(player == Color.CYAN){
            if(Arrays.equals(board.getRedPawns(), new Pawn[0])){
                return player;
            }
            else if(board.getFields()[move[1][0]][move[1][1]].hasFutureJumps(board.getFields()) && hasJumped){
                return turn(player);
            }
            else {
                return turn(Color.RED);
            }
        }
        else{
            if(Arrays.equals(board.getCyanPawns(), new Pawn[0])){
                return player;
            }
            else if(board.getFields()[move[1][0]][move[1][1]].hasFutureJumps(board.getFields()) && hasJumped){
                return turn(player);
            }
            else {
                return turn(Color.CYAN);
            }
        }


    }

    private int[][] playerMove(Color player){
        int[] from = display.choosePawn(board.getFields(), player, board.getForcedToMovePawns(player));
        display.printBoard(board.getFields(), from);
        if(board.getFields()[from[0]][from[1]].getJumpMoves(board.getFields()).length != 0)
            System.out.println(Arrays.toString(board.getFields()[from[0]][from[1]].getJumpMoves(board.getFields())));
        else
            System.out.println(Arrays.toString(board.getFields()[from[0]][from[1]].getAvailableMoves(board.getFields())));
        int[] to =display.chooseMove(board.getFields()[from[0]][from[1]], board.getFields());
        if(Arrays.equals(to, new int[]{-1, -1})){
            return playerMove(player);
        }
        return new int[][] {from, to};
    }
}
