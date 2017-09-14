package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.Test;

public class NegationExpressionTest {
    private NumberExpression a = new NumberExpression(-10.0);
    private AbsoluteExpression b = new AbsoluteExpression(a);
    private NegationExpression c = new NegationExpression(b);

    @Test
    public void testEval() {
        assertTrue(c.eval() == -10.0);
    }

    @Test
    public void testToString() {
        assertEquals(c.toString(), "|-10.0|*(-1.0)");
    }
}
