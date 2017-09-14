package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdditionOperatorTest {
    private BinaryOperator b = new AdditionOperator();
    private double x = -5.0;
    private double y = 2.0;
    private double z = 0.0;

    @Test
    public void testApply() {
        assertTrue(b.apply(x, y) == -3.0);
        assertTrue(b.apply(x, z) == -5.0);
    }

    @Test
    public void testToString() {
        assertTrue(b.toString() == "+");
    }

}
