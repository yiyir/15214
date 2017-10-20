package edu.cmu.cs.cs214.hw4.core.gameelements.tilebag;


/**
 * This class represents the letter tiles used in the game Scrabble.
 */
public class LetterTile {
    /**
     * the score value of the letter tile
     */
    private final int pointValue;
    /**
     * the letter represented by the letter tile
     */
    private final char letter;

    /**
     * Creates a new letter tile with the given score value and letter.
     *
     * @param pointValue the score value of the letter tile
     * @param letter     the letter represented by the letter tile
     */
    public LetterTile(int pointValue, char letter) {
        this.pointValue = pointValue;
        this.letter = letter;
    }

    /**
     * Returns the point value of the letter tile.
     *
     * @return the score value of the letter tile
     */
    public int getPointValue() {
        return pointValue;
    }

    /**
     * Returns the letter represented by the letter tile.
     *
     * @return the letter represented by the letter tile
     */
    public char getLetter() {
        return letter;
    }
}
