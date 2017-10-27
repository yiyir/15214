package edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles;

import edu.cmu.cs.cs214.hw4.core.ScrabbleImpl;

/**
 * This interface represents the special tile.
 */
public interface SpecialTile {
    /**
     * Activates the function of the special tile.
     *
     * @param index the index of the square on which the special tile is placed
     * @param game  the game system
     */
    void activateFunc(Integer index, ScrabbleImpl game);

    /**
     * Gets the price of the special tile.
     *
     * @return the price of the special tile
     */
    int getPrice();

    /**
     * Creats a string representation of the special tile.
     *
     * @returns tring representation of the special tile
     */
    String toString();
}
