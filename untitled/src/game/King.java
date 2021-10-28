package game;

public class King implements Piece {
    public final boolean hasPromoted = false;
    private final char colour;
    private final String name;
    private char posX;
    private int posY;

    public King(String name, char colour) {
        this.name = name;
        this.colour = colour;
        posX = 'e';
        if (colour == 'b') {
            posY = 8;
        } else {
            posY = 1;
        }
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

    public String getName() {
        return this.name;
    }

    public boolean getHasPromoted() {
        return this.hasPromoted;
    }

    public void move(char posX, int posY) {
        checkLegal(posX, posY);
        this.posX = posX;
        this.posY = posY;
    }

    public boolean checkLegal(char x, int y) {
        if (Game.offBoard(x, y)) {
            return false;
        } else if (y < posY - 1 || y > posY + 1 || x < posX - 1 || x > posX + 1) {
            return false;
        } else if (this.colour == 'b') { //King cannot put himself into check, change this to capture not move
            for (int i = 0; i < Board.position[0].length; i++) {
                if (Board.position[0][i].checkLegal(x, y)) {
                    return false;
                } else if (Board.INSTANCE.getPosition(x, y) == this.colour) { //Cannot take own Pieces
                    return false;
                }
            }
        } else if (this.colour == 'w') {
            for (int i = 0; i < Board.position[0].length; i++) {
                if (Board.position[1][i].checkLegal(x, y)) {
                    return false;
                } else if (Board.INSTANCE.getPosition(x, y) == this.colour) { //Cannot take own Pieces
                    return false;
                }
            }
        }
        return true;
    }
}
