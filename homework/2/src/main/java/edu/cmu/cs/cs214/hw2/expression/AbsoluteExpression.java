package edu.cmu.cs.cs214.hw2.expression;

public class AbsoluteExpression implements Expression {
    /**
     * Instance variable value to store the Expression passed to the constructor.
     */
    private Expression value;
    /**
     * Constructor method that takes in an Expression to construct an AbsoluteExpression object.
     * @param value the Expression that the constructor takes in
     */
    public AbsoluteExpression(Expression value) {
        this.value = value;
    }

    @Override
    public double eval() {
        return Math.abs(value.eval());
    }

    @Override
    public String toString() {
        return "|" + value.toString() + "|";
    }

}
