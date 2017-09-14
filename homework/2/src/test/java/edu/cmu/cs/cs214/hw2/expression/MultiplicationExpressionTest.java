package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.Test;

public class MultiplicationExpressionTest {
    private NumberExpression a = new NumberExpression(-10.0);
    private AbsoluteExpression b = new AbsoluteExpression(a);
    private NegationExpression c = new NegationExpression(b);
    private DivisionExpression e = new DivisionExpression(c, b);
    private NumberExpression j = new NumberExpression(2.0);
    private MultiplicationExpression g = new MultiplicationExpression(e, j);

    @Test
    public void testEval() {
        assertTrue(g.eval() == -2.0);
    }

    @Test
    public void testToString() {
        assertEquals(g.toString(), "|-10.0|*(-1.0)/|-10.0|*2.0");
    }
}
