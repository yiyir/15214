package edu.cmu.cs.cs214.hw2.expression;

public class NumberExpression implements Expression {
    /**
     * Instance variable to store the value of the number.
     */
    private double value;

    /**
     * Constructor method to construct a NumberExpression object.
     * 
     * @param value
     *            the value of the number that the constructor takes in
     */
    public NumberExpression(double value) {
        this.value = value;
    }

    @Override
    public double eval() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
