package edu.cmu.cs.cs214.hw2.zerofinder;

import edu.cmu.cs.cs214.hw2.expression.DerivativeExpression;
import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;

/**
 * Finds zeros of arbitrary functions using Newton's method.
 */
public class ZeroFinder {
    /**
     * Returns a zero of the specified function using Newton's method with
     * approxZero as the initial estimate.
     *
     * @param fn         the function whose zero is to be found
     * @param x          the independent variable of the function
     * @param approxZero initial approximation for the zero of the function
     * @param tolerance  how close to zero f(the returned value) has to be
     * @return a value x for which f(x) is "close to zero"(<= tolerance)
     */
    public static double zero(Expression fn, VariableExpression x, double approxZero, double tolerance) {
        DerivativeExpression df = new DerivativeExpression(fn, x);
        double oldValue = x.eval();
        double x0 = approxZero;
        x.store(x0);
        double x1 = 0.0;
        while (Math.abs(fn.eval()) > tolerance) {
            x1 = x0 - fn.eval() / df.eval();
            x.store(x1);
            x0 = x1;
        }
        x.store(oldValue);
        return x1;
    }
}
