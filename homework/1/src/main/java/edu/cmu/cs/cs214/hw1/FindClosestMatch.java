package edu.cmu.cs.cs214.hw1;

import javax.print.Doc;
import java.io.IOException;

/**
 * 15-214 HW1 Andrew ID: yiyir
 *
 * @author Yiyi Ren
 */
public class FindClosestMatch {
    /**
     * Takes a list of URLs on the command line and prints the two URL whose web
     * pages have the highest cosine similarity. Prints a stack trace if any of
     * the URLs are invalid, or if an exception occurs while reading data from
     * the URLs.
     *
     * @param args command line input of an arbitrary number of URLs; the
     *             arguments should not be null and the length of the arguments
     *             array should be greater than one; the arguments should be
     *             valid URLs
     * @throws IOException if an I/O exception occurs
     */
    public static void main(String[] args) throws IOException {
        try {
            int numDocs = args.length;
            Document[] docs = new Document[numDocs];
            for (int i = 0; i < numDocs; i++) {
                docs[i] = new Document(args[i]);
            }
            Document a = docs[0];
            Document b = docs[1];
            double max = a.cosSim(b);
            for (int i = 0; i < numDocs; i++) {
                for (int j = i + 1; j < numDocs; j++) {
                    double similarity = docs[i].cosSim(docs[j]);
                    if (similarity > max) {
                        max = similarity;
                        a = docs[i];
                        b = docs[j];
                    }
                }
            }
            System.out.println(a.toString());
            System.out.println(b.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}