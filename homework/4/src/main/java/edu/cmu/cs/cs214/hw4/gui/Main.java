package edu.cmu.cs.cs214.hw4.gui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Start a Scrabble game");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.add(new startScrabble(frame));
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
