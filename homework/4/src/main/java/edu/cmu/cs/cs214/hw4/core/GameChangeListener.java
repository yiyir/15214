package edu.cmu.cs.cs214.hw4.core;

import edu.cmu.cs.cs214.hw4.core.gameelements.Player;
import edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles.SpecialTile;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GameChangeListener {
    /**
     * Called when the current player is changed.
     *
     * @param player the new current player
     */
    void currentPlayerChanged(Player player);

    /**
     * Called when the current player's special tile inventory is changed.
     *
     * @param tileInventory the current player's special tile inventory
     */
    void specialTilesChanged(Map<SpecialTile, Integer> tileInventory);

    /**
     * Called when the tiles on the current player's rack are changed.
     *
     * @param rack the current player's rack
     */
    void rackChanged(List<LetterTile> rack);

    /**
     * Called when the score of the current player is changed.
     *
     * @param score the current player's score
     */
    void scoreChanged(int score);

    /**
     * Called when any square on the game board is changed.
     *
     * @param index the index of the square
     */
    void squareChanged(int index);

    /**
     * Called when squares on the game board are changed.
     *
     * @param indices the indices of the squares
     */
    void squaresChanged(Set<Integer> indices);

    /**
     * Called when the game has ended.
     *
     * @param winner the winner of the game
     */
    void gameEnded(Player winner);


    /**
     * Called when the dictionary of the game cannot be found.
     */
    void dictionaryNotFound();

}
