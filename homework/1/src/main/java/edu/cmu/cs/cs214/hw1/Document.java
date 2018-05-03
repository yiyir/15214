package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 15-214 HW1 Andrew ID: yiyir
 *
 * @author Yiyi Ren
 */
public class Document {
    /**
     * Instance variable url to store the URL string
     */
    private final String url;
    /**
     * Instance variable freq using HashMap to store the <word,frequency> pairs
     * of the document
     */
    private final Map<String, Integer> freq = new HashMap<String, Integer>();
    /**
     * Instance variable sum to store the sum of the squares of the frequencies
     */
    private double sum = 0.0;

    /**
     * Constructor: creates a Document object by taking a URL string
     *
     * @param urlString input URL string; should be valid and not null
     * @throws MalformedURLException if an unknown protocol is specified
     * @throws IOException           if an I/O exception occurs
     */
    public Document(String urlString) throws MalformedURLException, IOException {
        this.url = urlString;
        Scanner sc = new Scanner(new URL(urlString).openStream());
        while (sc.hasNext()) {
            String word = sc.next();
            Integer f = freq.get(word);
            if (f == null) {
                freq.put(word, 1);
            } else {
                freq.put(word, f + 1);
            }
        }
        sc.close();
        for (String str : freq.keySet()) {
            sum += freq.get(str) * freq.get(str);
        }
    }

    /**
     * Takes a second Document and calculates the cosine similarity between them
     *
     * @param second a second Document
     * @return the cosine similarity between the two Documents
     */
    double cosSim(Document second) {
        double result = 0.0;
        for (String str : freq.keySet()) {
            Integer value = second.freq.get(str);
            if (value != null) {
                result += (double) (value * this.freq.get(str));
            }
        }
        result = result / Math.sqrt(this.sum) / Math.sqrt(second.sum);
        return result;
    }

    @Override
    /**
     * Overrides the toString() method and returns a short string that
     * identifies the URL represented by the document
     */
    public String toString() {
        return this.url;
    }
}
