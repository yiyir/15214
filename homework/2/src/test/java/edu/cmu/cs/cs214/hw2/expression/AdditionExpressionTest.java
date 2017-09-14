package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdditionExpressionTest {
    private NumberExpression a = new NumberExpression(-10.0);
    private AbsoluteExpression b = new AbsoluteExpression(a);
    private NegationExpression c = new NegationExpression(b);
    private AdditionExpression d = new AdditionExpression(b, c);

    @Test
    public void test() {
        assertTrue(d.eval() == 0.0);
    }

    @Test
    public void testToString() {
        assertEquals(d.toString(), "(|-10.0|+|-10.0|*(-1.0))");
    }
}
