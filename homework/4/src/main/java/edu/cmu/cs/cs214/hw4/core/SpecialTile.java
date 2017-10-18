package edu.cmu.cs.cs214.hw4.core;

/**
 * This interface represents the special tile.
 */
public interface SpecialTile {
    /**
     * Activates the function of the special tile.
     *
     * @param board the game board of the game
     * @param game  the game system of the game
     */
    void activateFunc(GameBoard board, GameSystem game);

    /**
     * Gets the price of the special tile.
     * @return the price of the special tile
     */
    int getPrice();
}
