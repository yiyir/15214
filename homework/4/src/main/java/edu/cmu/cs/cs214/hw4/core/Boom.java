package edu.cmu.cs.cs214.hw4.core;

/**
 * This class represents the 'Boom' special tile.
 */
public class Boom implements SpecialTile {
    /**
     * Activates the function of the special tile(i.e. all tiles in a 3-tile radius on the board are removed from the board,
     * only surviving tiles are scored for this round).
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
