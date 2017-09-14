package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbsoluteExpressionTest {
    private NumberExpression a = new NumberExpression(-10.0);
    private AbsoluteExpression b = new AbsoluteExpression(a);

    @Test
    public void testEval() {
        assertTrue(b.eval() == 10.0);
    }

    @Test
    public void testToString() {
        assertEquals(b.toString(), "|-10.0|");
    }
}
