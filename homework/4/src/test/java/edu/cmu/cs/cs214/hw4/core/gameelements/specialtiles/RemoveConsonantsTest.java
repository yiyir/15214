package edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles;


import edu.cmu.cs.cs214.hw4.core.ScrabbleImpl;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.GameBoard;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.Square;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class RemoveConsonantsTest {
    private final ScrabbleImpl game = new ScrabbleImpl();
    private final SpecialTile specialTile = new RemoveConsonants();

    @Test
    public void activateFuncTest() {
        GameBoard board = game.getGameBoard();
        List<Square> squares = board.getSquares();
        Map<Integer, LetterTile> letterTiles = new HashMap<>();
        letterTiles.put(20, new LetterTile(1, 'a'));
        letterTiles.put(30, new LetterTile(1, 'b'));
        letterTiles.put(40, new LetterTile(1, 'c'));
        letterTiles.put(50, new LetterTile(1, 'd'));
        letterTiles.put(60, new LetterTile(1, 'e'));
        letterTiles.put(70, new LetterTile(1, 'f'));
        board.placeLetterTiles(letterTiles);
        assertTrue(squares.get(30).getLetterTile().getLetter() == 'b');
        assertTrue(squares.get(40).getLetterTile().getLetter() == 'c');
        assertTrue(squares.get(50).getLetterTile().getLetter() == 'd');
        assertTrue(squares.get(70).getLetterTile().getLetter() == 'f');
        specialTile.activateFunc(5, game);
        assertTrue(squares.get(20).getLetterTile().getLetter() == 'a');
        assertNull(squares.get(30).getLetterTile());
        assertNull(squares.get(40).getLetterTile());
        assertNull(squares.get(50).getLetterTile());
        assertTrue(squares.get(60).getLetterTile().getLetter() == 'e');
        assertNull(squares.get(70).getLetterTile());


    }


}
