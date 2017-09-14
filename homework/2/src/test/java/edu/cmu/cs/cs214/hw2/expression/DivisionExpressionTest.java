package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.Test;

public class DivisionExpressionTest {
    private NumberExpression a = new NumberExpression(-10.0);
    private AbsoluteExpression b = new AbsoluteExpression(a);
    private NegationExpression c = new NegationExpression(b);
    private DivisionExpression e = new DivisionExpression(c, b);

    @Test
    public void testEval() {
        assertTrue(e.eval() == -1.0);
    }

    @Test
    public void testToString() {
        assertEquals(e.toString(), "|-10.0|*(-1.0)/|-10.0|");
    }

}
