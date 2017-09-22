/**
 *
 */
package edu.cmu.cs.cs214.hw3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TMPPermutationGenerator -- an implementation of permutation generator using the template method pattern.
 *
 * @author yiyir
 */
class TMPPermutationGenerator<E> extends AbstractGenerator {

    private Set<List<E>> result = new HashSet<>();

    /**
     * Subclass constructor.
     *
     * @param set the input set of items
     */
    public TMPPermutationGenerator(List<E> set) {
        super(set);
    }

    @Override
    public void convertFormat() {

        // Call super.generatePermutations();
        // Convert the resulting permutations to the required format and store them in the field 'result'.
    }

    /**
     * Returns the resulting generated permutations in the required format of the client.
     *
     * @return the resulting permutations in the required format of the client
     */
    public Set<List<E>> returnResult() {
        return result;
    }

    /**
     * Sample client code: suppose the client wants all the permutations of a set of alphabets {a,b,c}
     * and wants the resulting permutations to be stored in a set of lists.
     *
     * @param args
     */
//    public static void main(String[] args) {
//        List<Character> set = new ArrayList<>();
//        set.add('a');
//        set.add('b');
//        set.add('c');
//        TMPPermutationGenerator<Character> client = new TMPPermutationGenerator<>(set);
//        client.convertFormat();
//        Set<List<Character>> clientResult = client.returnResult();
//        System.out.println(clientResult);
//    }

}

