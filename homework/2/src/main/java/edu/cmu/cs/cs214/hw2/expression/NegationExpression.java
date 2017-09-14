package edu.cmu.cs.cs214.hw2.expression;

public class NegationExpression implements Expression {
    /**
     * Instance variable to store the Expression of the operand.
     */
    private Expression operand;

    /**
     * Constructor method to construct a NegationExpression object.
     * 
     * @param operand
     *            the Expression of the operand that the constructor takes in
     */
    public NegationExpression(Expression operand) {
        this.operand = operand;
    }

    @Override
    public double eval() {
        return operand.eval() * (-1.0);
    }

    @Override
    public String toString() {
        return operand.toString() + "*(-1.0)";
    }

}
