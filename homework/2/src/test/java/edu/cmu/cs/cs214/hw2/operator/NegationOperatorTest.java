package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.Test;

public class NegationOperatorTest {
    private UnaryOperator e = new NegationOperator();
    private double x = -5.0;
    private double z = 0.0;

    @Test
    public void testApply() {
        assertTrue(e.apply(x) == 5.0);
        assertTrue(e.apply(z) == 0.0);
    }

    @Test
    public void testToString() {
        assertTrue(e.toString() == "neg");
    }

}
