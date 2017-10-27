package edu.cmu.cs.cs214.hw4.core.game;

import edu.cmu.cs.cs214.hw4.core.ScrabbleImpl;
import edu.cmu.cs.cs214.hw4.core.gameelements.Player;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.Square;
import edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles.*;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;
import org.junit.Test;

import java.util.*;


import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;


public class ScrabbleImplTest {
    Player a = new Player("yiyi");
    Player b = new Player("Bob");
    Player c = new Player("cici");
    private ScrabbleImpl game = new ScrabbleImpl();
    public static final SpecialTile BOOM = new Boom();
    public static final SpecialTile MY_OWN_TILE = new MyOwnTile();
    public static final SpecialTile NEGATIVE_POINTS = new NegativePoints();
    public static final SpecialTile REVERSE_ORDER = new ReversePlayerOrder();


    @Test
    public void startNewTurn() {
        game.addPlayer(a);
        game.addPlayer(b);
        game.addPlayer(b);
        assertTrue(game.startNewTurn());
        assertNull(game.getCurrentMove());
        assertEquals(game.getCurrentPlayer(),a);
        assertEquals(game.getCurrentPlayer().getRack().size(), 7);

    }

    @Test
    public void playSpecialTile() {
        buySpecialTiles();
        assertTrue(game.playSpecialTile(50, REVERSE_ORDER));
        assertFalse(game.playSpecialTile(50, NEGATIVE_POINTS));
        assertTrue(game.playSpecialTile(112, BOOM));
        assertTrue(game.playSpecialTile(112, MY_OWN_TILE));
        assertTrue(a.getTileInventory().size() == 0);
        assertTrue(game.getGameBoard().getSquares().get(112).getSpecialTiles().size() == 2);


    }

    @Test
    public void buySpecialTiles() {
        startNewTurn();
        Player firstPlayer = game.getCurrentPlayer();
        firstPlayer.setScore(50);
        Map<SpecialTile, Integer> buy = new HashMap<>();
        buy.put(BOOM, 1);
        buy.put(MY_OWN_TILE, 1);
        buy.put(REVERSE_ORDER, 1);
        game.buySpecialTiles(buy);
        assertTrue(firstPlayer.getTileInventory().size() == 3);
    }

    @Test
    public void playLetterTiles() {
        startNewTurn();
        List<Square> board = game.getGameBoard().getSquares();
        Player firstPlayer = game.getCurrentPlayer();
        Map<Integer, LetterTile> invalidFirstMove = new HashMap<>();
        invalidFirstMove.put(112, firstPlayer.getRack().get(4));
        assertFalse(game.playLetterTiles(invalidFirstMove));
        invalidFirstMove.put(46, firstPlayer.getRack().get(3));
        assertFalse(game.playLetterTiles(invalidFirstMove));
        invalidFirstMove.remove(46);
        invalidFirstMove.put(114, firstPlayer.getRack().get(1));
        assertFalse(game.playLetterTiles(invalidFirstMove));
        Map<Integer, LetterTile> validFirstMove = new HashMap<>();
        validFirstMove.put(112, firstPlayer.getRack().get(1));
        validFirstMove.put(113, firstPlayer.getRack().get(2));
        validFirstMove.put(114, firstPlayer.getRack().get(3));
        assertTrue(game.playLetterTiles(validFirstMove));
        assertTrue(firstPlayer.getRack().size() == 4);
        assertTrue(board.get(112).getLetterTile() == validFirstMove.get(112));
        assertTrue(board.get(113).getLetterTile() == validFirstMove.get(113));
        assertTrue(board.get(114).getLetterTile() == validFirstMove.get(114));


    }

    @Test
    public void challenge() {
        playLetterTiles();
        game.challenge(b);
        assertTrue(game.getCurrentPlayer() == b);
        assertTrue(game.getCurrentMove() == null);
        assertTrue(a.getRack().size() == 7);
        assertTrue(a.getScore() == 0);

    }

    @Test
    public void exchangeTiles() {
        startNewTurn();
        List<LetterTile> rack = game.getCurrentPlayer().getRack();
        List<LetterTile> exchange = new ArrayList<>();
        exchange.add(rack.get(0));
        exchange.add(rack.get(1));
        exchange.add(rack.get(2));
        game.exchangeTiles(exchange);
        assertTrue(a.getRack().size() == 7);
    }

    @Test
    public void endTurn() {
        startNewTurn();
        a.setScore(50);
        Map<SpecialTile, Integer> buy = new HashMap<>();
        buy.put(BOOM, 1);
        buy.put(MY_OWN_TILE, 1);
        game.buySpecialTiles(buy);
        game.playSpecialTile(112, BOOM);
        game.playSpecialTile(112, MY_OWN_TILE);
        Map<Integer, LetterTile> validFirstMove = new HashMap<>();
        validFirstMove.put(112, a.getRack().get(1));
        validFirstMove.put(113, a.getRack().get(2));
        validFirstMove.put(114, a.getRack().get(3));
        game.playLetterTiles(validFirstMove);
        game.endTurn();
        assertTrue(a.getScore() == 10);
        assertTrue(a.getRack().size() != 7);
        assertTrue(a.isSkipTurn());
    }

}