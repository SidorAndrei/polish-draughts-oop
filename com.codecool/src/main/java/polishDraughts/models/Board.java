package polishDraughts.models;

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
}
