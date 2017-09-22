package edu.cmu.cs.cs214.hw3;

import edu.cmu.cs.cs214.hw2.expression.Expression;
import edu.cmu.cs.cs214.hw2.expression.VariableExpression;
import edu.cmu.cs.cs214.hw2.termcalc.ExpressionMaker;
import edu.cmu.cs.cs214.hw2.termcalc.MyExpressionMaker;
import edu.cmu.cs.cs214.hw2.termcalc.TerminalCalculator;

import java.util.*;

/**
 * Cryptarithm -- a class representing cryptarithms.
 */
public class Cryptarithm {
    /**
     * Instance variable left: to store the left-hand side expression.
     */
    private StringBuilder left = new StringBuilder();
    /**
     * Instance variable right: to store the right-hand side expression.
     */
    private StringBuilder right = new StringBuilder();
    /**
     * Instance variable map: to store the <letter, VariableExpression> pair.
     */
    private Map<Character, VariableExpression> map = new HashMap<>();
    /**
     * Instance variable firstLetters: to store the first letters of each word.
     */
    private Set<Character> firstLetters = new HashSet<>();

    /**
     * Constructor method. Throws IllegalArgumentException if the input arguments are not valid.
     *
     * @param args the single String array representing the cryptarithm.
     *             The input strings must only contain uppercase letters(A-Z,no more than 10 letters in total)
     *             or "+"/"-"/"*"/"="
     */
    public Cryptarithm(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException();
        }
        Set<Character> set = new HashSet<>();
        boolean isLeft = true;
        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0 && args[i].matches("[A-Z ]+")) {
                firstLetters.add(args[i].charAt(0));
                for (Character letter : args[i].toCharArray()) {
                    set.add(letter);
                }
                if (isLeft) {
                    left.append(args[i]);
                } else {
                    right.append(args[i]);
                }

            } else if (i % 2 != 0 && (args[i].equals("+") || args[i].equals("-") || args[i].equals("*") || args[i].equals("="))) {

                if (args[i].equals("=")) {
                    isLeft = false;
                } else {
                    if (isLeft) {
                        left.append(args[i]);
                    } else {
                        right.append(args[i]);
                    }
                }

            } else {
                throw new IllegalArgumentException();
            }


        }
        if (set.size() > 10 || isLeft || right.toString().isEmpty()) throw new IllegalArgumentException();
        for (Character c : set) {
            map.put(c, new VariableExpression(c.toString()));

        }
    }

    /**
     * Gets the map that stores the <letter, VariableExpression> pair
     *
     * @return the map that stores the <letter, VariableExpression> pair
     */
    public Map<Character, VariableExpression> getMap() {
        return map;
    }

    /**
     * Checks if the cryptarithm is valid or not.
     *
     * @return true if the left-hand side and right-hand side expressions are equal to each other in value;
     * If any first letter of input words is assigned a value of 0, return false.
     */
    public boolean isValid() {
        for (Character ch : firstLetters) {
            if (map.get(ch).eval() == 0.0) {
                return false;
            }
        }

        StringBuilder leftExpBuilder = new StringBuilder();

        for (Character x : this.left.toString().toCharArray()) {
            if (Character.isAlphabetic(x)) {
                leftExpBuilder.append((int) map.get(x).eval());
            } else {
                leftExpBuilder.append(x);

            }
        }
        StringBuilder rightExpBuilder = new StringBuilder();
        for (Character x : this.right.toString().toCharArray()) {
            if (Character.isAlphabetic(x)) {
                rightExpBuilder.append((int) map.get(x).eval());
            } else {
                rightExpBuilder.append(x);
            }
        }
        ExpressionMaker expressionMaker = new MyExpressionMaker();
        TerminalCalculator calculator = new TerminalCalculator(expressionMaker);
        Expression leftExpression = calculator.run(leftExpBuilder.toString());
        Expression rightExpression = calculator.run(rightExpBuilder.toString());
        return Double.compare(leftExpression.eval(), rightExpression.eval()) == 0;
    }


}
