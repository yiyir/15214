package edu.cmu.cs.cs214.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * PermutationCommand -- an implementation of permutation generator using
 * command design pattern.
 *
 * @author yiyir
 */
public class PermutationCommand<E> implements CommandInterface<E> {
    /**
     * Instance variable set: to store the set of items to be permuted.
     */
    private List<E> set;

    /**
     * Constructor method.
     *
     * @param set the set of items to be permuted.
     */
    public PermutationCommand(List<E> set) {
        this.set = set;
    }

    /**
     * Generates all permutations of a given set.
     *
     * @return a list of all the permutations that are generated;
     * Return null if the given list is null or empty.
     */
    @Override
    public List<List<E>> generate() {
        if (set == null || set.size() == 0) return null;
        List<List<E>> result = new ArrayList<>();
        this.helper(this.set, set.size(), result);
        return result;
    }

    /**
     * Helps to generate all permutations of a given set.
     *
     * @param set    the set of items to be permuted
     * @param n      the number of elements in the set
     * @param result the resulting list of all the permutations
     */
    private void helper(List<E> set, int n, List<List<E>> result) {
        if (n == 1) {
            result.add(new ArrayList<E>(set));
        } else {
            for (int i = 0; i < n - 1; i++) {
                helper(set, n - 1, result);
                if (n % 2 == 0) {
                    swap(set, i, n - 1);
                } else {
                    swap(set, 0, n - 1);
                }
            }
            helper(set, n - 1, result);
        }

    }

    /**
     * Swaps the two elements in the given list.
     *
     * @param set the target list
     * @param a   the index of the element to be swapped
     * @param b   the index of the element to be swapped
     */
    private void swap(List<E> set, int a, int b) {
        E tmp = set.get(a);
        set.set(a, set.get(b));
        set.set(b, tmp);
    }

    /**
     * Sample client code: suppose the client wants all the permutations of a set of alphabets {a,b,c}
     * and wants to print out all the permutations
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Character> set = new ArrayList<>();
        set.add('a');
        set.add('b');
        set.add('c');
        CommandInterface<Character> client = new PermutationCommand<>(set);
        List<List<Character>> clientResult = client.generate();
        System.out.println(clientResult);
    }
}
