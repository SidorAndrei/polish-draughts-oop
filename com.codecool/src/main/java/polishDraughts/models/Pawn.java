package polishDraughts.models;

import polishDraughts.utils.Color;

import java.util.Arrays;

public class Pawn {
    private final Color color;
    private String symbol;
    private int[] position;
    private boolean isCrowned = false;

    public Pawn(Color color, int[] position) {
        this.color = color;
        this.position = position;
        if(this.color == Color.CYAN){
            this.symbol = "▲";
        }
        else{
            this.symbol = "▼";
        }
    }

    public boolean hasFutureJumps(Pawn[][] fields){
        int row = position[0];
        int col = position[1];
        if(row - 2 >= 0 && col - 2 >= 0)
            if(fields[row - 1][col - 1] != null)
                if(!fields[row - 1][col - 1].toString().equals(symbol)){
                    if(fields[row - 2][col - 2] == null){
                        return true;
                    }
                }
        if(row - 2 >= 0 && col + 2 < fields.length)
            if(fields[row - 1][col + 1] != null)
                if(!fields[row - 1][col + 1].toString().equals(symbol)){
                    if(fields[row - 2][col + 2] == null){
                        return true;
                    }
                }

        if(row + 2 < fields.length  && col - 2 >= 0)
            if(fields[row + 1][col - 1] != null)
                if(!fields[row + 1][col - 1].toString().equals(symbol)){
                    if(fields[row + 2][col - 2] == null){
                        return true;
                    }
                }
        if(row + 2 < fields.length && col + 2 < fields.length)
            if(fields[row + 1][col + 1] != null)
                if(!fields[row + 1][col + 1].toString().equals(symbol)){
                    return fields[row + 2][col + 2] == null;
                }
        return false;
    }

    public String[] getAvailableMoves(Pawn[][] fields){
        String[] availableMoves = new String[0];
        int row = position[0];
        int col = position[1];

        if(row - 1 >= 0 && col - 1 >= 0){
            if (this.color == Color.CYAN || this.isCrowned) {
                if(fields[row - 1][col - 1] == null ){
                    availableMoves = addToArray(availableMoves, transformInCoordinate(row - 1, col - 1));
                }
            }
            if(fields[row - 1][col - 1] != null && row - 2 >= 0 && col - 2 >= 0)
                if(!fields[row - 1][col - 1].toString().equals(symbol)){
                    if(fields[row - 2][col - 2] == null ){
                        availableMoves = addToArray(availableMoves, transformInCoordinate(row - 2, col - 2));
                    }
            }
        }
        if(row - 1 >= 0 && col + 1 < fields.length){
            if(this.color == Color.CYAN || this.isCrowned){
                if (fields[row - 1][col + 1] == null) {
                    availableMoves = addToArray(availableMoves, transformInCoordinate(row - 1, col + 1));
                }
            }
            if(fields[row - 1][col + 1] != null && row - 2 >= 0 && col + 2 < fields.length)
                if(!fields[row - 1][col + 1].toString().equals(symbol)){
                    if(fields[row - 2][col + 2] == null ){
                        availableMoves = addToArray(availableMoves, transformInCoordinate(row - 2, col + 2));
                    }
                }
        }
        if(row + 1 < fields.length  && col - 1 >= 0){
            if(this.color == Color.RED || this.isCrowned){
                if (fields[row + 1][col - 1] == null) {
                    availableMoves = addToArray(availableMoves, transformInCoordinate(row + 1, col - 1));
                }
            }
             if(fields[row + 1][col - 1] != null && row + 2 < fields.length  && col - 2 >= 0)
                if(!fields[row + 1][col - 1].toString().equals(symbol)){
                    if(fields[row + 2][col - 2] == null){
                        availableMoves = addToArray(availableMoves, transformInCoordinate(row + 2, col - 2));
                    }
            }
        }
        if(row + 1 < fields.length && col + 1 < fields.length){
            if(this.color == Color.RED || this.isCrowned){
                if (fields[row + 1][col + 1] == null) {
                    availableMoves = addToArray(availableMoves, transformInCoordinate(row + 1, col + 1));
                }
            }
            if(fields[row + 1][col + 1] != null && row + 2 < fields.length && col + 2 < fields.length)
                if(!fields[row + 1][col + 1].toString().equals(symbol)){
                    if(fields[row + 2][col + 2] == null ){
                        availableMoves = addToArray(availableMoves, transformInCoordinate(row + 2, col + 2));
                    }
            }
        }
        return availableMoves;
    }

    public String[] getJumpMoves(Pawn[][] fields){
        String[] jumpMoves = new String[0];
        int row = position[0];
        int col = position[1];
        if(row - 2 >= 0 && col - 2 >= 0)
            if(fields[row - 1][col - 1] != null)
                if(!fields[row - 1][col - 1].toString().equals(symbol)){
                    if(fields[row - 2][col - 2] == null){
                       jumpMoves = addToArray(jumpMoves, transformInCoordinate(row - 2, col - 2));
                    }
                }
        if(row - 2 >= 0 && col + 2 < fields.length)
            if(fields[row - 1][col + 1] != null)
                if(!fields[row - 1][col + 1].toString().equals(symbol)){
                    if(fields[row - 2][col + 2] == null){
                        jumpMoves = addToArray(jumpMoves, transformInCoordinate(row - 2, col + 2));
                    }
                }

        if(row + 2 < fields.length  && col - 2 >= 0)
            if(fields[row + 1][col - 1] != null)
                if(!fields[row + 1][col - 1].toString().equals(symbol)){
                    if(fields[row + 2][col - 2] == null){
                        jumpMoves = addToArray(jumpMoves, transformInCoordinate(row + 2, col - 2));
                    }
                }
        if(row + 2 < fields.length && col + 2 < fields.length)
            if(fields[row + 1][col + 1] != null)
                if(!fields[row + 1][col + 1].toString().equals(symbol)){
                    if(fields[row + 2][col + 2] == null){
                        jumpMoves = addToArray(jumpMoves, transformInCoordinate(row + 2, col + 2));
                    }
                }
        return jumpMoves;
    }

    public void setPosition(int[] position) {
        this.position = position;

    }

    @Override
    public String toString() {
        return symbol;
    }

    public Color getColor() {
        return this.color;
    }

    public void setCrowned(boolean crowned) {
        this.isCrowned = crowned;
        this.symbol = "♚";
    }
    //PRIVATE METHODS

    private String[] addToArray(String[] array, String element){
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = element;
        return  array;
    }


    private String transformInCoordinate(int row, int col){
        return String.format("%s%s", (char)(row + 65), col + 1);
    }


    public String getPosition() {
        return transformInCoordinate(position[0], position[1]);
    }
}
