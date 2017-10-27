package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.GameChangeListener;
import edu.cmu.cs.cs214.hw4.core.Scrabble;
import edu.cmu.cs.cs214.hw4.core.gameelements.Player;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.GameBoard;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.Square;
import edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles.SpecialTile;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlayerPanel extends JPanel implements GameChangeListener {
    private final Scrabble game;
    private final Player player;
    private final List<Player> players;
    private final List<JButton> squares;

    public PlayerPanel(Scrabble game, Player player) {
        this.game = game;
        this.player = player;
        players = new ArrayList<>();
        squares = new ArrayList<>();
        for (Player p : game.getPlayers()) {
            players.add(p);
        }

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        JLabel currentPlayerLabel = new JLabel("Current player: ");
        JTextArea scoresArea = new JTextArea();
        StringBuilder sb = new StringBuilder("Scores: ");
        for (Player p : players) {
            sb.append(player.toString() + ": " + player.getScore() + "   ");
        }
        scoresArea.setText(sb.toString());
        infoPanel.add(currentPlayerLabel);
        infoPanel.add(scoresArea);

        JPanel gridPanel = new JPanel(new GridLayout(15, 15));
        for (int i = 0; i < 225; i++) {
            GameBoard board = game.getGameBoard();
            List<Square> boardSquares = board.getSquares();
            JButton button = new JButton(boardSquares.get(i).toString());
            gridPanel.add(button);
            squares.add(button);
        }
        JPanel operationPanel = new JPanel(new GridLayout(2, 3));
        JButton buyS = new JButton("Buy special tiles");
        JButton playS = new JButton("Play special tiles");
        JButton playL = new JButton("Play letter tiles");
        JButton chal = new JButton("Challenge");
        JButton exch = new JButton("Exchange tiles");
        JButton pass = new JButton("Pass");
        operationPanel.add(buyS);
        operationPanel.add(playS);
        operationPanel.add(playL);
        operationPanel.add(chal);
        operationPanel.add(exch);
        operationPanel.add(pass);
        JPanel rack = new JPanel(new GridLayout(1, 7));
        for (int i = 0; i < 7; i++) {
            JButton tile = new JButton();
            rack.add(tile);
        }
        JPanel tileInventory = new JPanel(new GridLayout(1, 5));
        JButton boom = new JButton("Boom");
        JButton myOwnTile = new JButton("MyOwnTile");
        JButton negativePoints = new JButton("NegativePoints");
        JButton removeConsonants = new JButton("RemoveConsonants");
        JButton reversePlayerOrder = new JButton("ReversePlayerOrder");
        tileInventory.add(boom);
        tileInventory.add(myOwnTile);
        tileInventory.add(negativePoints);
        tileInventory.add(removeConsonants);
        tileInventory.add(reversePlayerOrder);
        JPanel tilesPanel = new JPanel(new BorderLayout());
        tilesPanel.add(tileInventory, BorderLayout.NORTH);
        tilesPanel.add(rack, BorderLayout.SOUTH);
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.add(operationPanel, BorderLayout.WEST);
        playerPanel.add(tilesPanel, BorderLayout.EAST);
        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(playerPanel, BorderLayout.SOUTH);
        game.addGameChangeListener(this);
    }

    @Override
    public void currentPlayerChanged(Player player) {

    }

    @Override
    public void specialTilesChanged(Map<SpecialTile, Integer> tileInventory) {

    }

    @Override
    public void rackChanged(List<LetterTile> rack) {

    }

    @Override
    public void scoreChanged(int score) {

    }

    @Override
    public void squareChanged(int index) {

    }

    @Override
    public void squaresChanged(Set<Integer> indices) {

    }

    @Override
    public void gameEnded(Player winner) {

    }

    @Override
    public void dictionaryNotFound() {

    }
}
