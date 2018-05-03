package edu.cmu.cs.cs214.rec08.gui;

import edu.cmu.cs.cs214.rec08.core.TicTacToeImpl;
import edu.cmu.cs.cs214.rec08.samples.chat.gui.SimpleChatClient;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //add frame and set its closing operation
            JFrame frame = new JFrame("Start a Tic-Tac-Toe game");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.add(new TicTacToePanel(new TicTacToeImpl()));
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
