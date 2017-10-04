package edu.cmu.cs.cs214.hw3;

import edu.cmu.cs.cs214.hw2.expression.VariableExpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SolveCryptarithm -- a class that generates and prints all solutions to a crytarithm.
 */
public class SolveCryptarithm {
    private static final Integer[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    /**
     * Generates and prints all solutions to a crytarithm.
     *
     * @param args the single String array representing the cryptarithm.
     */
    public static void main(String args[]) {
        Cryptarithm sb = new Cryptarithm(args);
        Map<Character, VariableExpression> map = sb.getMap();
        List<List<Integer>> result = getSubsets(map.size());
        List<Map<Character, Integer>> solutions = getSolutions(result, sb, map);
        System.out.println(solutions.size() + " " + "Solution(s):");
        for (Map<Character, Integer> one : solutions) {
            System.out.println(one);
        }
    }

    /**
     * Gets all subsets of size n of all 10 digits.
     *
     * @param n the subset size
     * @return all subsets of size n of all 10 digits.
     */
    private static List<List<Integer>> getSubsets(int n) {
        List<List<Integer>> result = new ArrayList<>();
        for (int bitVec = 0; bitVec < 1 << 10; bitVec++) { // 1 << n is 2^n
            if (Integer.bitCount(bitVec) == n) {
                List<Integer> subset = new ArrayList<>();
                int index = 0;
                int num = bitVec;
                while (num != 0) {
                    if ((1 & num) == 1) {
                        subset.add(digits[index]);
                    }
                    index++;
                    num = num >> 1;
                }
                result.add(subset);
            }
        }
        return result;
    }

    /**
     * Gets all the valid solutions for the given Cryptarithm.
     *
     * @param result all subsets of size n of all 10 digits
     * @param cryp   the given Cryptarithm
     * @param map    the map that stores the <letter, VariableExpression> pair
     * @return a list of all the valid solutions for the given Cryptarithm.
     */
    private static List<Map<Character, Integer>> getSolutions(List<List<Integer>> result, Cryptarithm cryp, Map<Character, VariableExpression> map) {
        List<Map<Character, Integer>> solutions = new ArrayList<>();
        for (List<Integer> subset : result) {
            CommandInterface each = new PermutationCommand(subset);
            List<List<Integer>> permuatations = each.generate();
            for (List<Integer> permutation : permuatations) {
                int i = 0;
                for (VariableExpression exp : map.values()) {
                    exp.store(permutation.get(i));
                    i++;
                }
                if (cryp.isValid()) {

                    Map<Character, Integer> solution = new HashMap<>();
                    for (Character c : map.keySet()) {
                        solution.put(c, (int) map.get(c).eval());
                    }
                    solutions.add(solution);
                }
            }
        }
        return solutions;
    }
}
