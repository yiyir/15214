package edu.cmu.cs.cs214.hw4.gui;

import edu.cmu.cs.cs214.hw4.core.Scrabble;
import edu.cmu.cs.cs214.hw4.core.ScrabbleImpl;
import edu.cmu.cs.cs214.hw4.core.gameelements.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.*;

public class startScrabble extends JPanel {
    /**
     * Horizontal and vertical distance between neighboring player windows in
     * pixels.
     */
    private static final int CHAT_WINDOW_POS_OFFSET = 30;
    /**
     * The JFrame from which this panel is established.
     */
    private JFrame parentFrame;
    /**
     * The ids/names of the players in this game
     */
    private final List<String> ids;
    /**
     * The logistics/core corresponding to the gui.
     */
    private final Scrabble game = new ScrabbleImpl();

    public startScrabble(JFrame frame) {
        this.parentFrame = frame;
        this.ids = new ArrayList<>();
        JLabel playerLabel = new JLabel("Player id: ");
        final JTextField idText = new JTextField(20);
        JButton addPlayerButton = new JButton("Add player");
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());
        startPanel.add(playerLabel, BorderLayout.WEST);
        startPanel.add(idText, BorderLayout.CENTER);
        startPanel.add(addPlayerButton, BorderLayout.EAST);
        ActionListener newPlayerListener = e -> {
            String id = idText.getText().trim();
            if (!id.isEmpty() && !ids.contains(id)) {
                ids.add(id);
            }
            idText.setText("");
            idText.requestFocus();
        };
        addPlayerButton.addActionListener(newPlayerListener);
        idText.addActionListener(newPlayerListener);
        JButton createButton = new JButton("Start new game");
        createButton.addActionListener(e -> {
            if (ids.size() < 2) {
                String message = "The number of players should be more than 2, please add more players.";
                JOptionPane.showMessageDialog(new JFrame(), message, "Warning",
                        JOptionPane.WARNING_MESSAGE);
                idText.setText("");
                idText.requestFocus();
            } else if (ids.size() > 4) {
                String message = "The number of players should be less than 4, please re-enter the ids.";
                JOptionPane.showMessageDialog(new JFrame(), message, "Warning",
                        JOptionPane.WARNING_MESSAGE);
                ids.clear();
                idText.setText("");
                idText.requestFocus();
            } else {
                startNewGame();
            }
        });
        setLayout(new BorderLayout());
        add(startPanel, BorderLayout.NORTH);
        add(createButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Starts a new game, opening one window for each player.
     */
    private void startNewGame() {
        parentFrame.dispose();
        parentFrame = null;
        Scrabble game = new ScrabbleImpl();
        for (String id : ids) {
            game.addPlayer(new Player(id));
        }
        // Creates a new window for each player.
        int pos = 0;
        for (Player player : game.getPlayers()) {
            JFrame frame = new JFrame("Scrabble Player -- " + player.toString());
            PlayerPanel playerPanel = new PlayerPanel(game, player);
            frame.add(playerPanel);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    String message = "Do you want to leave the game?";
                    int option = JOptionPane.showConfirmDialog(new JFrame(), message);
                    if (option == YES_OPTION) {
                        game.removeGameChangeListener(playerPanel);
                        frame.dispose();
                        game.removePlayer(player);
                    }
                }
            });
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.pack();
            frame.setLocation(pos, pos);
            pos += CHAT_WINDOW_POS_OFFSET;
            frame.setResizable(true);
            frame.setVisible(true);
        }
        game.startNewTurn();
    }
}
