package edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles;

import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.GameBoard;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.Square;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.TileBag;
import edu.cmu.cs.cs214.hw4.core.game.GameSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents the 'Boom' special tile.
 */
public class Boom implements SpecialTile {
    /**
     * Activates the function of the special tile(i.e. all tiles in a 3-tile radius on the board are removed from the board,
     * only surviving tiles are scored for this round).
     *
     * @param index the index of the square on which the special tile is placed
     * @param game  the game system of the game
     */
    @Override
    public void activateFunc(Integer index, GameSystem game) {

        GameBoard board = game.getGameBoard();
        List<List<Integer>> words = board.getWords(game.getCurrentMove());
        // record the square indices where the tiles are removed
        List<Integer> removedTiles = new ArrayList<>();
        int rowIndex = index / 15;
        int colIndex = index % 15;
        // get the row and col indices of the removed tiles
        Integer[] row = {rowIndex - 3, rowIndex - 2, rowIndex - 2, rowIndex - 2, rowIndex - 1, rowIndex - 1, rowIndex - 1, rowIndex - 1, rowIndex - 1,
                rowIndex, rowIndex, rowIndex, rowIndex, rowIndex, rowIndex, rowIndex, rowIndex + 1, rowIndex + 1, rowIndex + 1, rowIndex + 1,
                rowIndex + 1, rowIndex + 2, rowIndex + 2, rowIndex + 2, rowIndex + 3};
        Integer[] col = {colIndex, colIndex - 1, colIndex, colIndex + 1, colIndex - 2, colIndex - 1, colIndex, colIndex + 1, colIndex + 2,
                colIndex - 3, colIndex - 2, colIndex - 1, colIndex, colIndex + 1, colIndex + 2, colIndex + 3, colIndex - 2, colIndex - 1, colIndex, colIndex + 1, colIndex + 2,
                colIndex - 1, colIndex, colIndex + 1, colIndex};
        for (int i = 0; i < 25; i++) {

            if (row[i] >= 0 && row[i] <= 14 && col[i] >= 0 && col[i] <= 14) {
                int removedIndex = row[i] * 15 + col[i];
                removedTiles.add(removedIndex);
            }
        }
        // calculate the score to be deducted from the player after the tiles are removed

        Map<Integer, LetterTile> move = game.getCurrentMove();
        int totalRemovedScore = 0;
        for (List<Integer> word : words) {
            int multiplier = 1;
            int removedScore = 0;
            for (int i : word) {
                Square square = board.getGameBoard().get(i);
                if (move.keySet().contains(i)) {
                    if (square.isForWord()) {
                        multiplier *= square.getMultiplier();
                    } else {// check if the current index is contained in the removed tiles' indices
                        if (removedTiles.contains(i))
                            removedScore += square.getLetterTile().getPointValue() * square.getMultiplier();
                    }
                }

            }
            totalRemovedScore += removedScore * multiplier;
        }
        // deduct the points from the player's score
        game.getCurrentPlayer().addScore(-totalRemovedScore);
        // remove the tiles in a 3-tile radius and put them back in the tile bag
        for (int i : removedTiles) {
            LetterTile removed = board.removeLetterTile(i);
            TileBag bag = game.getTileBag();
            bag.getTileBag().add(removed);
        }
    }

    /**
     * Gets the price of this special tile.
     *
     * @return the price(in points) of the special tile
     */
    @Override
    public int getPrice() {
        return 20;
    }
}
