package edu.cmu.cs.cs214.rec09.plugin;

import edu.cmu.cs.cs214.rec09.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec09.framework.core.GamePlugin;

import java.util.Random;

public class TicTacToePlugin implements GamePlugin {
    private static final String GAME_NAME = "Tic-Tac-Toe";

    private static final int GRID_WIDTH = 3;
    private static final int GRID_HEIGHT = 3;

    private static final String PLAYER_WON_MSG = "Player %s won!";
    private static final String COMPUTER_WON_MSG = "The computer won!";
    private static final String GAME_TIED_MSG = "The game ended in a tie.";



    private GameFramework framework;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return GRID_WIDTH;
    }

    @Override
    public int getGridHeight() {
        return GRID_HEIGHT;
    }

    @Override
    public void onRegister(GameFramework framework) {
        this.framework = framework;
    }

    @Override
    public void onNewGame() {
        for(int i=0;i<3;i++){
            for(int j = 0;j<3;j++){
                framework.setSquare(i,j,"");
            }
        }

    }

    @Override
    public void onNewMove() {

    }

    @Override
    public boolean isMoveValid(int x, int y) {
        if(framework.getSquare(x,y)==null)return true;
        return false;
    }

    @Override
    public boolean isMoveOver() {
        return true;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        framework.setSquare(x,y,framework.getCurrentPlayer().getSymbol());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String getGameOverMessage() {
        return framework.getCurrentPlayer().getName();
    }

    @Override
    public void onGameClosed() {

    }

}
