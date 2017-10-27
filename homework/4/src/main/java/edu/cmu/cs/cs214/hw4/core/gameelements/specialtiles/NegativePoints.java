package edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles;

import edu.cmu.cs.cs214.hw4.core.ScrabbleImpl;

import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.GameBoard;

/**
 * This class represents the 'Negative-points' special tile.
 */
public class NegativePoints implements SpecialTile {
    /**
     * Activates the function of the special tile(i.e. the word that activated this tile
     * is scored negatively for the player who activated the tile).
     *
     * @param index the index of the square on which the special tile is placed
     * @param game  the game system of the game
     */
    @Override
    public void activateFunc(Integer index, ScrabbleImpl game) {
        GameBoard board = game.getGameBoard();
        int score = board.getScoreForNegativeWords(game.getCurrentMove(), index);
        game.getCurrentPlayer().addScore(score * (-2));


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
