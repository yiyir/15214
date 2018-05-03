package edu.cmu.cs.cs214.rec13;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.math.BigInteger.ONE;

/**
 * Prints the first 20 Mersenne primes.  A Mersenne prime is a prime
 * number of the form 2^p - 1, for some p that is itself prime.
 */
public class ParallelMersennePrimes1 {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(30);
    private static final int LIMIT = 20; // Number of Mersenne primes to find
    private static final BigInteger TWO = new BigInteger("2");
    private static final ArrayList<BigInteger> resultList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime(); // Record the start time for simple benchmarking
        // TODO: Use Java concurrency tools to improve the performance of Mersenne prime-finding here.
        BigInteger p = TWO; // Start iterating from the smallest prime
        while (resultList.size() < LIMIT) {
            BigInteger finalP =p;
            threadPool.submit(() -> {
                BigInteger candidate = TWO.pow(finalP.intValueExact()).subtract(ONE); // A candidate Mersenne number
                if (candidate.isProbablePrime(50)) { // this is an expensive operation
                    if(resultList.size() < LIMIT) {
                        System.out.println(candidate);
                        resultList.add(candidate);
                    }
                }
            });
            p = p.nextProbablePrime(); // Advances to the next prime p
        }
        double totalTime = (System.nanoTime() - startTime) / 1_000_000_000.0;
        System.out.printf("It took %.2f seconds to find %d Mersenne primes.\n", totalTime, LIMIT);
//        for(BigInteger bigInteger:resultList){
//            System.out.println(bigInteger);
//        }

        System.exit(0); // Forces background threads to quit, also

    }

}
