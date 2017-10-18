package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the player of the scrabble game. There can be 2-4 players in the same game.
 * Each player has a rack of letter tiles and a tile inventory of special tiles.
 */
public class Player {
    /**
     * the player's id
     */
    private final String id;
    /**
     * the player's score
     */
    private int score;
    /**
     * the player's tile inventory of special tiles
     */
    private Map<SpecialTile, Integer> tileInventory;
    /**
     * the rack of the player containing letter tiles
     */
    private List<LetterTile> rack;
    /**
     * whether to skip the next turn
     */
    private boolean skipTurn;
    /**
     * how many turns should the player skip
     */
    private int numOfSkips;

    /**
     * Creates a new player with the input id.
     *
     * @param id the player's id
     */
    public Player(String id) {
        this.id = id;
        this.tileInventory = new HashMap<>();
        this.rack = new ArrayList<>();
    }


    /**
     * Gets the current score of the player.
     *
     * @return the current score of the player
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the player's tile inventory of special tiles
     *
     * @return the player's tile inventory of special tiles
     */
    public Map<SpecialTile, Integer> getTileInventory() {
        return tileInventory;
    }

    /**
     * Gets the rack of the player containing letter tiles
     *
     * @return the rack of the player containing letter tiles
     */
    public List<LetterTile> getRack() {
        return rack;
    }

    /**
     * Gets whether to skip the next turn for the player
     *
     * @return whether to skip the next turn for the player
     */
    public boolean isSkipTurn() {
        return skipTurn;
    }

    /**
     * Gets how many turns should the player skip
     *
     * @return how many turns should the player skip
     */
    public int getNumOfSkips() {
        return numOfSkips;
    }

    /**
     * Adds the given points to the player's score.
     *
     * @param points the points to be added to the player's score
     */
    public void AddScore(int points) {
        this.score += points;
    }

    /**
     * Sets the player's score to be the given score.
     *
     * @param score the given score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the player to skip the next turn(s)
     */
    public void setSkipTurn() {
        this.skipTurn = true;
        numOfSkips++;
    }

    /**
     * Sets the number of turns for the player to skip
     *
     * @param numOfSkips the number of turns for the player to skip
     */
    public void setNumOfSkips(int numOfSkips) {
        this.numOfSkips = numOfSkips;
    }


    /**
     * Draws tiles from the bag to replenish the player's rack to seven tiles.
     * If there are not enough tiles in the tile bag, the player takes all the remaining tiles.
     *
     * @param tileBag the tile bag to draw tiles
     */
    public void updateRack(TileBag tileBag) {
        List<LetterTile> bag = tileBag.getTileBag();
        int bagSize = bag.size();
        if (rack.size() < 7) {
            if (bagSize < (7 - rack.size())) {
                rack.addAll(bag);
            }
            for (int i = 0; i < (7 - rack.size()); i++) {
                rack.add(bag.remove(generateRandomIndex(bagSize)));
            }
        }
    }

    /**
     * Adds letter tiles to the player's rack.
     *
     * @param tiles the letter tiles to be added to the player's rack
     * @return true if the tiles are successfully added; false if the rack cannot hold the all tiles to be added
     */
    public boolean addLetterTiles(Map<Integer, LetterTile> tiles) {
        if (tiles.size() > (7 - rack.size())) return false;
        rack.addAll(tiles.values());
        return true;

    }

    /**
     * Removes the given letter tiles from the player's rack.
     *
     * @param tiles the letter tiles to be removed from the player's rack
     * @return true if the player has the given letter tiles and the tiles are removed successfully from the rack;
     * false if the player doesn't have all the given tiles
     */
    public boolean removeLetterTiles(Map<Integer, LetterTile> tiles) {
        if (!rack.containsAll(tiles.values())) return false;
        rack.removeAll(tiles.values());
        return true;
    }

    /**
     * Removes the given letter tiles from the player's rack.
     *
     * @param tiles the letter tiles to be removed from the player's rack
     * @return true if the player has the given letter tiles and the tiles are removed successfully from the rack;
     * false if the player doesn't have all the given tiles
     */
    public boolean removeLetterTiles(List<LetterTile> tiles) {
        if (!rack.containsAll(tiles)) return false;
        rack.removeAll(tiles);
        return true;
    }

    /**
     * Removes the given special tile from the player's tile inventory.
     *
     * @param tile the given special tile to be removed from the player's tile inventory
     * @return true if the player has the given special tile and the tile is removed successfully from the player's tile inventory;
     * false if the player doesn't have the given tile
     */
    public boolean removeSpecialTile(SpecialTile tile) {
        Integer num = tileInventory.get(tile);
        if (num == null) {
            return false;
        } else if (num == 1) {
            tileInventory.remove(tile);
        } else {
            tileInventory.put(tile, num - 1);
        }
        return true;
    }

    /**
     * Generates a random index number from 0 to num-1.
     *
     * @param num the total number of indices
     */
    private int generateRandomIndex(int num) {
        return (int) (num * Math.random());
    }

}
