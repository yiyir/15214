package edu.cmu.cs.cs214.hw2.termcalc;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmu.cs.cs214.hw2.expression.AbsoluteExpression;
import edu.cmu.cs.cs214.hw2.expression.DivisionExpression;
import edu.cmu.cs.cs214.hw2.expression.ExponentiationExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.MultiplicationExpression;
import edu.cmu.cs.cs214.hw2.expression.NegationExpression;
import edu.cmu.cs.cs214.hw2.expression.NumberExpression;
import edu.cmu.cs.cs214.hw2.expression.SubtractionExpression;

public class MyExpressionMakerTest {
    private MyExpressionMaker maker = new MyExpressionMaker();
    private NumberExpression a = new NumberExpression(-10.0);
    private AbsoluteExpression b = new AbsoluteExpression(a);
    private NumberExpression i = new NumberExpression(0.0);
    private NumberExpression j = new NumberExpression(2.0);

    @Test
    public void testSumExpression() {
        Expression result = maker.sumExpression(a, i);
        assertTrue(result.eval()==-10.0);
        assertEquals(result.toString(),"(-10.0+0.0)");
    }

    @Test
    public void testDifferenceExpression() {
        Expression result = maker.differenceExpression(a, j);
        assertTrue(result.eval()==-12.0);
        assertEquals(result.toString(),"(-10.0-2.0)");

    }

    @Test
    public void testProductExpression() {
        Expression result = maker.productExpression(a, i);
        assertTrue(result.eval()==0.0);
        assertEquals(result.toString(),"-10.0*0.0");
    }

    @Test
    public void testDivisionExpression() {
        Expression result = maker.divisionExpression(a, j);
        assertTrue(result.eval()==-5.0);
        assertEquals(result.toString(),"-10.0/2.0");
    }

    @Test
    public void testExponentiationExpression() {
        Expression result = maker.exponentiationExpression(a, i);
        assertTrue(result.eval()==1.0);
        assertEquals(result.toString(),"(-10.0)^(0.0)");
    }

    @Test
    public void testNegationExpression() {
        Expression result = maker.negationExpression(a);
        assertTrue(result.eval()==10.0);
        assertEquals(result.toString(),"-10.0*(-1.0)");
    }
    @Test
    public void testAbsoluteValueExpression() {
        Expression result = maker.absoluteValueExpression(a);
        assertTrue(result.eval()==10.0);
        assertEquals(result.toString(),"|-10.0|");
    }
    @Test
    public void testNumberExpressionn() {
        Expression result = maker.numberExpression(8.1);
        assertTrue(result.eval()==8.1);
        assertEquals(result.toString(),"8.1");
    }
}
