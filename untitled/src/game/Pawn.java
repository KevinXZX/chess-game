package game;

public class Pawn implements Piece {
    private final char colour;
    private final String name;
    private char posX;
    private int posY;
    private boolean hasNotMoved = true;
    private boolean hasPromoted = false;

    public Pawn(String name, char a, char posX) {
        this.colour = a;
        this.name = name;
        this.posX = posX;
        if (a == 'b') {
            this.posY = 7;
        } else {
            this.posY = 2;
        }
    }

    public char getPosX() {
        return this.posX;
    }

    public boolean getHasPromoted() {
        return this.hasPromoted;
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

    public void move(char posX, int posY) {
        checkLegal(posX, posY);
        if (hasNotMoved) {
            hasNotMoved = false;
        }
        this.posX = posX;
        this.posY = posY;
        if (this.colour == 'b' && this.posY == 1) {
            hasPromoted = true;
        } else if (this.colour == 'w' && this.posY == 8) {
            hasPromoted = true;
        }
    }

    public boolean checkLegal(char x, int y) { //TODO: Add en passant
        if (Game.offBoard(x, y)) {
            return false;
        } else if (Board.INSTANCE.getPosition(x, y) != '0') {
            if (colour == 'b') {
                if (y == posY - 1 && Math.abs(posX - x) == 1) {
                    return Board.INSTANCE.getPosition(x, y) != this.colour;
                }
            } else if (colour == 'w') {
                if (y == posY + 1 && Math.abs(posX - x) == 1) {
                    return Board.INSTANCE.getPosition(x, y) != this.colour;
                }
            }
        } else if (colour == 'b') {
            if (y == posY - 1 && x == posX) {
                return true;
            } else if (y == posY - 2 && x == posX && hasNotMoved) {
                return Board.INSTANCE.getPosition(x, posY - 1) == '0';
            }
        } else if (colour == 'w') {
            if (y == posY + 1 && x == posX) {
                return true;
            } else if (y == posY + 2 && x == posX && hasNotMoved) {
                return Board.INSTANCE.getPosition(x, posY + 1) == '0';
            }
        }
        return false;
    }
}
