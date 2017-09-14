package edu.cmu.cs.cs214.hw2.operator;

public class AbsoluteOperator implements UnaryOperator {

    @Override
    public double apply(double arg) {

        return Math.abs(arg);
    }

    @Override
    public String toString() {
        return "abs";
    }

}
