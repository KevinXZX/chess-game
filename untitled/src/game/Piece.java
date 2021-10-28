package game;

public interface Piece {
    boolean checkLegal(char x, int y);

    void move(char x, int y);

    char getPosX();

    int getPosY();

    char getColour();

    String getName();

    boolean getHasPromoted();
}
