package polishDraughts.views;

import polishDraughts.models.Pawn;
import polishDraughts.utils.Color;

import java.util.Arrays;
import java.util.Scanner;

public class Display {

    public int getBoardSize() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter board size(10-20): ");
        try{
            int size = in.nextInt();
            if(size >= 10 && size <=20){
                return size;
            }
            else{
                System.out.println("The size must be between 10 and 20!!!");
                return getBoardSize();
            }
        }catch (Exception e){
            System.out.println("A number is required!");
            return getBoardSize();
        }
    }


    public void printBoard(Pawn[][] fields){
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
                    if(fields[row][col].getColor() == Color.CYAN){
                        line.append("\u001b[40m\u001B[31m ");
                        line.append(fields[row][col]);
                        line.append(" \u001B[0m");
                    }
                    else{
                        line.append("\u001b[40m\u001B[36m ");
                        line.append(fields[row][col]);
                        line.append(" \u001B[0m");
                    }
                }
                else{
                    if((row + col) % 2 != 0 ){
                        line.append("\u001B[47m   \u001B[0m");
                    }
                    else{
                        line.append("\u001B[40m   \u001B[0m");
                    }
                }
            }
            System.out.println(line);
        }
    }


    public int[] choosePawn(Pawn[][] fields, Color color, String symbol){
        Scanner in = new Scanner(System.in);
        System.out.printf("Player %s choose your pawn to move: ", color);
        try {
            String coordinate = in.nextLine();
            int row = (int)coordinate.charAt(0) - 65;
            int col = Integer.parseInt(coordinate.substring(1))-1;
            if(row < 0 || row >= fields.length  ){
                System.out.printf("Row should be between A and %s\n", (char)(fields.length + 64));
                return choosePawn(fields, color, symbol);
            }
            else if(col < 0 || col >= fields.length){
                System.out.printf("Column should be between 1 and %s\n", fields.length);
                return choosePawn(fields, color, symbol);
            }
            else{
                if(fields[row][col] == null){
                    System.out.println("Given coordinate is empty!!!");
                    return choosePawn(fields, color, symbol);
                }
                else if(fields[row][col].getColor() == color){
                    System.out.println("You have chosen an enemy pawn!!!");
                    return choosePawn(fields, color, symbol);
                }
                return new int[] {row, col};
            }

        }catch (NumberFormatException e){
            System.out.println("Column's coordinate should be a number!!! ");
            return choosePawn(fields, color, symbol);
        }
    }


    public int[] chooseMove(){

        return null;
    }


    // DEBUGGING
    public void printFields(Pawn[][] fields){
        for (Pawn[] pawns: fields) System.out.println(Arrays.toString(pawns));
    }
}
