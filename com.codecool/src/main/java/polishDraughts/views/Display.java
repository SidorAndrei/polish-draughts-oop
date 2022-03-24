package polishDraughts.views;

import org.apache.commons.lang3.ArrayUtils;
import polishDraughts.models.Pawn;
import polishDraughts.utils.Color;

import java.util.Arrays;
import java.util.Scanner;

public class Display {

    public int getBoardSize() {
        Scanner in = new Scanner(System.in);
        System.out.print("\u001b[31mPlease enter board size(10-20): \u001b[0m");
        try{
            int size = in.nextInt();
            if(size >= 10 && size <=20){
                return size;
            }
            else{
                System.out.println("\u001b[35mThe size must be between 10 and 20!!!\u001b[0m");
                return getBoardSize();
            }
        }catch (Exception e){
            System.out.println("\u001b[35mA number is required!\u001b[0m");
            return getBoardSize();
        }
    }


    public void printBoard(Pawn[][] fields, int[] chosenPawn){
        String[] availableMoves = new String[0];
        if(!Arrays.equals(chosenPawn, new int[] {-1,-1})){
            if (fields[chosenPawn[0]][chosenPawn[1]].hasFutureJumps(fields)) {
                availableMoves = fields[chosenPawn[0]][chosenPawn[1]].getJumpMoves(fields);
            } else {
                availableMoves = fields[chosenPawn[0]][chosenPawn[1]].getAvailableMoves(fields);
            }
        }
        StringBuilder firstLine = new StringBuilder("  ");
        for(int k = 1; k<=fields.length; k++){
            if(k<10) firstLine.append(String.format(" %s ", k));
            else firstLine.append(String.format(" %s", k));
        }
        System.out.println(firstLine);
        for (int row = 0; row < fields.length; row++){
            StringBuilder line = new StringBuilder(String.format("%s ", (char)(row + 65)));
            for (int col = 0; col < fields[row].length; col++){
                if (fields[row][col] != null){
                    if(fields[row][col].getColor() == Color.RED){
                        if(Arrays.equals(chosenPawn, new int[]{row, col})) line.append("\u001b[42m\u001B[31m ");
                        else line.append("\u001b[40m\u001B[31m ");
                    }
                    else {
                        if(Arrays.equals(chosenPawn, new int[]{row, col})) line.append("\u001b[42m\u001B[36m ");
                        else line.append("\u001b[40m\u001B[36m ");
                    }
                    line.append(fields[row][col]);
                    line.append(" \u001B[0m");
                }
                else{
                    if((row + col) % 2 != 0 ){
                        line.append("\u001B[47m   \u001B[0m");
                    }
                    else{
                        if(ArrayUtils.contains(availableMoves,String.format("%s%s", (char)(row + 65),col+1))){
                            line.append("\u001B[43m   \u001B[0m");
                        }
                        else line.append("\u001B[40m   \u001B[0m");
                    }
                }
            }
            System.out.println(line);
        }
    }


    public int[] choosePawn(Pawn[][] fields, Color color, String[] forcedToMove){
        Scanner in = new Scanner(System.in);
        StringBuilder line;
        if(color== Color.RED)
            line = new StringBuilder("\u001B[31m");
        else
            line = new StringBuilder("\u001B[36m");
        line.append(String.format("Player %s choose your pawn to move: ", color));
        line.append("\u001B[0m");
        System.out.print(line);
        try {
            String coordinate = in.nextLine().toUpperCase();
            int row = (int)coordinate.charAt(0) - 65;
            int col = Integer.parseInt(coordinate.substring(1))-1;
            if(row < 0 || row >= fields.length  ){
                System.out.printf("\u001b[35mRow should be between A and %s\n\u001b[0m", (char)(fields.length + 64));
                return choosePawn(fields, color, forcedToMove);
            }
            else if(col < 0 || col >= fields.length){
                System.out.printf("\u001b[35mColumn should be between 1 and %s\n\u001b[0m", fields.length);
                return choosePawn(fields, color, forcedToMove);
            }
            else{
                if(fields[row][col] == null){
                    System.out.println("\u001b[35mGiven coordinate is empty!!!\u001b[0m");
                    return choosePawn(fields, color, forcedToMove);
                }
                else if(fields[row][col].getColor() != color){
                    System.out.println("\u001b[35mYou have chosen an enemy pawn!!!\u001b[0m");
                    return choosePawn(fields, color, forcedToMove);
                }
                if(forcedToMove.length != 0){
                    if(ArrayUtils.contains(forcedToMove, String.format("%s%s", (char)(row + 65), col + 1 ))){
                        return new int[] {row, col};
                    }
                    else{
                        System.out.printf("\u001b[35mYou are forced to move the pawn according to the given coordinate: %s\u001b[0m\n", forcedToMove[0]);
                        return choosePawn(fields, color, forcedToMove);
                    }
                }
                return new int[] {row, col};
            }

        }catch (NumberFormatException e){
            System.out.println("\u001b[35mColumn's coordinate should be a number!!! \u001b[0m");
            return choosePawn(fields, color, forcedToMove);
        }catch (Exception e){
            System.out.println("\u001b[35mYou have to provide a coordinate!! \u001b[0m");
            return choosePawn(fields, color, forcedToMove);
        }

    }


    public int[] chooseMove(Pawn player, Pawn[][] fields){
        Scanner in = new Scanner(System.in);
        StringBuilder line;
        if(player.getColor() == Color.RED)
            line = new StringBuilder("\u001B[31m");
        else
            line = new StringBuilder("\u001B[36m");
        line.append(String.format("Player %s choose your move: ", player.getColor()));
        line.append("\u001B[0m");
        System.out.print(line);
        String move;
        try{
            move = in.nextLine().toUpperCase();
        }catch (Exception e){
            System.out.println("\u001b[35mYou have to provide a coordinate!! \u001b[0m");
            return chooseMove(player, fields);
        }
        if(player.getJumpMoves(fields).length != 0) {
            if (ArrayUtils.contains(player.getJumpMoves(fields), move)) {
                int row = (int) move.charAt(0) - 65;
                int col = Integer.parseInt(move.substring(1)) - 1;
                return new int[]{row, col};
            } else{
                return chooseMove(player, fields);
            }
        }
        if (ArrayUtils.contains(player.getAvailableMoves(fields),move)){
            int row = (int)move.charAt(0) - 65;
            int col = Integer.parseInt(move.substring(1))-1;
            return new int[] {row, col};
        }
        else{
            if(Arrays.equals(player.getAvailableMoves(fields), new String[0])){
                System.out.println("\u001b[35mThe chosen pawn can't be moved!\u001b[0m");
                return new int[]{-1, -1};
            }
            System.out.println("\u001b[35mThat is not a valid move for the chosen pawn!!!\u001b[0m");
            return chooseMove(player, fields);
        }
    }

    public void printHints(String[] hints){
        StringBuilder hintLine = new StringBuilder("\u001b[34m You can move to the next coordinates: ");
        for(String hint: hints){
            hintLine.append(String.format("%s ", hint));
        }
        hintLine.append("\u001b[0m");
        System.out.println(hintLine);
    }

    public void displayWinner(Color player){
        System.out.printf("\u001b[35mPlayer %s has won!\u001b[0m", player);
    }
}
