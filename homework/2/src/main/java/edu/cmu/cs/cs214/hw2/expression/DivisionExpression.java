package edu.cmu.cs.cs214.hw2.expression;

public class DivisionExpression implements Expression {
    /**
     * Instance variables dividend and divisor to store the Expressions of the
     * dividend and the divisor.
     */
    private Expression dividend;
    private Expression divisor;

    /**
     * Constructor method to construct a DivisionExpression object.
     * 
     * @param dividend
     *            the Expression of the dividend that the constructor takes in
     * @param divisor
     *            the Expression of the divisor that the constructor takes in
     */
    public DivisionExpression(Expression dividend, Expression divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }

    @Override
    public double eval() {
        return dividend.eval() / divisor.eval();
    }

    @Override
    public String toString() {
        return dividend.toString() + "/" + divisor.toString();
    }

}
