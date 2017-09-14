package edu.cmu.cs.cs214.hw2.zerofinder;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.NumberExpression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;
import edu.cmu.cs.cs214.hw2.termcalc.ExpressionMaker;
import edu.cmu.cs.cs214.hw2.termcalc.MyExpressionMaker;

public class ZeroFinderTest {

    @Test
    public void test() {
        ExpressionMaker maker = new MyExpressionMaker();
        VariableExpression var = new VariableExpression("x");
        NumberExpression number = new NumberExpression(2.0);
        Expression fn = maker.exponentiationExpression(var, number);
        fn = maker.differenceExpression(fn, number);
        double result = ZeroFinder.zero(fn, var, 1.0, 0.01);
        assertTrue(result < 1.42 && result > 1.41);
    }

}
