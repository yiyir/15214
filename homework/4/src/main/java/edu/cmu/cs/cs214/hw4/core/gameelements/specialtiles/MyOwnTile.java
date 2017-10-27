package edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles;

import edu.cmu.cs.cs214.hw4.core.ScrabbleImpl;

public class MyOwnTile implements SpecialTile {
    /**
     * Activates the function of the special tile(i.e. the player who activated this tile loses his next turn).
     *
     * @param index the index of the square on which the special tile is placed
     * @param game  the game system
     */
    @Override
    public void activateFunc(Integer index, ScrabbleImpl game) {
        game.getCurrentPlayer().setSkipTurn();
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
