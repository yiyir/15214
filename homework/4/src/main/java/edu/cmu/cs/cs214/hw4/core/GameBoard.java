package edu.cmu.cs.cs214.hw4.core;

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

    /**
     * Places the letter tiles on the specified squares.
     *
     * @param move the move of play
     */
    public void placeLetterTiles(Map<Integer, LetterTile> move) {
        for (Integer i : move.keySet()) {
            Square square = gameBoard.get(i);
            square.setLetterTile(move.get(i));
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
        List<List<Integer>> result = new ArrayList<>();
        // get any one index of the placement of the move
        Integer[] array = (Integer[]) move.keySet().toArray();
        Integer index = array[0];
        //check if the move is in a row or column
        Set<Integer> col = new HashSet<>();
        Set<Integer> row = new HashSet<>();
        for (Integer i : move.keySet()) {
            int rowIndex = i / 15;
            int colIndex = i % 15;
            row.add(rowIndex);
            col.add(colIndex);
        }
        if (row.size() == 1) {
            result.add(getHorizontalWord(index));
            for (Integer i : move.keySet()) {
                result.add(getVerticalWord(i));
            }
        } else {
            result.add(getVerticalWord(index));
            for (Integer i : move.keySet()) {
                result.add(getHorizontalWord(i));
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
        Integer upIndex = n;
        Integer downIndex = upIndex;
        while ((upIndex / 15 > 0) && gameBoard.get(upIndex - 15).getLetterTile() != null) {
            upIndex -= 15;
        }
        while ((downIndex / 15 < 14) && gameBoard.get(downIndex + 1).getLetterTile() != null) {
            downIndex += 15;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = upIndex; i <= downIndex; i += 15) {
            result.add(i);
        }
        return result;

    }

    /**
     * Gets the horizontal word given the index of one letter in the word.
     *
     * @param n the index of one letter in the word
     * @return the indices of the letters in the horizontal word
     */
    private List<Integer> getHorizontalWord(Integer n) {
        Integer leftIndex = n;
        Integer rightIndex = leftIndex;
        while ((leftIndex % 15 > 0) && gameBoard.get(leftIndex - 1).getLetterTile() != null) {
            leftIndex--;
        }
        while ((rightIndex % 15 < 14) && gameBoard.get(rightIndex + 1).getLetterTile() != null) {
            rightIndex++;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = leftIndex; i <= rightIndex; i++) {
            result.add(i);
        }
        return result;
    }

    /**
     * Calculates the total score of the given move.
     *
     * @param move the given move
     * @return the total score of the given move
     */
    public int calculateScore(Map<Integer, LetterTile> move) {
        List<List<Integer>> words = getWords(move);
        int totalScore = 0;
        for (List<Integer> word : words) {
            int multiplier = 1;
            int wordScore = 0;
            for (Integer i : word) {
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
     * Checks if the placement is valid or not(i.e. all letters are in a single row/col;
     * all tiles are on empty squares; at least one letter tile is placed next to
     * a pre-existing letter tile on the board)
     *
     * @param move the move of play
     * @return whether the placement is valid or not
     */
    public boolean isValidLetterTilePlacement(Map<Integer, LetterTile> move) {
        boolean hasTouched = false;
        Set<Integer> col = new HashSet<>();
        Set<Integer> row = new HashSet<>();
        for (Integer i : move.keySet()) {
            Square square = gameBoard.get(i);
            //check if the square is empty or not
            if (square.getLetterTile() != null) return false;
            //get the row and col indices of the square
            int rowIndex = i / 15;
            int colIndex = i % 15;
            row.add(rowIndex);
            col.add(colIndex);
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
        //check if any pre-existing letter tile has been touched
        if (!hasTouched) return false;
        // check if all letter tiles are placed on the same col/row
        if (row.size() != 1 && col.size() != 1) return false;
        // check if there are gaps between the letters
        if (row.size() == 1) {
            Integer[] array = (Integer[]) move.keySet().toArray();
            Arrays.sort(array);
            for (int i = array[0] + 1; i < array[array.length - 1]; i++) {
                if (gameBoard.get(i).getLetterTile() == null && !move.keySet().contains(i)) return false;
            }
        } else {
            Integer[] array = (Integer[]) move.keySet().toArray();
            Arrays.sort(array);
            for (int i = array[0] + 15; i < array[array.length - 1]; i += 15) {
                if (gameBoard.get(i).getLetterTile() == null && !move.keySet().contains(i)) return false;
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
}
