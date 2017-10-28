package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.GameChangeListener;
import edu.cmu.cs.cs214.hw4.core.Scrabble;
import edu.cmu.cs.cs214.hw4.core.gameelements.Player;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.GameBoard;
import edu.cmu.cs.cs214.hw4.core.gameelements.gameboard.Square;
import edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles.SpecialTile;
import edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles.SpecialTileFactory;
import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.LetterTile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

import static javax.swing.JOptionPane.YES_OPTION;

public class PlayerPanel extends JPanel implements GameChangeListener {
    private final Scrabble game;
    private final Player player;
    private final List<JButton> squares = new ArrayList<>();
    private final String[] specialTileOwnership = new String[225];
    private final List<JButton> rack = new ArrayList<>();
    private final List<JButton> tileInventory = new ArrayList<>();
    private final JLabel currentPlayerLabel;
    private final JLabel scoresLabel;
    private final JButton buyS;
    private final JButton playS;
    private final JButton playL;
    private final JButton chal;
    private final JButton exch;
    private final JButton pass;


    public PlayerPanel(Scrabble game, Player player) {
        this.game = game;
        this.player = player;
        // Create the information panel on top of the frame to display the game information
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        currentPlayerLabel = new JLabel("Current player: ");
        scoresLabel = new JLabel();
        StringBuilder sb = new StringBuilder("Scores:  ");
        for (Player p : game.getPlayers()) {
            sb.append(p.toString() + ": " + p.getScore() + "   ");
        }
        scoresLabel.setText(sb.toString());
        infoPanel.add(currentPlayerLabel);
        infoPanel.add(scoresLabel);

        // create the grid panel to represent the game board with squares
        JPanel gridPanel = new JPanel(new GridLayout(15, 15));
        for (int i = 0; i < 225; i++) {
            GameBoard board = game.getGameBoard();
            List<Square> boardSquares = board.getSquares();
            JButton button = new JButton(boardSquares.get(i).toString());
            gridPanel.add(button);
            squares.add(button);
        }

        // create the operation panel for game user operations
        JPanel operationPanel = new JPanel(new GridLayout(2, 3));
        buyS = new JButton("Buy special tiles");
        playS = new JButton("Play special tiles");
        playL = new JButton("Play letter tiles");
        chal = new JButton("Challenge");
        exch = new JButton("Exchange tiles");
        pass = new JButton("Pass");
        operationPanel.add(buyS);
        operationPanel.add(playS);
        operationPanel.add(playL);
        operationPanel.add(chal);
        operationPanel.add(exch);
        operationPanel.add(pass);

        // create the rack panel
        JPanel rackPanel = new JPanel(new GridLayout(1, 7));
        for (int i = 0; i < 7; i++) {
            JButton tile = new JButton();
            rack.add(tile);
            rackPanel.add(tile);
        }
        // create the tile inventory panel
        JPanel tileInventoryPanel = new JPanel(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++) {
            JButton specialTile = new JButton();
            tileInventoryPanel.add(specialTile);
            tileInventory.add(specialTile);
        }

        // create an action listener to the buyS button
        ActionListener buySListener = e -> {
            buyS.setEnabled(false);
            // create a new frame to represent the special tile store interface
            JFrame frame = new JFrame("Special tile shop: please select items and quantities.");
            frame.setLayout(new BorderLayout());
            frame.setSize(400, 400);
            frame.setLocation(350, 350);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    buyS.setEnabled(true);
                }
            });
            // create an items panel
            JPanel itemsPanel = new JPanel();
            itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.PAGE_AXIS));
            List<JCheckBox> checkBoxes = new ArrayList<>();
            List<JTextField> numbers = new ArrayList<>();
            SpecialTileFactory store = game.getSpecialTileStore();
            for (SpecialTile sp : store.getAllSpecialTiles()) {
                JPanel itemPanel = new JPanel(new BorderLayout());
                JCheckBox checkBox = new JCheckBox(sp.toString());
                JTextField number = new JTextField("1");
                checkBoxes.add(checkBox);
                numbers.add(number);
                itemPanel.add(checkBox, BorderLayout.WEST);
                itemPanel.add(number, BorderLayout.EAST);
                itemsPanel.add(itemPanel);
            }
            // create a "Buy" button
            JButton buyButton = new JButton("Buy");
            // add action listener to buy button
            buyButton.addActionListener(f -> {
                String message = "Do you want to buy these special tiles?";
                int option = JOptionPane.showConfirmDialog(new JFrame(), message);
                if (option == YES_OPTION) {
                    Map<SpecialTile, Integer> shopList = new HashMap<>();
                    for (int i = 0; i < 5; i++) {
                        if (checkBoxes.get(i).isSelected()) {
                            if (!isInteger(numbers.get(i).getText())) {
                                String s = "Please enter a valid non-zero number";
                                JOptionPane.showMessageDialog(new JFrame(), s, "Warning",
                                        JOptionPane.WARNING_MESSAGE);
                                return;
                            } else {
                                String type = checkBoxes.get(i).getText();
                                shopList.put(store.getSpecialTile(type), Integer.valueOf(numbers.get(i).getText()));
                            }
                        }

                    }
                    boolean result = game.buySpecialTiles(shopList);
                    if (result == false) {
                        String s = "Your current points are not enough, please re-select.";
                        JOptionPane.showMessageDialog(new JFrame(), s, "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            frame.add(itemsPanel, BorderLayout.NORTH);
            frame.add(buyButton, BorderLayout.SOUTH);
        };
        // add the action listener to the buyS button
        buyS.addActionListener(buySListener);


        // add and arrange the panels to be shown on the player panel
        JPanel tilesPanel = new JPanel(new BorderLayout());
        tilesPanel.add(tileInventoryPanel, BorderLayout.NORTH);
        tilesPanel.add(rackPanel, BorderLayout.SOUTH);
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
    public void currentPlayerChanged() {
        if (player != game.getCurrentPlayer()) {
            playL.setEnabled(false);
            playS.setEnabled(false);
            buyS.setEnabled(false);
            exch.setEnabled(false);
            pass.setEnabled(false);
        }
        currentPlayerLabel.setText("Current player: " + game.getCurrentPlayer().toString());
    }

    @Override
    public void specialTilesChanged(Map<SpecialTile, Integer> tileInventory) {
        if (player == game.getCurrentPlayer()) {
            int i = 0;
            for (SpecialTile tile : tileInventory.keySet()) {
                this.tileInventory.get(i).setText(tile.toString() + " x" + tileInventory.get(tile));
                i++;
            }
        }

    }

    @Override
    public void rackChanged(List<LetterTile> rack) {
        if (player == game.getCurrentPlayer()) {
            for (int i = 0; i < rack.size(); i++) {
                this.rack.get(i).setText(rack.get(i).toString());
            }
        }
    }

    @Override
    public void scoreChanged(int score) {
        StringBuilder sb = new StringBuilder("Scores:  ");
        for (Player p : game.getPlayers()) {
            sb.append(p.toString() + ": " + p.getScore() + "   ");
        }
        scoresLabel.setText(sb.toString());
    }

    @Override
    public void squareChanged(int index) {
        if (player == game.getCurrentPlayer()) {
            Square sq = game.getGameBoard().getSquares().get(index);
            List<SpecialTile> specialTiles = sq.getSpecialTiles();
            String name = specialTiles.get(specialTiles.size() - 1).toString();
            this.specialTileOwnership[index] = name;
            this.squares.get(index).setText(name);
        }
    }

    @Override
    public void squaresChanged(Set<Integer> indices) {
        List<Square> board = game.getGameBoard().getSquares();
        for (Integer i : indices) {
            LetterTile letterTile = board.get(i).getLetterTile();
            if (letterTile != null) {
                this.squares.get(i).setText(letterTile.toString());
            } else {
                String tileName = this.specialTileOwnership[i];
                if (tileName != null) {
                    this.squares.get(i).setText(tileName);
                } else {
                    this.squares.get(i).setText("");
                }
            }
        }
    }

    @Override
    public void gameEnded(Player winner) {
        String message = "Game is over. " + winner.toString() + "wins!";
        JOptionPane.showMessageDialog(new JFrame(), message);
        System.exit(0);
    }

    @Override
    public void dictionaryNotFound() {
        String message = "The dictionary is not found.";
        JOptionPane.showMessageDialog(new JFrame(), message, "System error",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    @Override
    public void playersChanged() {
        String message = "One player has left.";
        JOptionPane.showMessageDialog(new JFrame(), message);
        StringBuilder sb = new StringBuilder("Scores:  ");
        for (Player p : game.getPlayers()) {
            sb.append(p.toString() + ": " + p.getScore() + "   ");
        }
        scoresLabel.setText(sb.toString());
    }

    @Override
    public void allPlayersLeft() {
        String message = "The other players have left, please start a new game.";
        JOptionPane.showMessageDialog(new JFrame(), message);
        System.exit(0);
    }

    /**
     * Checks if the given string represents a valid integer. Returns false if the given integer is 0.
     *
     * @param s the given string
     */
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        if (s.trim().equals("0")) return false;
        return true;
    }
}
