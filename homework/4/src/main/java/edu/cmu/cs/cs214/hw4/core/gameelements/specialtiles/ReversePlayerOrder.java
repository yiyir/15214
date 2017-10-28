package edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles;

import edu.cmu.cs.cs214.hw4.core.ScrabbleImpl;

/**
 * This class represents the 'Reverse-player-order' special tile.
 */
public class ReversePlayerOrder implements SpecialTile {
    /**
     * Activates the function of the special tile(i.e. the turn ends as usual, but after this tile is activated
     * play continues in the reverse of the previous order).
     *
     * @param index the index of the square on which the special tile is placed
     * @param game  the game system of the game
     */
    @Override
    public void activateFunc(Integer index, ScrabbleImpl game) {
        game.setReverseOrder();
    }

    /**
     * Gets the price of this special tile.
     *
     * @return the price(in points) of the special tile
     */
    @Override
    public int getPrice() {
        return 10;
    }

    @Override
    public String toString() {
        return "ReversePlayerOrder";
    }
}
