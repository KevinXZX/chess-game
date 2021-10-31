package game;

public class Bishop implements Piece {
    public final boolean hasPromoted = false;
    private final char colour;
    private final String name;
    private char posX;
    private int posY;

    public Bishop(String name, char colour, char posX, int posY) {
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

    public String getName() {
        return this.name;
    }

    public boolean getHasPromoted() {
        return this.hasPromoted;
    }

    public void move(char posX, int posY) {
        if (checkLegal(posX, posY)) {
            this.posX = posX;
            this.posY = posY;
        } else {
            System.out.println("Invalid");
        }
    }

    public char getColour() {
        return this.colour;
    }

    public boolean checkLegal(char x, int y) {
        if (Game.offBoard(x, y)) {
            return false;
        } else if (Board.INSTANCE.getPosition(x, y) == this.colour) { //Cannot take own Pieces
            System.out.println("Own Piece!");
            return false;
        } else if (Math.abs((posY - y)) == Math.abs((posX - x))) { //Diagonals
            char currentPosX = posX;
            int currentPosY = posY;
            while (currentPosX != x && currentPosY != y) {
                if (currentPosX > x) {
                    currentPosX--;
                } else {
                    currentPosX++;
                }
                if (currentPosY > y) {
                    currentPosY--;
                } else {
                    currentPosY++;
                }
                if (currentPosX == x && currentPosY == y) {
                    return true;
                } else if (Board.INSTANCE.getPosition(currentPosX, currentPosY) != '0') {
                    return false;
                }
            }
        }
        return false;
    }
}
