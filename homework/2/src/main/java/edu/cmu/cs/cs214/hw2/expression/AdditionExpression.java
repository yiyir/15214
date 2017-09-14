package edu.cmu.cs.cs214.hw2.expression;

public class AdditionExpression implements Expression {
    /**
     * Instance variables to store the Expressions passed to the constructor.
     */
    private Expression addend1;
    private Expression addend2;

    /**
     * Constructor method to construct an AdditionExpression object.
     * 
     * @param addend1
     *            the Expression that the constructor takes in
     * @param addend2
     *            the other Expression that the constructor takes in
     */
    public AdditionExpression(Expression addend1, Expression addend2) {
        this.addend1 = addend1;
        this.addend2 = addend2;
    }

    @Override
    public double eval() {
        return addend1.eval() + addend2.eval();
    }

    @Override
    public String toString() {
        return "(" + addend1.toString() + "+" + addend2.toString() + ")";
    }

}
