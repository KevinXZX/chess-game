package game;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board.INSTANCE.start();
        Board.INSTANCE.printBoard();
        int turn = 1;
        while (true) {
            if (turn % 2 != 0) { //White Turn
                turn('w');
            } else { //Black Turn
                turn('b');
            }
            turn++;
        }
    }

    public static void turn(char colour) {
        System.out.println("Please enter board to see the board, resign to resign " +
                "or the name of the piece you wish to move");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("board")) {
            Board.INSTANCE.printBoard();
            turn(colour);
        } else if (input.equalsIgnoreCase("Resign")) {
            if (colour == 'w') {
                win('b');
            } else if (colour == 'b') {
                win('w');
            }
        } else {
            System.out.println("Please enter the co-ordinates you wish to move to");
            String move = sc.nextLine();
            System.out.println(input + " " + colour + " " + move.charAt(0) + " " + ((int) move.charAt(1) - (int) '0'));
            if (Board.INSTANCE.movePiece(input, colour, move.charAt(0), (int) move.charAt(1) - (int) '0')) {
                System.out.println("Turn complete!");
            } else {
                System.out.println("Error");
                turn(colour);
            }
        }
    }

    public static boolean offBoard(char x, int y) {
        return !((96 < x && x < 105) && 0 < y && y < 9);
    }

    public static void win(char colour) {
        if (colour == 'b') {
            System.out.println("The game is over!\nBlack has won!");
        } else if (colour == 'w') {
            System.out.println("The game is over!\nWhite has won!");
        } else {
            System.out.println("The game is a draw!");
        }
        System.exit(0);
    }
}

