package edu.cmu.cs.cs214.hw3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Permutations -- an implementation of permutation generator using the Iterator Pattern.
 *
 * @param <E> items to be permuted
 */
public class Permutations<E> implements Iterable<List<E>> {
    /**
     * Instance variable to store the resulting permutations.
     */
    private List<List<E>> permutations = new ArrayList<>();

    /**
     * Constructor method: creates a 'permutation container'.
     *
     * @param set the given set of items to be permuted
     */
    public Permutations(List<E> set) {
        this.generatePermutations(set);
    }

    /**
     * Generates all permutations of the given set.
     *
     * @param set the given set of items to be permuted
     */
    private void generatePermutations(List<E> set) {
        //Use Heap's Algorithm to generate all permutations of the given set
        // and store the results in the field 'result'.
    }

    @Override
    public Iterator<List<E>> iterator() {
        return new PermutationIterator();
    }

    /**
     * Private nested class PermutationIterator: provides iterator objects for the Permutations.
     */
    private class PermutationIterator implements Iterator<List<E>> {
        @Override
        public boolean hasNext() {
            // check if this.result has next element to be iterated.
            return false;
        }

        @Override
        public List<E> next() {
            // return the next element in this.result to be iterated
            return null;
        }
    }

    /**
     * Sample client code: suppose the client wants all the permutations of a set of alphabets {a,b,c}
     * and wants to access each permutation of the generated permutations separately
     *
     * @param args
     */
//    public static void main(String[] args) {
//        List<Character> set = new ArrayList<>();
//        set.add('a');
//        set.add('b');
//        set.add('c');
//        Permutations<Character> client = new Permutations<>(set);
//        Iterator<List<Character>> itr = client.iterator();
//        while (itr.hasNext()) {
//            System.out.println(itr.next());
//        }
//    }
}

