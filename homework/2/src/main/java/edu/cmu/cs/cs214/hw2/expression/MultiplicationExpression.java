package edu.cmu.cs.cs214.hw2.expression;

public class MultiplicationExpression implements Expression {
    /**
     * Instance variables to store the Expressions of the factors.
     */
    private Expression factor1;
    private Expression factor2;

    /**
     * Constructor method to construct an MultiplicationExpression object.
     * 
     * @param factor1
     *            the Expression of the first factor that the constructor takes
     *            in
     * @param factor2
     *            the Expression of the second factor that the constructor takes
     *            in
     */
    public MultiplicationExpression(Expression factor1, Expression factor2) {
        this.factor1 = factor1;
        this.factor2 = factor2;
    }

    @Override
    public double eval() {
        return factor1.eval() * factor2.eval();
    }

    @Override
    public String toString() {
        return factor1.toString() + "*" + factor2.toString();
    }

}
