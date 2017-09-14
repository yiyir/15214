package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.Test;

public class MultiplicationOperatorTest {
    private BinaryOperator g = new MultiplicationOperator();
    private double x = -5.0;
    private double y = 2.0;
    private double z = 0.0;

    @Test
    public void testApply() {

        assertTrue(g.apply(x, y) == -10.0);

        assertTrue(g.apply(x, z) == 0.0);

    }

    @Test
    public void testToString() {

        assertTrue(g.toString() == "x");

    }

}
