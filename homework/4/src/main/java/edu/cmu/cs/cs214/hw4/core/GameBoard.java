package edu.cmu.cs.cs214.hw4.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents the game board of the game Scrabble.
 */
public class GameBoard {
    /**
     * the 225 squares constituting the game board
     */
    private final List<Square> gameBoard = new ArrayList<>();

    /**
     * Creates a new game board of 225 squares.
     */
    public GameBoard() {
        Square normal = new Square(1, false);
        Square doubleWord = new Square(2, true);
        Square doubleLetter = new Square(2, false);
        Square tripleWord = new Square(3, true);
        Square tripleLetter = new Square(3, false);
        for (int i = 0; i < 225; i++) {
            gameBoard.add(normal);
        }
        int[] TWS = {0, 7, 14, 105, 119, 210, 217, 224};
        for (int i : TWS) {
            gameBoard.set(i, tripleWord);
        }
        int[] DLS = {3, 11, 36, 38, 45, 52, 59, 92, 96, 98, 102, 108, 116, 122, 126, 128, 132, 165, 172, 179, 186, 188, 213, 221};
        for (int i : DLS) {
            gameBoard.set(i, doubleLetter);
        }
        int[] TLS = {20, 24, 76, 80, 84, 88, 148, 144, 140, 136, 200, 204};
        for (int i : TLS) {
            gameBoard.set(i, tripleLetter);
        }
        int[] DWS = {16, 32, 48, 64, 112, 160, 176, 192, 208, 28, 42, 56, 70, 154, 168, 182, 196};
        for (int i : DWS) {
            gameBoard.set(i, doubleWord);
        }
    }

    /**
     * Gets the squares representing the game board.
     *
     * @return the squares representing the game board
     */
    public List<Square> getGameBoard() {
        return gameBoard;
    }

    public boolean placeLetterTiles(Map<Integer, LetterTile> move) {
return false;
    }

    /**
     * Places the special tile on the specified square.
     *
     * @param i    the index of the square
     * @param tile the special tile to be played
     * @return true if the operation succeeds
     */
    public boolean placeSpecialTile(Integer i, SpecialTile tile) {
        Square square = gameBoard.get(i);
        return square.addSpecialTile(tile);

    }

    public List<String> getWords(Map<Integer, LetterTile> move) {
return null;
    }

    public void calculateScore(Map<Integer, LetterTile> move) {

    }

    public boolean isValidLetterTilePlacement(Map<Integer, LetterTile> move) {
        return false;
    }

    public void removeTiles(Map<Integer, LetterTile> move) {

    }
}
