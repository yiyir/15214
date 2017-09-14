package edu.cmu.cs.cs214.hw2.termcalc;

import edu.cmu.cs.cs214.hw2.expression.AbsoluteExpression;
import edu.cmu.cs.cs214.hw2.expression.AdditionExpression;
import edu.cmu.cs.cs214.hw2.expression.DivisionExpression;
import edu.cmu.cs.cs214.hw2.expression.ExponentiationExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.MultiplicationExpression;
import edu.cmu.cs.cs214.hw2.expression.NegationExpression;
import edu.cmu.cs.cs214.hw2.expression.NumberExpression;
import edu.cmu.cs.cs214.hw2.expression.SubtractionExpression;

public class MyExpressionMaker implements ExpressionMaker {

    @Override
    public Expression sumExpression(Expression addend1, Expression addend2) {
        return new AdditionExpression(addend1, addend2);
    }

    @Override
    public Expression differenceExpression(Expression op1, Expression op2) {
        return new SubtractionExpression(op1, op2);
    }

    @Override
    public Expression productExpression(Expression factor1, Expression factor2) {
        return new MultiplicationExpression(factor1, factor2);
    }

    @Override
    public Expression divisionExpression(Expression dividend, Expression divisor) {
        return new DivisionExpression(dividend, divisor);
    }

    @Override
    public Expression exponentiationExpression(Expression base, Expression exponent) {
        return new ExponentiationExpression(base, exponent);
    }

    @Override
    public Expression negationExpression(Expression operand) {
        return new NegationExpression(operand);
    }

    @Override
    public Expression absoluteValueExpression(Expression value) {
        return new AbsoluteExpression(value);
    }

    @Override
    public Expression numberExpression(double value) {
        return new NumberExpression(value);
    }

}
