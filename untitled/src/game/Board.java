package game;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public enum Board {
    INSTANCE;
    static Piece[][] position = new Piece[2][16];
    int promoteCounterBlack = 3;
    int promoteCounterWhite = 3;
    private Map<Point, Piece> boardMap = new HashMap<>();

    public void start() {
        position[0][0] = new Rook("R1", 'w', 'a', 1); //White Pieces
        position[0][1] = new Knight("N1", 'w', 'b', 1);
        position[0][2] = new Bishop("B1", 'w', 'c', 1);
        position[0][3] = new Queen("Q1", 'w', 'd', 1);
        position[0][4] = new King("K1", 'w');
        position[0][5] = new Bishop("B2", 'w', 'f', 1);
        position[0][6] = new Knight("N2", 'w', 'g', 1);
        position[0][7] = new Rook("R2", 'w', 'h', 1);
        position[0][8] = new Pawn("P1", 'w', 'a');
        position[0][9] = new Pawn("P2", 'w', 'b');
        position[0][10] = new Pawn("P3", 'w', 'c');
        position[0][11] = new Pawn("P4", 'w', 'd');
        position[0][12] = new Pawn("P5", 'w', 'e');
        position[0][13] = new Pawn("P6", 'w', 'f');
        position[0][14] = new Pawn("P7", 'w', 'g');
        position[0][15] = new Pawn("P8", 'w', 'h');
        position[1][0] = new Rook("R1", 'b', 'a', 8); //Black Pieces
        position[1][1] = new Knight("N1", 'b', 'b', 8);
        position[1][2] = new Bishop("B1", 'b', 'c', 8);
        position[1][3] = new Queen("Q1", 'b', 'd', 8);
        position[1][4] = new King("K1", 'b');
        position[1][5] = new Bishop("B2", 'b', 'f', 8);
        position[1][6] = new Knight("N2", 'b', 'g', 8);
        position[1][7] = new Rook("R2", 'b', 'h', 8);
        position[1][8] = new Pawn("P1", 'b', 'a');
        position[1][9] = new Pawn("P2", 'b', 'b');
        position[1][10] = new Pawn("P3", 'b', 'c');
        position[1][11] = new Pawn("P4", 'b', 'd');
        position[1][12] = new Pawn("P5", 'b', 'e');
        position[1][13] = new Pawn("P6", 'b', 'f');
        position[1][14] = new Pawn("P7", 'b', 'g');
        position[1][15] = new Pawn("P8", 'b', 'h');
        for (int i = 97; i < 105; i++) {
            for (int h = 1; h < 9; h++) {
                Point temp = new Point(i, h);
                boardMap.put(temp, null);
            }
        }
        for (int i = 0; i < position.length; i++) {
            for (int h = 0; h < position[0].length; h++) {
                Point temp = new Point(position[i][h].getPosX(), position[i][h].getPosY());
                boardMap.put(temp, position[i][h]);
            }
        }
    }

    public boolean movePiece(String name, char colour, char posX, int posY) {
        for (int i = 0; i < position.length; i++) {
            for (int h = 0; h < position[0].length; h++) {
                if (position[i][h].getName().equalsIgnoreCase(name) && position[i][h].getColour() == colour) {
                    if (position[i][h].checkLegal(posX, posY)) {
                        Point old = new Point(position[i][h].getPosX(), position[i][h].getPosY());
                        Point newPoint = new Point(posX, posY);
                        position[i][h].move(posX, posY);
                        if (position[i][h].getHasPromoted()) {
                            Piece temp = promote(position[i][h].getPosX(), position[i][h].getPosY(),
                                    position[i][h].getColour());
                            position[i][h] = temp;
                        }
                        boardMap.put(newPoint, position[i][h]);
                        boardMap.put(old, null);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Piece promote(char x, int y, char colour) { //TODO: Make counter a variable within piece classes
        System.out.println("You must now promote your pawn, options {Q,N,B,R}");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String name;
        if (colour == 'b') {
            name = input + promoteCounterBlack;
        } else {
            name = input + promoteCounterWhite;
        }
        boolean valid = false;
        Piece newPiece = null;
        switch (input) {
            case "Q":
                newPiece = new Queen(name, colour, x, y);
                valid = true;
                break;
            case "N":
                newPiece = new Knight(name, colour, x, y);
                valid = true;
                break;
            case "B":
                newPiece = new Bishop(name, colour, x, y);
                valid = true;
                break;
            case "R":
                newPiece = new Rook(name, colour, x, y);
                valid = true;
                break;
            default:
                System.out.println("Invalid");
                promote(x, y, colour);
        }
        if (valid) {
            if (colour == 'b') {
                promoteCounterBlack++;
            } else {
                promoteCounterWhite++;
            }
        }
        return newPiece;
    }

    public char getPosition(char x, int y) {
        Point temp = new Point(x, y);
        if (boardMap.get(temp) == null) {
            return '0';
        } else if (boardMap.get(temp).getColour() == 'w') {
            return 'w';
        } else {
            return 'b';
        }
    }

    public void printBoard() {
        for (int h = 8; h > 0; h--) {
            for (int i = 97; i < 105; i++) {
                Point temp = new Point(i, h);
                if (boardMap.get(temp) == null) {
                    System.out.print(" 0  ");
                } else if (boardMap.containsKey(temp)) {
                    System.out.print(boardMap.get(temp).getColour() + boardMap.get(temp).getName() + " ");
                }
            }
            System.out.println();
        }
    }
}
