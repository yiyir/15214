package edu.cmu.cs.cs214.hw2.operator;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubtractionOperatorTest {
    private BinaryOperator f = new SubtractionOperator();
    private double x = -5.0;
    private double y = 2.0;
    private double z = 0.0;

    @Test
    public void test() {
        assertTrue(f.apply(x, y) == -7.0);
        assertTrue(f.apply(x, z) == -5.0);
    }

    @Test
    public void testToString() {
        assertTrue(f.toString() == "-");
    }

}
