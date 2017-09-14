package edu.cmu.cs.cs214.hw2.expression;

public class ExponentiationExpression implements Expression {
    /**
     * Instance variables to store the Expressions of the base and the exponent.
     */
    private Expression base;
    private Expression exponent;

    /**
     * Constructor method to construct an ExponentiationExpression object.
     * 
     * @param base
     *            the Expression of the base that the constructor takes in
     * @param exponent
     *            the Expression of the exponent that the constructor takes in
     */
    public ExponentiationExpression(Expression base, Expression exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    public double eval() {
        return Math.pow(base.eval(), exponent.eval());
    }

    @Override
    public String toString() {
        return "(" + base.toString() + ")" + "^" + "(" + exponent.toString() + ")";
    }

}
