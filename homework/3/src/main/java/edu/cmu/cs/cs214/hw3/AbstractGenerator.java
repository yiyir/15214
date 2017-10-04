/**
 *
 */
package edu.cmu.cs.cs214.hw3;

import java.util.List;

/**
 * AbstractGenerator -- an abstract class for general permutation generator using template method pattern.
 *
 * @author yiyir
 */
public abstract class AbstractGenerator<E> {
    /**
     * Instance variable to store the input set of items.
     */
    protected List<E> set;
    protected  List<List<E>> result;

    /**
     * Super class constructor.
     *
     * @param set the input set of items
     */
    protected AbstractGenerator(List<E> set) {
        this.set = set;
    }

    /**
     * Invariant method to generate permutations using Heap's Algorithm.
     *
     * @return the resulting list of all the permutations
     */
    protected void generatePermutations() {
        // Use Heap's algorithm to generate all permutations and store them in the field result;
    }

    /**
     * Converts the generated permutations into different formats based on client requirements.
     */
    abstract public void convertFormat();
}


