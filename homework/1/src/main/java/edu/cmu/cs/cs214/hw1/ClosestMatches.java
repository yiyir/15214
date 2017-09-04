package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * 15-214 HW1 Andrew ID: yiyir
 * 
 * @author yiyir
 *
 */
public final class ClosestMatches {
    /**
     * Instance variable matches using PriorityQueue to store the matches and
     * help determine the closest match for each URL
     */
    private Queue<Pair> matches;

    /**
     * Constructor: creates a ClosestMatches object initialized with default
     */
    private ClosestMatches() {
        matches = new PriorityQueue<>(new PairComparator());
    }

    /**
     * Takes an arbitrary number of URLs on the command line and finds the
     * closest matching web page for each of the arguments. Prints pairs of
     * URLs: one for each URL and its closest match, with each pair on its own
     * line. Prints a stack trace if any of the URLs are invalid, or if an
     * exception occurs while reading data from the URLs.
     * 
     * @param args
     *            command line input of an arbitrary number of URLs; the
     *            arguments should not be null and the length of the arguments
     *            array should be greater than one; the arguments should be
     *            valid URLs
     * @throws IOException
     *             if an I/O exception occurs
     */
    public static void main(String[] args) throws IOException {
        try {
            int numDocs = args.length;
            ClosestMatches[] obj = new ClosestMatches[numDocs];
            for (int i = 0; i < numDocs; i++) {
                obj[i] = new ClosestMatches();
            }
            for (int i = 0; i < numDocs; i++) {
                for (int j = i + 1; j < numDocs; j++) {
                    Document one = new Document(args[i]);
                    Document two = new Document(args[j]);
                    double similarity = one.cosSim(two);
                    Pair p = new Pair(two, similarity);
                    Pair q = new Pair(one, similarity);
                    obj[i].matches.offer(p);
                    obj[j].matches.offer(q);
                }
            }
            for (int i = 0; i < numDocs; i++) {
                System.out.println(args[i] + "   " + obj[i].matches.poll().other.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Private nested class to implement the Comparator interface and define a
     * PairComparator.
     * 
     * @author Yiyi Ren
     *
     */
    private class PairComparator implements Comparator<Pair> {

        @Override
        /**
         * Overrides the compare() method and defines a new compare rule
         */
        public int compare(Pair o1, Pair o2) {
            if (o1.simValue > o2.simValue) {
                return -1;
            }
            if (o1.simValue < o2.simValue) {
                return 1;
            }
            if (o1.simValue == o2.simValue) {
                return 0;
            }
            return 0;
        }

    }

    /**
     * Private static nested class to store the URL strings of the match pair
     * and their cosine similarity
     * 
     * @author Yiyi Ren
     *
     */
    private static class Pair {
        /**
         * Instance variable other to store the second Document of the match
         * pair
         */
        private Document other;
        /**
         * Instance variable simValue to store the cosine similarity between the
         * matched documents
         */
        private double simValue;

        /**
         * Constructor: creates a Pair object that matches the current object
         * with Document a and cosine similarity sim
         * 
         * @param a
         *            the second Document of the match pair of documents
         * @param sim
         *            the cosine similarity between the matched documents
         */
        Pair(Document a, double sim) {
            other = a;
            simValue = sim;
        }
    }
}
