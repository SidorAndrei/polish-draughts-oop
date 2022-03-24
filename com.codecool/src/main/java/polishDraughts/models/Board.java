package polishDraughts.models;

import org.apache.commons.lang3.ArrayUtils;
import polishDraughts.utils.Color;

import java.util.Arrays;

public class Board {
    private static Board instance = null;
    private final int boardSize;
    private final Pawn [][] fields;
    private Pawn[] redPawns, cyanPawns;


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
        redPawns = new Pawn[0];
        cyanPawns = new Pawn[0];

        for(int row=0; row < boardSize; row++){
            for(int col=0; col < boardSize; col++){
                if( (row + col)%2 != 0 ){
                    fields[row][col] = null;
                }
                else{
                    if(row <= 2){
                        fields[row][col] = new Pawn(Color.RED, new int[] {row, col});
                        redPawns = addToArray(redPawns, fields[row][col]);
                    }
                    else if(row >= boardSize - 3){
                        fields[row][col] = new Pawn(Color.CYAN, new int[] {row, col});
                        cyanPawns = addToArray(cyanPawns, fields[row][col]);
                    }
                    else{
                        fields[row][col] = null;
                    }
                }
            }
        }
    }

    public void removePawn(int[] position){
        if(fields[position[0]][position[1]].getColor() == Color.RED){
            redPawns = ArrayUtils.remove(redPawns, ArrayUtils.indexOf(redPawns, fields[position[0]][position[1]]));
        }
        else {
            cyanPawns = ArrayUtils.remove(cyanPawns, ArrayUtils.indexOf(cyanPawns, fields[position[0]][position[1]]));
        }
        fields[position[0]][position[1]] = null;
    }

    public boolean movePawn(int[] from, int[] to){
        fields[to[0]][to[1]] = fields[from[0]][from[1]];
        fields[from[0]][from[1]] = null;
        fields[to[0]][to[1]].setPosition(to);
        if(fields[to[0]][to[1]].getColor() == Color.RED && to[0] == fields.length-1){
            fields[to[0]][to[1]].setCrowned(true);
        }
        else if(fields[to[0]][to[1]].getColor() == Color.CYAN && to[0] == 0){
            fields[to[0]][to[1]].setCrowned(true);
        }
        if(Math.abs(from[0] - to[0]) == 2){
            removePawn(new int[] {(from[0] + to[0])/2, (from[1]+to[1])/2});
            return true;
        }
        return false;
    }

    public boolean checkForLose(Color player){
        if(player == Color.RED){
            for(Pawn redPawn: redPawns){
                if(!Arrays.equals(redPawn.getAvailableMoves(fields), new String[0])){
                    return false;
                }
            }
        }
        else{
            for(Pawn cyanPawn: cyanPawns) {
                if (!Arrays.equals(cyanPawn.getAvailableMoves(fields), new String[0])) {
                    return false;
                }
            }
        }
        return true;
    }

    public String[] getForcedToMovePawns(Color player){
        Pawn[] pawns;
        String[] pawnsCoordinates = new String[0];
        if(player ==Color.RED){
            pawns = redPawns;
        }
        else pawns = cyanPawns;
        for(Pawn pawn: pawns){
            if(pawn.hasFutureJumps(fields)){
                pawnsCoordinates = addToArray(pawnsCoordinates, pawn.getPosition());
            }
        }
        return pawnsCoordinates;
    }

    public Pawn[][] getFields() {
        return fields;
    }

    private Pawn[] addToArray(Pawn[] array, Pawn element){
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = element;
        return  array;
    }

    private String[] addToArray(String[] array, String element){
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = element;
        return  array;
    }

    public Pawn[] getRedPawns() {
        return redPawns;
    }

    public Pawn[] getCyanPawns() {
        return cyanPawns;
    }
}
