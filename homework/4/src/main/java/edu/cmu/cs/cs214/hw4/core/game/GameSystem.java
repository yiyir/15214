package edu.cmu.cs.cs214.hw4.core.game;


import edu.cmu.cs.cs214.hw4.core.gameelements.*;
import edu.cmu.cs.cs214.hw4.core.gameelements.Dictionary;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.GameBoard;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.Square;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.TileBag;
import edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles.SpecialTile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * This class represents the game system of the game Scrabble.
 * Each game has 2-4 players, a bag of letter tiles, a dictionary, a game board and several special tiles.
 */
public class GameSystem {
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
     * whether the current main word played should be scored negatively
     */
    private boolean isNegative;
    /**
     * whether the current move is the first move.
     */
    private boolean isFirstMove;
    /**
     * whether the game has ended or not
     */
    private boolean isGameEnd;

    /**
     * Creates a new game.
     */
    public GameSystem() {
        try {
            dictionary = new Dictionary(new File("words.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("The dictionary is not found!");
        } finally {
            tileBag = new TileBag();
            players = new LinkedList<>();
            gameBoard = new GameBoard();
        }
    }

    /**
     * Gets the game board.
     *
     * @return the game board
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Gets the tile bag.
     *
     * @return the tile bag
     */
    public TileBag getTileBag() {
        return tileBag;
    }

    /**
     * Gets the current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
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

    /**
     * Sets the current main word played to be scored negatively.
     */
    public void setNegative() {
        isNegative = true;
    }

    /**
     * Adds a new player to the game.
     *
     * @param id the player's id
     * @return whether the player is added successfully
     */
    public boolean addPlayer(String id) {
        if (players.size() == 4) return false;
        Player newPlayer = new Player(id);
        players.offerLast(newPlayer);
        return true;
    }

    /**
     * Starts a new turn according to the game order.
     *
     * @return whether the operation succeeds or not
     */
    public boolean startNewTurn() {
        // fail to start a new turn when there are less than 2 players
        if (players.size() < 2) {
            return false;
        }
        // fail to start a new turn when the game should end, get the winner and print out the winner's name
        if (isGameEnd) {
            Player winner = getWinner();
            System.out.println("Congratulations: " + winner.getId() + "wins!");
            return false;
        }
        // get the first player and mark the first turn
        if (currentPlayer == null) {
            currentPlayer = players.pollFirst();
            isFirstMove = true;
        } else {
            isFirstMove = false;
            // update the number of scoreless turns if necessary
            if (currentMove == null) scorelessTurns++;
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
        // load the new player's rack
        currentPlayer.updateRack(tileBag);
        return true;
    }


    /**
     * Plays the special tile on the specified square of the game board.
     *
     * @param i    the index of the square on the game board on which the special tile is to be placed
     * @param tile the special tile to be played
     * @return true if the operation succeeds, false if the player doesn't have the special tile
     */
    public boolean playSpecialTile(Integer i, SpecialTile tile) {
        if (!currentPlayer.removeSpecialTile(tile)) return false;
        return gameBoard.placeSpecialTile(i, tile);
    }

    /**
     * Buys the special tiles from the game and adds them to the current player's tile inventory.
     *
     * @param specialTiles the special tiles to be bought
     * @return true if the operation is successful, false if the player doesn't have enough points to buy the tiles
     */
    public boolean buySpecialTiles(Map<SpecialTile, Integer> specialTiles) {
        // check if the player has enough points to buy the special tiles
        int money = currentPlayer.getScore();
        for (SpecialTile tile : specialTiles.keySet()) {
            money -= (tile.getPrice() * specialTiles.get(tile));
            if (money < 0) return false;
        }
        // deduct the points from the player's score
        currentPlayer.setScore(money);
        // add the special tiles bought to the player's tile inventory
        currentPlayer.addSpecialTiles(specialTiles);
        return true;
    }

    /**
     * Plays the letter tiles on the specified squares.
     *
     * @param letterTiles the letter tiles to be played
     * @return whether the operation succeeds or not
     */
    public boolean playLetterTiles(Map<Integer, LetterTile> letterTiles) {
        // check validation of the move
        if (isFirstMove) {
            if (!gameBoard.isValidLetterTilePlacement(letterTiles, true)) return false;
        } else {
            if (!gameBoard.isValidLetterTilePlacement(letterTiles, false)) return false;
        }
        // remove the letter tiles from the current player's rack
        if (!currentPlayer.removeLetterTiles(letterTiles)) return false;
        // place the letter tiles on the game board
        gameBoard.placeLetterTiles(letterTiles);
        // update the current move
        currentMove = letterTiles;
        return true;
    }

    /**
     * Challenges the current move. This happens before calculating/updating the score for the current play.
     *
     * @param challenger the player who issues the challenge
     */
    public void challenge(Player challenger) {
        // get the words out of the current move
        List<String> result = new ArrayList<>();
        List<List<Integer>> words = gameBoard.getWords(currentMove);
        for (List<Integer> word : words) {
            Integer[] array = (Integer[]) word.toArray();
            Arrays.sort(array);
            StringBuilder sb = new StringBuilder();
            for (Integer i : array) {
                Square square = gameBoard.getGameBoard().get(i);
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
            currentPlayer.addLetterTiles(currentMove);
            currentMove = null;
            startNewTurn();
        }
    }


    /**
     * Exchanges the selected letter tiles on the player's rack.
     *
     * @param tiles the selected letter tiles
     * @return true if the operation succeeds, false if the selected tiles are not on the player's rack
     */
    public boolean exchangeTiles(List<LetterTile> tiles) {
        // check if the player has the tiles
        if (!currentPlayer.getRack().containsAll(tiles)) return false;
        // check if the tile bag has enough tiles to exchange
        if (tileBag.getTileBag().size() < tiles.size()) return false;
        currentPlayer.removeLetterTiles(tiles);
        currentPlayer.updateRack(tileBag);
        tileBag.getTileBag().addAll(tiles);
        startNewTurn();
        return true;
    }

    /**
     * Ends the turn by calculating and updating the current player's score after activating the special tiles.
     */

    public void endTurn() {
        // if the player chooses to end the turn without playing any letter tile
        if (currentMove == null) {
            if (scorelessTurns >= 6) isGameEnd = true;
            startNewTurn();
            return;
        }
        scorelessTurns = 0;
        // get the original score of the play when no special tile is activated
        int score = gameBoard.calculateScore(currentMove);
        // add the original score to the player
        currentPlayer.addScore(score);
        // activate the special tiles that are triggered by the play
        for (Integer i : currentMove.keySet()) {
            Square square = gameBoard.getGameBoard().get(i);
            List<SpecialTile> specialTiles = square.getSpecialTiles();
            if (specialTiles != null) {
                for (SpecialTile tile : specialTiles) {
                    tile.activateFunc(i, this);
                }
            }
        }
        // check if the main word of the current move should be scored negatively
        if (isNegative) currentPlayer.addScore(gameBoard.getScoreForMainWord(currentMove) * (-2));
        // check if the game should end(i.e. all letters have been drawn and one player uses his or her last letter)
        if (tileBag.getTileBag().isEmpty() && currentPlayer.getRack().isEmpty()) isGameEnd = true;
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


}
