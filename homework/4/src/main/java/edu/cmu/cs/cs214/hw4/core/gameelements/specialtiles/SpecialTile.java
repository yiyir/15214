package edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles;

import edu.cmu.cs.cs214.hw4.core.game.GameSystem;

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
    void activateFunc(Integer index, GameSystem game);

    /**
     * Gets the price of the special tile.
     *
     * @return the price of the special tile
     */
    int getPrice();
}
