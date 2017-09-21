package edu.cmu.cs.cs214.hw3;

import java.util.ArrayList;
import java.util.List;

/**
 * PermutationCommand -- an implementation of permutation generator using
 * command design pattern.
 * 
 * @author yiyir
 *
 */
public class PermutationCommand<E> implements CommandInterface<E> {
    /**
     * Instance variable set: to store the set of items to be permuted.
     */
    private List<E> set;

    /**
     * Constructor method.
     * 
     * @param set
     */
    public PermutationCommand(List<E> set) {
        this.set = set;
    }

    @Override
    public List<List<E>> generate() {
        List<List<E>> result = new ArrayList<>();
        this.helper(this.set, set.size(), result);
        return result;
    }

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

    private void swap(List<E> set, int a, int b) {
        E tmp = set.get(a);
        set.set(a, set.get(b));
        set.set(b, tmp);
    }
    
}
