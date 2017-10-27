package edu.cmu.cs.cs214.hw4.core;


import edu.cmu.cs.cs214.hw4.core.gameelements.*;
import edu.cmu.cs.cs214.hw4.core.gameelements.Dictionary;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.GameBoard;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.Square;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.TileBag;
import edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles.SpecialTile;

import java.io.FileNotFoundException;
import java.util.*;


/**
 * This class represents the game system of the game Scrabble.
 * Each game has 2-4 players, a bag of letter tiles, a dictionary, a game board and several special tiles.
 */
public class ScrabbleImpl implements Scrabble {
    private final List<GameChangeListener> gameChangeListeners = new ArrayList<>();
    /**
     * the bag of letter tiles used in the game
     */
    private final TileBag tileBag;
    /**
     * the game board used in the game
     */
    private final GameBoard gameBoard;
    /**
     * the players of the game
     */
    private final Deque<Player> players;
    /**
     * the dictionary used in the game
     */
    private Dictionary dictionary;
    /**
     * the current player
     */
    private Player currentPlayer;
    /**
     * the current play/move
     */
    private Map<Integer, LetterTile> currentMove;
    /**
     * the number of successive scoreless turns
     */
    private int scorelessTurns = 0;
    /**
     * whether the players should play in the reverse of the previous order
     */
    private boolean isReverseOrder;
    /**
     * whether the current move is the first move.
     */
    private boolean isFirstMove;


    /**
     * Creates a new game.
     */
    public ScrabbleImpl() {
        try {
            dictionary = new Dictionary();
        } catch (FileNotFoundException e) {
            notifyDictionaryNotFound();
        } finally {
            tileBag = new TileBag();
            players = new LinkedList<>();
            gameBoard = new GameBoard();
        }
    }


    /**
     * Gets the current move.
     *
     * @return the current move
     */
    public Map<Integer, LetterTile> getCurrentMove() {
        return currentMove;
    }

    /**
     * Sets the order of the game to be the reverse of the previous order.
     */
    public void setReverseOrder() {
        isReverseOrder = !isReverseOrder;
    }

    @Override
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public Deque<Player> getPlayers() {
        return players;
    }

    @Override
    public void addGameChangeListener(GameChangeListener listener) {
        this.gameChangeListeners.add(listener);
    }

    @Override
    public void removeGameChangeListener(GameChangeListener listener) {
        this.gameChangeListeners.remove(listener);
    }


    @Override
    public boolean addPlayer(Player player) {
        if (players.size() == 4) return false;
        players.offerLast(player);
        return true;
    }


    @Override
    public boolean startNewTurn() {
        // fail to start a new turn when there are less than 2 players
        if (players.size() < 2) {
            return false;
        }
        // get the first player and mark the first turn
        if (currentPlayer == null) {
            currentPlayer = players.pollFirst();
            isFirstMove = true;
        } else {
            // updates the number of consecutive scoreless turns if necessary
            if (currentMove == null) {
                scorelessTurns++;
            } else {
                scorelessTurns = 0;
            }
            // check if the game should end here
            checkGameEnd();
            isFirstMove = false;
            // check the playing order of the game
            if (!isReverseOrder) {
                players.offerLast(currentPlayer);
                currentPlayer = players.pollFirst();
                // check if the current player has any turns to skip
                while (currentPlayer.isSkipTurn()) {
                    currentPlayer.reduceNumOfSkips();
                    players.offerLast(currentPlayer);
                    currentPlayer = players.pollFirst();
                }
            } else {
                players.offerFirst(currentPlayer);
                currentPlayer = players.pollLast();
                // check if the current player has any turns to skip
                while (currentPlayer.isSkipTurn()) {
                    currentPlayer.reduceNumOfSkips();
                    players.offerFirst(currentPlayer);
                    currentPlayer = players.pollLast();
                }
            }
            // set the currentMove back to null
            currentMove = null;
        }
        notifyPlayerChanged();
        // load the new player's rack
        currentPlayer.updateRack(tileBag);
        notifyRackChanged();
        return true;
    }

    @Override
    public boolean playSpecialTile(Integer i, SpecialTile tile) {
        if (!currentPlayer.removeSpecialTile(tile)) return false;
        notifySpecialTilesChanged();
        boolean result = gameBoard.placeSpecialTile(i, tile);
        notifySquareChanged(i);
        return result;
    }

    @Override
    public boolean buySpecialTiles(Map<SpecialTile, Integer> specialTiles) {
        // check if the player has enough points to buy the special tiles
        int money = currentPlayer.getScore();
        for (SpecialTile tile : specialTiles.keySet()) {
            money -= (tile.getPrice() * specialTiles.get(tile));
            if (money < 0) return false;
        }
        // deduct the points from the player's score
        currentPlayer.setScore(money);
        notifyScoreChanged();
        // add the special tiles bought to the player's tile inventory
        currentPlayer.addSpecialTiles(specialTiles);
        notifySpecialTilesChanged();
        return true;
    }

    @Override
    public boolean playLetterTiles(Map<Integer, LetterTile> letterTiles) {
        // check validation of the move
        if (isFirstMove) {
            if (!gameBoard.isValidLetterTilePlacement(letterTiles, true)) return false;
        } else {
            if (!gameBoard.isValidLetterTilePlacement(letterTiles, false)) return false;
        }
        // remove the letter tiles from the current player's rack
        currentPlayer.removeLetterTiles(letterTiles);
        notifyRackChanged();
        // place the letter tiles on the game board
        gameBoard.placeLetterTiles(letterTiles);
        notifySquaresChanged(letterTiles.keySet());
        // update the current move
        currentMove = letterTiles;
        return true;
    }


    @Override
    public void challenge(Player challenger) {
        // get the words out of the current move
        List<String> result = new ArrayList<>();
        List<List<Integer>> words = gameBoard.getWords(currentMove);
        for (List<Integer> word : words) {
            Collections.sort(word);
            StringBuilder sb = new StringBuilder();
            for (Integer i : word) {
                Square square = gameBoard.getSquares().get(i);
                sb.append(square.getLetterTile().getLetter());
            }
            result.add(sb.toString());
        }
        // check if the challenge is acceptable or not
        if (dictionary.contains(result)) {
            challenger.setSkipTurn();
        } else {
            // the current player's move as well as turn is forfeited
            gameBoard.removeTiles(currentMove);
            notifySquaresChanged(currentMove.keySet());
            currentPlayer.addLetterTiles(currentMove);
            notifyRackChanged();
            currentMove = null;
            startNewTurn();
        }
    }



    @Override
    public boolean exchangeTiles(List<LetterTile> tiles) {
        // check if the player has the tiles
        if (!currentPlayer.getRack().containsAll(tiles)) return false;
        // check if the tile bag has enough tiles to exchange
        if (tileBag.getTileBag().size() < tiles.size()) return false;
        currentPlayer.removeLetterTiles(tiles);
        currentPlayer.updateRack(tileBag);
        tileBag.getTileBag().addAll(tiles);
        notifyRackChanged();
        startNewTurn();
        return true;
    }

    @Override
    public void endTurn() {
        // if the player chooses to end the turn without playing any letter tile
        if (currentMove != null) {
            // get the original score of the play when no special tile is activated
            int score = gameBoard.calculateScore(currentMove);
            // add the original score to the player
            currentPlayer.addScore(score);
            // activate the special tiles that are triggered by the play
            for (Integer i : currentMove.keySet()) {
                Square square = gameBoard.getSquares().get(i);
                List<SpecialTile> specialTiles = square.getSpecialTiles();
                if (specialTiles != null) {
                    for (SpecialTile tile : specialTiles) {
                        tile.activateFunc(i, this);
                    }
                }
            }
            notifyScoreChanged();
        }
        startNewTurn();
    }

    /**
     * Gets the winner of the game.
     *
     * @return the winner of the game
     */
    private Player getWinner() {
        Player emptyPlayer = null;
        int sumScore = 0;
        // add the current player back to the players
        players.add(currentPlayer);
        // check if any of the players has an empty rack
        for (Player player : players) {
            if (player.getRack().isEmpty()) emptyPlayer = player;
        }

        // for each player, reduce the player's score by the sum of his or her unplayed letters
        for (Player player : players) {
            for (LetterTile tile : player.getRack()) {
                int points = tile.getPointValue();
                sumScore += points;
                player.addScore(points * (-1));
            }
        }
        // if any player has an empty rack, add the sum of the other players' unplayed letters to that player's score
        if (emptyPlayer != null) emptyPlayer.addScore(sumScore);
        // compare the scores of the players and get the winner
        Player winner = players.pollFirst();
        for (Player player : players) {
            if (player.getScore() > winner.getScore()) winner = player;
        }
        return winner;
    }

    private void notifyPlayerChanged() {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.currentPlayerChanged(getCurrentPlayer());
        }
    }

    private void notifySpecialTilesChanged() {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.specialTilesChanged(currentPlayer.getTileInventory());
        }
    }


    private void notifyRackChanged() {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.rackChanged(currentPlayer.getRack());
        }
    }

    private void notifyScoreChanged() {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.scoreChanged(currentPlayer.getScore());
        }
    }

    private void notifySquareChanged(int i) {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.squareChanged(i);
        }
    }

    private void notifySquaresChanged(Set<Integer> indices) {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.squaresChanged(indices);
        }
    }

    private void notifyDictionaryNotFound() {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.dictionaryNotFound();
        }
    }


    private void notifyGameEnd(Player winner) {
        for (GameChangeListener listener : gameChangeListeners) {
            listener.gameEnded(winner);
        }
    }

    private void checkGameEnd() {
        if (isGameEnd()) {
            notifyGameEnd(getWinner());
        }
    }

    /**
     * Checks if the game has ended.
     */
    private boolean isGameEnd() {
        // check if the game should end(i.e. all letters have been drawn and one player uses his or her last letter)
        if (tileBag.getTileBag().isEmpty() && currentPlayer.getRack().isEmpty()) return true;
        if (scorelessTurns >= 6) return true;
        return false;
    }


}
