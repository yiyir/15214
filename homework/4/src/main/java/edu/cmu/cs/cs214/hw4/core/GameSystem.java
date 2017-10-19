package edu.cmu.cs.cs214.hw4.core;


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
     * whether the players should play in the reverse of the previous order
     */
    private boolean isReverseOrder;

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
     * Sets the order of the game to be the reverse of the previous order.
     */
    public void setReverseOrder() {
        isReverseOrder = !isReverseOrder;
    }

    /**
     * Updates the score and starts a new turn according to the game order.
     *
     * @return whether the operation succeeds or not
     */
    public boolean startNewTurn() {
        if (players.size() < 2) {
            return false;
        }

//        if (currentPlayer != null) {
//            for(Integer i : currentMove.keySet()){
//                Square square = gameBoard.getGameBoard().get(i);
//                List<SpecialTile> specialTiles = square.getSpecialTiles();
//                if(specialTiles!=null){
//                    for(SpecialTile tile: specialTiles){
//                        tile.activateFunc(gameBoard,this);
//                    }
//                }
//            }
//            int score = gameBoard.calculateScore(currentMove);
//            currentPlayer.addScore(score);
//        }
        if (!isReverseOrder) {
            if (currentPlayer != null) players.offerLast(currentPlayer);
            currentPlayer = players.pollFirst();
        } else {
            if (currentPlayer != null) players.offerFirst(currentPlayer);
            currentPlayer = players.pollLast();
        }
        currentMove = null;
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
     * Plays the letter tiles on the specified squares.
     *
     * @param letterTiles the letter tiles to be played
     * @return whether the operation succeeds or not
     */
    public boolean playLetterTiles(Map<Integer, LetterTile> letterTiles) {
        if (!gameBoard.isValidLetterTilePlacement(letterTiles)) return false;
        if (!currentPlayer.removeLetterTiles(letterTiles)) return false;
        gameBoard.placeLetterTiles(letterTiles);
        currentMove = letterTiles;
        return true;
    }

    /**
     * Challenges the current move. This happens before calculating the score for the current play.
     *
     * @param challenger the player who issues the challenge
     */
    public void challenge(Player challenger) {
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
        if (dictionary.contains(result)) {
            challenger.setSkipTurn();
        } else {
            gameBoard.removeTiles(currentMove);
            currentPlayer.addLetterTiles(currentMove);
            startNewTurn();
        }
    }

    /**
     * Buys the special tiles from the game and adds them to the current player's tile inventory.
     *
     * @param specialTiles the special tiles to be bought
     * @return true if the operation is successful, false if the player doesn't have enough points to buy the tiles
     */
    public boolean buySpecialTiles(Map<SpecialTile, Integer> specialTiles) {
        int money = currentPlayer.getScore();
        for (SpecialTile tile : specialTiles.keySet()) {
            money -= (tile.getPrice() * specialTiles.get(tile));
            if (money < 0) return false;
        }
        currentPlayer.setScore(money);
        Map<SpecialTile, Integer> tileInventory = currentPlayer.getTileInventory();
        for (SpecialTile tile : specialTiles.keySet()) {
            Integer num = tileInventory.get(tile);
            if (num == null) {
                tileInventory.put(tile, specialTiles.get(tile));
            } else {
                tileInventory.put(tile, num + specialTiles.get(tile));
            }
        }
        return true;
    }

    /**
     * Exchanges the selected letter tiles on the player's rack.
     *
     * @param tiles the selected letter tiles
     * @return true if the operation succeeds, false if the selected tiles are not on the player's rack
     */
    public boolean exchangeTiles(List<LetterTile> tiles) {
        if (!currentPlayer.getRack().containsAll(tiles)) return false;
        currentPlayer.removeLetterTiles(tiles);
        currentPlayer.updateRack(tileBag);
        tileBag.getTileBag().addAll(tiles);
        startNewTurn();
        return true;
    }


}
