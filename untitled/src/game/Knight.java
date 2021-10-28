package game;

public class Knight implements Piece {
    public final boolean hasPromoted = false;
    private final char colour;
    private final String name;
    private char posX;
    private int posY;

    public Knight(String name, char colour, char posX, int posY) {
        this.colour = colour;
        this.name = name;
        this.posX = posX;
        this.posY = posY;
    }

    public char getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public char getColour() {
        return this.colour;
    }

    public boolean getHasPromoted() {
        return this.hasPromoted;
    }

    public String getName() {
        return this.name;
    }

    public void move(char posX, int posY) {
        if (checkLegal(posX, posY)) {
            this.posX = posX;
            this.posY = posY;
        } else {
            System.out.println("Invalid");
        }
    }

    public boolean checkLegal(char x, int y) {
        if (Game.offBoard(x, y)) {
            return false;
        } else if (Board.INSTANCE.getPosition(x, y) == this.colour) { //Cannot take own Pieces
            return false;
        } else if ((Math.abs(posX - x) == 1 && Math.abs(posY - y) == 2) || (Math.abs(posX - x) == 2 && Math.abs(posY - y) == 1)) {
            return true;
        }
        return false;
    }
}