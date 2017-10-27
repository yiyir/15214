package edu.cmu.cs.cs214.hw4.core;


import edu.cmu.cs.cs214.hw4.core.gameelements.Player;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.GameBoard;
import edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles.SpecialTile;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;

import java.util.Deque;
import java.util.List;
import java.util.Map;

/**
 * The Scrabble interface is used by the GUI to report GUI related events to
 * the core implementation.
 */
public interface Scrabble {
    /**
     * Register a game change listener to be notified of game change events.
     *
     * @param listener the listener to be registered
     */
    void addGameChangeListener(GameChangeListener listener);

    /**
     * Remove a game change listener from the list of listeners. The game change listener will no longer be notified of game change events.
     *
     * @param listener the listener to be removed
     */
    void removeGameChangeListener(GameChangeListener listener);

    /**
     * Gets the current player.
     *
     * @return the current player
     */
    Player getCurrentPlayer();

    /**
     * Gets the game board.
     *
     * @return the game board
     */
    GameBoard getGameBoard();

    /**
     * Adds a new player to the game.
     *
     * @param player the new player
     * @return whether the player is added successfully
     */
    boolean addPlayer(Player player);

    /**
     * Gets all the players of the game.
     *
     * @return all the players of the game
     */
    Deque<Player> getPlayers();

    /**
     * Starts a new turn according to the game order.
     *
     * @return whether the operation succeeds or not
     */
    boolean startNewTurn();

    /**
     * Plays the special tile on the specified square of the game board.
     *
     * @param i    the index of the square on the game board on which the special tile is to be placed
     * @param tile the special tile to be played
     * @return true if the operation succeeds, false if the player doesn't have the special tile
     */
    boolean playSpecialTile(Integer i, SpecialTile tile);

    /**
     * Buys the special tiles from the game and adds them to the current player's tile inventory.
     *
     * @param specialTiles the special tiles to be bought
     * @return true if the operation is successful, false if the player doesn't have enough points to buy the tiles
     */
    boolean buySpecialTiles(Map<SpecialTile, Integer> specialTiles);

    /**
     * Plays the letter tiles on the specified squares.
     *
     * @param letterTiles the letter tiles to be played
     * @return whether the operation succeeds or not
     */
    boolean playLetterTiles(Map<Integer, LetterTile> letterTiles);

    /**
     * Challenges the current move. This happens before calculating/updating the score for the current play.
     *
     * @param challenger the player who issues the challenge
     */
    void challenge(Player challenger);

    /**
     * Exchanges the selected letter tiles on the player's rack.
     *
     * @param tiles the selected letter tiles
     * @return true if the operation succeeds, false if the selected tiles are not on the player's rack
     */
    boolean exchangeTiles(List<LetterTile> tiles);

    /**
     * Passes or ends the turn by calculating and updating the current player's score after activating the special tiles.
     */
    void endTurn();

}
