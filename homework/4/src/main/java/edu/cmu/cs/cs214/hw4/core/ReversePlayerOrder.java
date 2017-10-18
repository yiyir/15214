package edu.cmu.cs.cs214.hw4.core;

/**
 * This class represents the 'Reverse-player-order' special tile.
 */
public class ReversePlayerOrder implements SpecialTile {
    /**
     * Activates the function of the special tile(i.e. the turn ends as usual, but after this tile is activated
     * play continues in the reverse of the previous order).
     *
     * @param board the game board of the game
     * @param game  the game system of the game
     */
    @Override
    public void activateFunc(GameBoard board, GameSystem game) {
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
}
