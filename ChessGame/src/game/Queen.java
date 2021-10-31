package game;

public class Queen implements Piece {
    private final boolean hasPromoted = false;
    private final char colour;
    private final String name;
    private char posX;
    private int posY;

    public Queen(String name, char colour, char posX, int posY) {
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
        checkLegal(posX, posY);
        this.posX = posX;
        this.posY = posY;
    }

    public boolean checkLegal(char x, int y) {
        if (Game.offBoard(x, y)) {
            return false;
        } else if (Board.INSTANCE.getPosition(x, y) == this.colour) { //Cannot take own Pieces
            return false;
        }
        if (Math.abs((posY - y)) == Math.abs((posX - x))) {//Diagonals
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
        } else if (posX == x || posY == y) { //Cross Movement
            char currentPosX = posX;
            int currentPosY = posY;
            while (currentPosX != x || currentPosY != y) { //Loop to check if cross movement is obstructed
                if (currentPosX < x) {
                    currentPosX++;
                } else if (currentPosX > x) {
                    currentPosX--;
                } else if (currentPosY < y) {
                    currentPosY++;
                } else if (currentPosY > y) {
                    currentPosY--;
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
