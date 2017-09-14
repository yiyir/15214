package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbsoluteOperatorTest {
    private AbsoluteOperator a = new AbsoluteOperator();
    private double x = -5.0;
    private double z = 0.0;

    @Test
    public void testApply() {
        assertTrue(a.apply(x) == 5.0);
        assertTrue(a.apply(z) == 0.0);
    }

    @Test
    public void testToString() {
        assertTrue(a.toString() == "abs");
    }
}
