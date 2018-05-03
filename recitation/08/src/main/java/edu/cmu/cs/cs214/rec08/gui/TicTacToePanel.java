package edu.cmu.cs.cs214.rec08.gui;

import edu.cmu.cs.cs214.rec08.core.GameChangeListener;
import edu.cmu.cs.cs214.rec08.core.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TicTacToePanel extends JPanel implements GameChangeListener {
    private final TicTacToe game;
    private final List<JButton> squares;
    private JLabel playerLabel;

    public TicTacToePanel(TicTacToe game) {
        this.game = game;
        squares = new ArrayList<>();
        this.setLayout(new BorderLayout());
        playerLabel = new JLabel();
        JButton startButton = new JButton("Start new game!");
        JPanel gridPanel = new JPanel();
        this.add(playerLabel, BorderLayout.NORTH);
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(startButton, BorderLayout.SOUTH);
        startButton.addActionListener(e -> {
            this.game.startNewGame();
        });
        gridPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            final int index = i;
            JButton button = new JButton();
            squares.add(button);
            gridPanel.add(button);
            button.addActionListener(e -> {
                        this.game.playMove(index / 3, index % 3);
                    }

            );
        }

        game.addGameChangeListener(this);
    }

    @Override
    public void squareChanged(int row, int col) {
        TicTacToe.Player player = game.getSquare(row, col);
        if (player == null) {
            squares.get(row * 3 + col).setText("");
        } else {
            squares.get(row * 3 + col).setText(player.name());
        }
    }

    @Override
    public void currentPlayerChanged(TicTacToe.Player player) {
        this.playerLabel.setText("Current player: "+ game.getCurrentPlayer().toString());
    }

    @Override
    public void gameEnded(TicTacToe.Player winner) {
        this.playerLabel.setText("Congratulations: " + winner.toString());
    }
}
