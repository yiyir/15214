package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.Test;

public class DivisionOperatorTest {
    private BinaryOperator c = new DivisionOperator();
    private double x = -5.0;
    private double y = 2.0;
    private double z = 0.0;

    @Test
    public void testApply() {
        assertTrue(c.apply(x, y) == -2.5);
        assertTrue(c.apply(z, y) == 0.0);
    }

    @Test
    public void testToString() {
        assertTrue(c.toString() == "/");
    }
}
