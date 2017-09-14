package edu.cmu.cs.cs214.hw2.expression;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmu.cs.cs214.hw2.termcalc.ExpressionMaker;
import edu.cmu.cs.cs214.hw2.termcalc.MyExpressionMaker;

public class VariableExpressionTest {
    private VariableExpression var = new VariableExpression("x");

    @Test
    public void test() {
        ExpressionMaker maker = new MyExpressionMaker();
        var.store(5.0);
        Expression exp = maker.exponentiationExpression(var, new NumberExpression(2.0));
        exp = maker.sumExpression(exp, var);
        assertTrue(exp.eval() == 30.0);
        assertEquals(exp.toString(), "((x)^(2.0)+x)");
        assertEquals(var.name(),"x");
    }

}