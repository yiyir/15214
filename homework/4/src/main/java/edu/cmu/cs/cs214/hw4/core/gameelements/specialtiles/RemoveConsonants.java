package edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles;

import edu.cmu.cs.cs214.hw4.core.ScrabbleImpl;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.GameBoard;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.Square;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the 'Remove-consonants' special tile.
 */
public class RemoveConsonants implements SpecialTile {
    private static final List<Character> NON_CONSONANTS = new ArrayList<>();

    public RemoveConsonants() {
        NON_CONSONANTS.add('a');
        NON_CONSONANTS.add('e');
        NON_CONSONANTS.add('i');
        NON_CONSONANTS.add('o');
        NON_CONSONANTS.add('u');
    }

    /**
     * Activates the function of the special tile(i.e. the letter tiles for all consonants (bcdfghjklmnpqrstvwxyz) on the board are removed).
     *
     * @param index the index of the square on which the special tile is placed
     * @param game  the game system of the game
     */
    @Override
    public void activateFunc(Integer index, ScrabbleImpl game) {
        GameBoard board = game.getGameBoard();
        List<Square> squares = board.getSquares();
        for (int i = 0; i < squares.size(); i++) {
            LetterTile letterTile = squares.get(i).getLetterTile();
            if (letterTile != null) {
                if (!NON_CONSONANTS.contains(letterTile.getLetter())) {
                    board.removeLetterTile(i);
                }
            }
        }
    }

    /**
     * Gets the price of this special tile.
     *
     * @return the price(in points) of the special tile
     */
    @Override
    public int getPrice() {
        return 30;
    }

    @Override
    public String toString() {
        return "RemoveConsonants";
    }
}
