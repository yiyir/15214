package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmu.cs.cs214.hw2.termcalc.ExpressionMaker;
import edu.cmu.cs.cs214.hw2.termcalc.MyExpressionMaker;

public class DerivativeExpressionTest {
    private DerivativeExpression d;

    @Test
    public void test() {
        VariableExpression var = new VariableExpression("x");
        ExpressionMaker maker = new MyExpressionMaker();
        NumberExpression number = new NumberExpression(2.0);
        Expression fn = maker.exponentiationExpression(var, number);
        fn = maker.differenceExpression(fn, number);
        d = new DerivativeExpression(fn, var);
        var.store(3.0);
        assertTrue(d.eval() < 6.1 && d.eval() > 5.9);
        var.store(-2.48);
        assertTrue(d.eval() < -4.9 && d.eval() > -5.0);
        fn = maker.productExpression(fn, var);
        fn = maker.productExpression(fn, number);
        d = new DerivativeExpression(fn, var);
        assertTrue(d.eval() < 33.0 && d.eval() > 32.9);
        assertEquals(d.toString(), "d(((x)^(2.0)-2.0)*x*2.0)/dx");
    }

}
