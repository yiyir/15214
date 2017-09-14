package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExponentiationOperatorTest {
    private BinaryOperator d = new ExponentiationOperator();
    private double x = -5.0;
    private double y = 2.0;
    private double z = 0.0;

    @Test
    public void testApply() {

        assertTrue(d.apply(x, y) == 25.0);
        assertTrue(d.apply(x, z) == 1.0);
    }

    @Test
    public void testToString() {
        assertTrue(d.toString() == "^");
    }
}
