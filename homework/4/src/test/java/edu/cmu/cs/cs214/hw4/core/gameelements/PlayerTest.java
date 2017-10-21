package edu.cmu.cs.cs214.hw4.core.gameelements;

import edu.cmu.cs.cs214.hw4.core.gameelements.tilebag.TileBag;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PlayerTest {
    private Player player = new Player("lily");
    private TileBag tileBag = new TileBag();

    @Test
    public void getId() {
    }

    @Test
    public void getScore() {
    }

    @Test
    public void getTileInventory() {
    }

    @Test
    public void getRack() {
    }

    @Test
    public void isSkipTurn() {
    }

    @Test
    public void getNumOfSkips() {
    }

    @Test
    public void addScore() {
    }

    @Test
    public void setScore() {
    }

    @Test
    public void setSkipTurn() {
    }

    @Test
    public void reduceNumOfSkips() {
    }

    @Test
    public void updateRack() {
        player.updateRack(tileBag);
        assertEquals(player.getRack().size(), 7);
    }

    @Test
    public void addLetterTiles() {
    }

    @Test
    public void removeLetterTiles() {
    }

    @Test
    public void removeLetterTiles1() {
    }

    @Test
    public void removeSpecialTile() {
    }

    @Test
    public void addSpecialTiles() {
    }

}