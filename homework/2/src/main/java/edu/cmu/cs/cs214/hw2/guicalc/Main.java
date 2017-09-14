package edu.cmu.cs.cs214.hw2.guicalc;

import edu.cmu.cs.cs214.hw2.operator.AbsoluteOperator;
import edu.cmu.cs.cs214.hw2.operator.AdditionOperator;
import edu.cmu.cs.cs214.hw2.operator.BinaryOperator;
import edu.cmu.cs.cs214.hw2.operator.DivisionOperator;
import edu.cmu.cs.cs214.hw2.operator.ExponentiationOperator;
import edu.cmu.cs.cs214.hw2.operator.MultiplicationOperator;
import edu.cmu.cs.cs214.hw2.operator.NegationOperator;
import edu.cmu.cs.cs214.hw2.operator.SubtractionOperator;
import edu.cmu.cs.cs214.hw2.operator.UnaryOperator;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Main program that runs the GUI Calculator
 */
public class Main {
    /**
     * The main method to run the GUI Calculator
     * 
     * @param args
     *            : do not take in any arguments from the command line
     */
    public static void main(String[] args) {
        // TODO: Replace null with your own unary operators. Use a Linked
        // HashSet so operators are displayed in order.
        Set<UnaryOperator> unaryOperators = new LinkedHashSet<>();
        unaryOperators.add(new NegationOperator());
        unaryOperators.add(new AbsoluteOperator());

        // TODO: Replace null with your own binary operators. Use a Linked
        // HashSet so operators are displayed in order
        Set<BinaryOperator> binaryOperators = new LinkedHashSet<>();
        binaryOperators.add(new AdditionOperator());
        binaryOperators.add(new SubtractionOperator());
        binaryOperators.add(new MultiplicationOperator());
        binaryOperators.add(new DivisionOperator());
        binaryOperators.add(new ExponentiationOperator());

        // Run the calculator!
        new GuiCalculator(unaryOperators, binaryOperators);
    }
}
