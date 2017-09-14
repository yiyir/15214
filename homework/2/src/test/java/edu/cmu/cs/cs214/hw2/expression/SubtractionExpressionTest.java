package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubtractionExpressionTest {
    private NumberExpression a = new NumberExpression(-10.0);
    private AbsoluteExpression b = new AbsoluteExpression(a);
    private NumberExpression i = new NumberExpression(0.0);
    private NumberExpression j = new NumberExpression(2.0);
    private NegationExpression c = new NegationExpression(b);
    private DivisionExpression e = new DivisionExpression(c, b);
    private ExponentiationExpression f = new ExponentiationExpression(c, i);
    private MultiplicationExpression g = new MultiplicationExpression(e, j);
    private SubtractionExpression h = new SubtractionExpression(f, g);

    @Test
    public void testEval() {
        assertTrue(h.eval() == 3.0);
    }

    @Test
    public void testToString() {
        assertEquals(h.toString(), "((|-10.0|*(-1.0))^(0.0)-|-10.0|*(-1.0)/|-10.0|*2.0)");
    }
}
