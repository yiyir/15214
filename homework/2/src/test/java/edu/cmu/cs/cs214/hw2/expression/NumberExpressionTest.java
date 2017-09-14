package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberExpressionTest {
    private NumberExpression a = new NumberExpression(-10.0);

    @Test
    public void testEval() {
        assertTrue(a.eval() == -10.0);
    }

    @Test
    public void testToString() {
        assertEquals(a.toString(), "-10.0");
    }

}
