package edu.cmu.cs.cs214.hw4.core.gameelements.gameboard;

import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;
import edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles.SpecialTile;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the squares on the game board of the game Scrabble.
 */
public class Square {
    /**
     * the letter tile corresponding to the square (null means no letter tile is on the square)
     */
    private LetterTile letterTile = null;
    /**
     * the special tiles corresponding to the square (null means no special tile is on the square)
     */
    private List<SpecialTile> specialTiles = null;
    /**
     * the multiplier represented by the square
     */
    private final int multiplier;
    /**
     * whether the multiplier is for word or for letter
     */
    private final boolean isForWord;

    /**
     * Creates a new square with the given information(index, multiplier and isForWord).
     *
     * @param multiplier the multiplier of the square
     * @param isForWord  whether the multiplier is for word or for letter
     */
    public Square(int multiplier, boolean isForWord) {
        this.multiplier = multiplier;
        this.isForWord = isForWord;
    }

    /**
     * Gets the letter tile corresponding to the square
     *
     * @return the letter tile corresponding to the square
     */
    public LetterTile getLetterTile() {
        return letterTile;
    }

    /**
     * Gets the special tiles corresponding to the square
     *
     * @return the special tiles corresponding to the square
     */
    public List<SpecialTile> getSpecialTiles() {
        return specialTiles;
    }

    /**
     * Gets the multiplier represented by the square
     *
     * @return the multiplier represented by the square
     */
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * Gets whether the multiplier is for word or for letter
     *
     * @return whether the multiplier is for word or for letter
     */
    public boolean isForWord() {
        return isForWord;
    }

    /**
     * Adds the given letter tile to the square or remove the current letter tile on the square
     *
     * @param letterTile the letter tile to be added to the square, null means to remove the current letter tile on the square
     * @return true if the operation succeeds; false if it fails to add the given letter tile to the square()
     */
    public boolean setLetterTile(LetterTile letterTile) {
        if (this.letterTile != null && letterTile != null) return false;
        this.letterTile = letterTile;
        return true;
    }

    /**
     * Adds the given special tile to the square.
     *
     * @param specialTile the special tile to be added to the square
     * @return true if the operation succeeds; false if it fails to add the given special tile to the square
     */
    public boolean addSpecialTile(SpecialTile specialTile) {
        if (this.letterTile != null) return false;
        if (this.specialTiles == null) this.specialTiles = new ArrayList<>();
        this.specialTiles.add(specialTile);
        return true;
    }
}
