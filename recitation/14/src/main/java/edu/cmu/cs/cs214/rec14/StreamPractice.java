package edu.cmu.cs.cs214.rec14;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice {

    /**
     * 0.  Returns the number of lines in file. (an example)
     */
    public static int lineCountRegular(String fileName) throws IOException {
        int result = 0;
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNextLine()) {
                result++;
                sc.nextLine();
            }
        }
        return result;
    }

    /**
     * 0. This solution is an example solution for #0.
     */
    public static int lineCountStream(String fileName) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            return (int) lines.count();
        }
    }

    /**
     * Returns whether an input string is a palindrome.  This helper
     * function will be useful for the next method.
     */
    private static boolean isPalindrome(String s) {
        int length = s.length();
        for (int i = 0, j = length - 1; i < length / 2; i++, j--)
            if (s.charAt(i) != s.charAt(j))
                return false;
        return true;
    }

    /**
     * 1. Returns palindromes in word list in original order.
     * <p>
     * HINT:
     * <p>
     * You can filter the contents of a stream:
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-
     * <p>
     * You can also turn a stream into a Collection using Collectors
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
     */
    public static List<String> palindromesRegular(String wordListFileName) throws IOException {
        List<String> result = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(wordListFileName))) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (isPalindrome(line))
                    result.add(line);
            }
        }
        return result;
    }

    /**
     * 1.  Returns palindromes in word list in original order.
     */
    public static List<String> palindromesStream(String wordListFileName) throws IOException {
        // TODO: Implement me, matching the above behavior but using Streams.
        Stream<String> lines = Files.lines(Paths.get(wordListFileName));
        return lines.filter(StreamPractice::isPalindrome).collect(Collectors.toList());
    }

    /**
     * 2. Returns the number of palindromes in word list.
     */
    public static int palindromeCountRegular(String wordListFileName) throws IOException {
        int result = 0;
        try (Scanner sc = new Scanner(new File(wordListFileName))) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (isPalindrome(line)) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 2. Returns the number of palindromes in word list.
     */
    public static int palindromeCountStream(String wordListFileName) throws IOException {
        // TODO: Implement me, matching the above behavior but using Streams
        Stream<String> lines = Files.lines(Paths.get(wordListFileName));
        return (int)lines.filter(StreamPractice::isPalindrome).count();
    }

    /**
     * 3. Returns the longest palindrome in word list.
     * HINT:
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#max-java.util.Comparator-
     */
    public static String longestPalindromeRegular(String wordListFileName) throws IOException {
        String longest = "";
        try (Scanner sc = new Scanner(new File(wordListFileName))) {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (isPalindrome(line) && line.length() > longest.length()) {
                    longest = line;
                }
            }
        }
        return longest;
    }

    /**
     * 3. Returns the longest palindrome in word list.
     */
    public static String longestPalindromeStream(String wordListFileName) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(wordListFileName));
        return lines.filter(StreamPractice::isPalindrome).max(Comparator.comparingInt(String::length)).get();
    }

    /**
     * 4. Returns the sum of all integers in the given collection
     * satisfying the given criterion.
     * <p>
     * HINT:
     * <p>
     * You can get a stream from a collection using:
     * https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#stream--
     * <p>
     * You can also combine a stream's elements into a single value using:
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#reduce-T-java.util.function.BinaryOperator-
     */
    public static int sumAllSatisfyingRegular(Collection<Integer> numbers, Predicate<Integer> criterion) {
        int result = 0;
        for (int num : numbers)
            if (criterion.test(num))
                result += num;
        return result;
    }

    /**
     * 4. Returns the sum of all integers in the given collection
     * satisfying the given criterion.
     */
    public static int sumAllSatisfyingStream(Collection<Integer> numbers, Predicate<Integer> criterion) {
        // TODO: Implement me, matching the above behavior but using Streams.
        Stream<Integer> stream = numbers.stream();
        stream.reduce();
        return 0;
    }


    /**
     * 5. Returns a frequency table describing the given collection.
     * HINTS:
     * <p>
     * You can merge a stream into a map using this Collector, which is analogous to Map.merge:
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toMap-java.util.function.Function-java.util.function.Function-java.util.function.BinaryOperator-
     * Using this Collector requires three functions. While you could use lambdas for all three, the first
     * can be found in java.util.function.Function, and the third can be found in java.lang.Integer.
     */
    public static <E> Map<E, Integer> frequencyTableRegular(Collection<E> coll) {
        Map<E, Integer> result = new HashMap<>();
        for (E e : coll)
            result.merge(e, 1, Integer::sum);
        return result;
    }

    /**
     * 5. Returns a frequency table describing the given collection.
     */
    public static <E> Map<E, Integer> frequencyTableStream(Collection<E> coll) {
        // TODO: Implement me, matching the above behavior but using Streams.
        return null;
    }

    /**
     * 6. Returns a list consisting of all the characters in all the given
     * strings, in order.
     * <p>
     * HINT:
     * Use flatMap and friends to generate multiple elements from one element
     * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMap-java.util.function.Function-
     */
    public static List<Character> charactersInStringsRegular(String... strings) {
        List<Character> result = new ArrayList<>();
        for (String s : strings)
            for (char c : s.toCharArray())
                result.add(c);
        return result;
    }

    /**
     * 6. Returns a list consisting of all the characters in all the given
     * strings, in order.
     */
    public static List<Character> charactersInStringsStream(String... strings) {
        // TODO: Implement me, matching the above behavior but using Streams.
        return null;
    }

    /**
     * An example of infinite Streams.
     */
    public static Stream<BigInteger> positives() {
        BigInteger[] nextVal = {BigInteger.ONE};

        return Stream.generate(() -> {
            BigInteger result = nextVal[0];
            nextVal[0] = result.add(BigInteger.ONE);
            return result;
        });
    }

    /**
     * 7. Returns an infinite stream of the Fibonacci numbers.
     */
    public static Stream<BigInteger> fibonacciNumbers() {
        BigInteger[] fibs = {BigInteger.ZERO, BigInteger.ONE};
        // TODO: Implement me!
        return null;
    }

    /**
     * 8. Returns the first Fibonacci number greater than or equal to
     * the input parameter n.
     */
    public static BigInteger firstFibStartingFrom(BigInteger n) {
        // TODO: Implement me!
        return null;
    }

    /**
     * 9. Returns the whether the input argument n is a Fibonacci number.
     */
    public static boolean isFib(BigInteger n) {
        // TODO: Implement me!
        return false;
    }

    /**
     * 10. Returns the nth Fibonacci number, with 0 being the 0th
     * Fibonacci number.
     */
    public static BigInteger nthFib(int n) {
        // TODO: Implement me!
        return null;
    }
}
