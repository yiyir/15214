package edu.cmu.cs.cs214.rec13;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.*;

import static java.math.BigInteger.ONE;

/**
 * Prints the first 20 Mersenne primes.  A Mersenne prime is a prime
 * number of the form 2^p - 1, for some p that is itself prime.
 */
public class ParallelMersennePrimes2 {
    private static final int LIMIT = 20; // Number of Mersenne primes to find
    private static final BigInteger TWO = new BigInteger("2");
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(16);
    private static final ArrayList<BigInteger> resultList = new ArrayList<>();
    private static final BlockingQueue<Future<BigInteger>> queue = new LinkedBlockingQueue<>();


    public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime(); // Record the start time for simple benchmarking
        BigInteger p = TWO;
        threadPool.submit(() -> {
                    while (resultList.size() < LIMIT) {
                        try {
                            BigInteger result = queue.take().get();
                            if (result != null) {
                                resultList.add(result);
                                System.out.println(result);
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
        );

        // Do a few tasks at the same time.
        while (resultList.size() < LIMIT) {
            BigInteger finalP = p;
            queue.put(threadPool.submit(() -> {
                BigInteger candidate = TWO.pow(finalP.intValueExact()).subtract(ONE); // A candidate Mersenne number
                if (candidate.isProbablePrime(50)) { // this is an expensive operation
                    return candidate;
                }
                return null;
            }));
            p = p.nextProbablePrime(); // Advances to the next prime p

        }
        // After finishing the previous tasks, print out the time used.
        double totalTime = (System.nanoTime() - startTime) / 1_000_000_000.0;
        System.out.printf("It took %.2f seconds to find %d Mersenne primes.\n", totalTime, LIMIT);
        System.exit(0); // Forces background threads to quit, close up all threads
    }
}
