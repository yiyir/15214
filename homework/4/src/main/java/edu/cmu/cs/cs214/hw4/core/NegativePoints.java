package edu.cmu.cs.cs214.hw4.core;

/**
 * This class represents the 'Negative-points' special tile.
 */
public class NegativePoints implements SpecialTile {
    /**
     * Activates the function of the special tile(i.e. the word that activated this tile
     * is scored negatively for the player who activated the tile).
     *
     * @param board the game board of the game
     * @param game  the game system of the game
     */
    @Override
    public void activateFunc(GameBoard board, GameSystem game) {

    }

    /**
     * Gets the price of this special tile.
     *
     * @return the price(in points) of the special tile
     */
    @Override
    public int getPrice() {
        return 20;
    }
}
