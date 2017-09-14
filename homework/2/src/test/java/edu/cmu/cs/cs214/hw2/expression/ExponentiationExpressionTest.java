package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExponentiationExpressionTest {
    private NumberExpression a = new NumberExpression(-10.0);
    private NumberExpression i = new NumberExpression(0.0);
    private AbsoluteExpression b = new AbsoluteExpression(a);
    private NegationExpression c = new NegationExpression(b);
    private ExponentiationExpression f = new ExponentiationExpression(c, i);

    @Test
    public void testEval() {
        assertTrue(f.eval() == 1.0);
    }

    @Test
    public void testToString() {
        assertEquals(f.toString(), "(|-10.0|*(-1.0))^(0.0)");
    }
}
