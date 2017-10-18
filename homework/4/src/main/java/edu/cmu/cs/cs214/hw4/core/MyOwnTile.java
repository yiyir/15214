package edu.cmu.cs.cs214.hw4.core;

public class MyOwnTile implements SpecialTile {
    @Override
    public void activateFunc(GameBoard board, GameSystem game) {
        game.getCurrentPlayer().setSkipTurn();
    }

    @Override
    public int getPrice() {
        return 20;
    }
}
