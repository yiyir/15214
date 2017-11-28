package edu.cmu.cs.cs214.rec13;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;

/**
 *  Prints the first 20 Mersenne primes.  A Mersenne prime is a prime
 *  number of the form 2^p - 1, for some p that is itself prime.
 */
public class ParallelMersennePrimes2 {
    private static final int LIMIT = 20; // Number of Mersenne primes to find
    private static final BigInteger TWO = new BigInteger("2");

    public static void main(String[] args) {
        long startTime = System.nanoTime(); // Record the start time for simple benchmarking

        // TODO: Use Java concurrency tools to improve the performance of Mersenne prime-finding here.

        double totalTime = (System.nanoTime() - startTime) / 1_000_000_000.0;
        System.out.printf("It took %.2f seconds to find %d Mersenne primes.\n", totalTime, LIMIT);
        System.exit(0); // Forces background threads to quit, also
    }
}
