package edu.cmu.cs.cs214.rec13;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;

/**
 *  Prints the first 20 Mersenne primes.  A Mersenne prime is a prime
 *  number of the form 2^p - 1, for some p that is itself prime.
 *  This implementation iterates through increasingly large primes p,
 *  computing and checking the primality of 2^p - 1 for each prime p until
 *  we have found 20 such primes.
 */
public class SequentialMersennePrimes {
    private static final int LIMIT = 20; // Number of Mersenne primes to find
    private static final BigInteger TWO = new BigInteger("2");

    public static void main(String[] args) {
        long startTime = System.nanoTime(); // Record the start time for simple benchmarking

        int count = 0; // Number of Mersenne primes found so far
        BigInteger p = TWO; // Start iterating from the smallest prime
        while (count < LIMIT) {
            BigInteger candidate = TWO.pow(p.intValueExact()).subtract(ONE); // A candidate Mersenne number
            if (candidate.isProbablePrime(50)) { // this is an expensive operation
                count++;  // (probably) found a Mersenne prime!
                System.out.println(candidate);
            }
            p = p.nextProbablePrime(); // Advances to the next prime p
        }

        double totalTime = (System.nanoTime() - startTime) / 1_000_000_000.0;
        System.out.printf("It took %.2f seconds to find %d Mersenne primes.\n", totalTime, LIMIT);

    }
}
