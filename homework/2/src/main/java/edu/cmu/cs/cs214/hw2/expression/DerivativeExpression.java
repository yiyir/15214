package edu.cmu.cs.cs214.hw2.expression;

public class DerivativeExpression implements Expression {
    /**
     * Instance variables fn and independentVar to store the Expressions of the
     * function and the variable.
     */
    private Expression fn;
    private VariableExpression independentVar;
    /**
     * Class final variable delta to store the value of delta to compute the
     * derivative.
     */
    private static final double delta = Math.pow(10.0, -9.0);

    /**
     * Creates an expression representing the derivative of the specified
     * function with respect to the specified variable.
     * 
     * @param fn
     *            the function whose derivative this expression represents
     * @param independentVar
     *            the variable with respect to which we're differentiating
     */
    public DerivativeExpression(Expression fn, VariableExpression independentVar) {
        this.fn = fn;
        this.independentVar = independentVar;
    }

    @Override
    public double eval() {
        double oldVar = independentVar.eval();
        double oldVal = fn.eval();
        independentVar.store(oldVar + delta);
        return (fn.eval() - oldVal) / delta;
    }

    @Override
    public String toString() {
        return "d(" + fn.toString() + ")/d" + independentVar.toString();
    }
}
