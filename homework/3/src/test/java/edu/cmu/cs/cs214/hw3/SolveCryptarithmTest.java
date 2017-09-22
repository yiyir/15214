package edu.cmu.cs.cs214.hw3;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolveCryptarithmTest {
    private String[] valid1 = {"IJOP", "+", "GH", "=", "JOPH"};
    private String[] valid2 = {"IP", "+", "GH", "*", "I", "=", "JOPH"};
    private String[] valid3 = {"NORTH", "*", "WEST", "=", "SOUTH", "*", "EAST"};

    @Test
    public void testGetSolutions() {
        SolveCryptarithm.main(valid1);
        SolveCryptarithm.main(valid2);
        SolveCryptarithm.main(valid3);

    }

}