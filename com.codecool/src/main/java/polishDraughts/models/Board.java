package polishDraughts.models;

import polishDraughts.utils.Color;

public class Board {
    private static Board instance = null;
    private int boardSize;
    private Pawn [][] fields;


    private Board(int boardSize) {
        this.boardSize = boardSize;
        this.fields = new Pawn[boardSize][boardSize];

    }

    public static Board getInstance(int boardSize){
        if (instance == null){
            instance = new Board(boardSize);
        }

        return instance;
    }

    public void initBoard(){
        for(int row=0; row < boardSize; row++){
            for(int col=0; col < boardSize; col++){
                if( (row + col)%2 != 0 ){
                    fields[row][col] = null;
                }
                else{
                    if(row <= 2){
                        fields[row][col] = new Pawn(Color.CYAN, new int[] {row, col});
                    }
                    else if(row >= boardSize - 3){
                        fields[row][col] = new Pawn(Color.RED, new int[] {row, col});
                    }
                    else{
                        fields[row][col] = null;
                    }
                }
            }
        }
    }

    public Pawn[][] getFields() {
        return fields;
    }
}
