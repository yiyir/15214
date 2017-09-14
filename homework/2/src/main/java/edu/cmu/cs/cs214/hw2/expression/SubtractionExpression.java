package edu.cmu.cs.cs214.hw2.expression;

public class SubtractionExpression implements Expression {
    /**
     * Instance variables to store the Expressions of the operands.
     */
    private Expression op1;
    private Expression op2;

    /**
     * Constructor method to construct an SubtractionExpression object.
     * 
     * @param op1
     *            the Expression of the first operand that the constructor takes
     *            in
     * @param op2
     *            the Expression of the second operand that the constructor
     *            takes in
     */
    public SubtractionExpression(Expression op1, Expression op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public double eval() {
        return op1.eval() - op2.eval();
    }

    @Override
    public String toString() {
        return "(" + op1.toString() + "-" + op2.toString() + ")";
    }

}
