package edu.cmu.cs.cs214.hw4.core.gameelements.gameboard;

import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;
import edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles.SpecialTile;

import java.util.*;

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
        for (int i = 0; i < 225; i++) {
            gameBoard.add(new Square(1, false));
        }
        int[] TWS = {0, 7, 14, 105, 119, 210, 217, 224};
        for (int i : TWS) {
            gameBoard.set(i, new Square(3, true));
        }
        int[] DLS = {3, 11, 36, 38, 45, 52, 59, 92, 96, 98, 102, 108, 116, 122, 126, 128, 132, 165, 172, 179, 186, 188, 213, 221};
        for (int i : DLS) {
            gameBoard.set(i, new Square(2, false));
        }
        int[] TLS = {20, 24, 76, 80, 84, 88, 148, 144, 140, 136, 200, 204};
        for (int i : TLS) {
            gameBoard.set(i, new Square(3, false));
        }
        int[] DWS = {16, 32, 48, 64, 112, 160, 176, 192, 208, 28, 42, 56, 70, 154, 168, 182, 196};
        for (int i : DWS) {
            gameBoard.set(i, new Square(2, true));
        }
    }

    /**
     * Gets the squares representing the game board.
     *
     * @return the squares representing the game board
     */
    public List<Square> getSquares() {
        return gameBoard;
    }

    /**
     * Places the letter tiles on the specified squares.
     *
     * @param move the move of play
     */
    public void placeLetterTiles(Map<Integer, LetterTile> move) {
        for (Integer i : move.keySet()) {
            gameBoard.get(i).setLetterTile(move.get(i));
        }
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

    /**
     * Gets all the words made in the play.
     *
     * @param move the move of play
     * @return all the words made in the play
     */
    public List<List<Integer>> getWords(Map<Integer, LetterTile> move) {
        // use the indices of squares to represent the word
        List<List<Integer>> result = new ArrayList<>();
        // get any one index of the placement of the move
        List<Integer> indices = new ArrayList<>(move.keySet());
        Integer index = indices.get(0);
        if (rowOrCol(move)) { // if the move is played in a row
            result.add(getHorizontalWord(index));
            for (int i : move.keySet()) {
                List<Integer> word = getVerticalWord(i);
                if (word != null) result.add(getVerticalWord(i));
            }
        } else { // if the move is played in a column
            result.add(getVerticalWord(index));
            for (int i : move.keySet()) {
                List<Integer> word = getHorizontalWord(i);
                if (word != null) result.add(word);
            }
        }
        return result;
    }

    /**
     * Gets the vertical word given the index of one letter in the word.
     *
     * @param n the index of one letter in the word
     * @return the indices of the letters in the vertical word
     */
    private List<Integer> getVerticalWord(Integer n) {
        int upIndex = n;
        int downIndex = upIndex;
        // keep moving up until find the first letter of the word
        while ((upIndex / 15 > 0) && gameBoard.get(upIndex - 15).getLetterTile() != null) {
            upIndex -= 15;
        }
        // keep moving down until find the last letter of the word
        while ((downIndex / 15 < 14) && gameBoard.get(downIndex + 15).getLetterTile() != null) {
            downIndex += 15;
        }
        List<Integer> result = new ArrayList<>();
        // add every letter that forms the word
        for (int i = upIndex; i <= downIndex; i += 15) {
            result.add(i);
        }
        if (result.size() < 2) return null;
        return result;

    }

    /**
     * Gets the horizontal word given the index of one letter in the word.
     *
     * @param n the index of one letter in the word
     * @return the indices of the letters in the horizontal word
     */
    private List<Integer> getHorizontalWord(Integer n) {
        int leftIndex = n;
        int rightIndex = leftIndex;
        // keep moving left until find the first letter of the word
        while ((leftIndex % 15 > 0) && gameBoard.get(leftIndex - 1).getLetterTile() != null) {
            leftIndex--;
        }
        // keep moving right until find the last letter of the word
        while ((rightIndex % 15 < 14) && gameBoard.get(rightIndex + 1).getLetterTile() != null) {
            rightIndex++;
        }
        List<Integer> result = new ArrayList<>();
        // add every letter that forms the word
        for (int i = leftIndex; i <= rightIndex; i++) {
            result.add(i);
        }
        if (result.size() < 2) return null;
        return result;
    }

    /**
     * Calculates the total score of the given move before any special tile is activated.
     *
     * @param move the given move
     * @return the total score of the given move
     */
    public int calculateScore(Map<Integer, LetterTile> move) {
        if (move == null) return 0;
        // get all the words from the given move
        List<List<Integer>> words = getWords(move);
        int totalScore = 0;
        for (List<Integer> word : words) {
            int multiplier = 1;
            int wordScore = 0;
            for (Integer i : word) {
                Square square = gameBoard.get(i);
                // only check the premium  squares for the tiles placed in the move
                if (move.keySet().contains(i)) {
                    if (square.isForWord()) {
                        multiplier *= square.getMultiplier();
                    } else {
                        wordScore += square.getLetterTile().getPointValue() * square.getMultiplier();
                    }
                }
            }
            totalScore += wordScore * multiplier;
        }
        if (move.values().size() == 7) totalScore += 50; // "Bingo"
        return totalScore;
    }

    /**
     * Calculates the total original score for the words to be negatively scored.
     *
     * @param move  the given move
     * @param index the index of the square where 'Negative-points' special tile is triggered
     * @return the total original score for the words to be negatively scored
     */
    public int getScoreForNegativeWords(Map<Integer, LetterTile> move, Integer index) {
        List<List<Integer>> negativeWords = new ArrayList<>();
        negativeWords.add(this.getHorizontalWord(index));
        negativeWords.add(this.getVerticalWord(index));
        int totalScore = 0;
        for (List<Integer> word : negativeWords) {
            int multiplier = 1;
            int wordScore = 0;
            for (int i : word) {
                Square square = gameBoard.get(i);
                if (move.keySet().contains(i)) {
                    if (square.isForWord()) {
                        multiplier *= square.getMultiplier();
                    } else {
                        wordScore += square.getLetterTile().getPointValue() * square.getMultiplier();
                    }
                }
            }
            totalScore += wordScore * multiplier;
        }
        return totalScore;

    }

    /**
     * Checks if the given valid move is in a row or column.
     *
     * @param move the given validated move
     * @return true if the move is in a row, false if it's in a column
     */
    private boolean rowOrCol(Map<Integer, LetterTile> move) {
        Set<Integer> row = new HashSet<>();
        for (Integer i : move.keySet()) {
            int rowIndex = i / 15;
            row.add(rowIndex);
        }
        if (row.size() == 1) return true;
        return false;
    }


    /**
     * Checks if the placement is valid or not(i.e. all letters are in a single row/col;
     * all tiles are on empty squares; at least one letter tile is placed next to
     * a pre-existing letter tile on the board)
     *
     * @param move        the move of play
     * @param isFirstMove whether the given move is the first move of the game
     * @return whether the placement is valid or not
     */

    public boolean isValidLetterTilePlacement(Map<Integer, LetterTile> move, boolean isFirstMove) {
        if (isFirstMove) {
            // check if the first move covers the center square and contains at least two letter tiles
            if (move.keySet().size() < 2 || !move.keySet().contains(112)) return false;
        }
        boolean hasTouched = false;
        Set<Integer> col = new HashSet<>();
        Set<Integer> row = new HashSet<>();
        for (Integer i : move.keySet()) {
            //get the row and col indices of the square
            int rowIndex = i / 15;
            int colIndex = i % 15;
            row.add(rowIndex);
            col.add(colIndex);
            if (!isFirstMove) {
                //check if the square is empty or not
                Square square = gameBoard.get(i);
                if (square.getLetterTile() != null) return false;
                //check if any pre-existing letter tile has been touched
                List<Integer> nei = new ArrayList<>();
                if (rowIndex > 0) {
                    nei.add(i - 15);
                }
                if (rowIndex < 14) {
                    nei.add(i + 15);
                }
                if (colIndex > 0) {
                    nei.add(i - 1);
                }
                if (colIndex < 14) {
                    nei.add(i + 1);
                }
                for (Integer n : nei) {
                    if (gameBoard.get(n).getLetterTile() != null) hasTouched = true;
                }
            }
        }
        if (!isFirstMove && !hasTouched) return false;
        // check if all letter tiles are placed on the same col/row
        if (row.size() != 1 && col.size() != 1) return false;
        // check if there are gaps between the letters
        List<Integer> indices = new ArrayList<>();
        for (Integer i : move.keySet()) {
            indices.add(i);
        }
        Collections.sort(indices);
        if (row.size() == 1) {
            for (int k = indices.get(0) + 1; k < indices.get(indices.size() - 1); k++) {
                if (gameBoard.get(k).getLetterTile() == null && !move.keySet().contains(k)) return false;
            }
        } else {
            for (int k = indices.get(0) + 15; k < indices.get(indices.size() - 1); k += 15) {
                if (gameBoard.get(k).getLetterTile() == null && !move.keySet().contains(k)) return false;
            }
        }
        return true;

    }

    /**
     * Removes the letter tiles of the move from the game board.
     *
     * @param move the move of play
     */
    public void removeTiles(Map<Integer, LetterTile> move) {
        for (Integer i : move.keySet()) {
            gameBoard.get(i).setLetterTile(null);
        }
    }

    /**
     * Removes the letter tile on the specified square of the game board.
     *
     * @param i the index of the square
     * @return the letter tile removed
     */
    public LetterTile removeLetterTile(Integer i) {
        LetterTile letterTile = gameBoard.get(i).getLetterTile();
        gameBoard.get(i).setLetterTile(null);
        return letterTile;
    }
}
