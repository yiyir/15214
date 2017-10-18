package edu.cmu.cs.cs214.hw4.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class represents the dictionary used in a scrabble game.
 * There might be different versions of dictionary available, however, each game should only refer to one dictionary.
 */
public class Dictionary {
    /**
     * the words contained in a dictionary
     */
    private final Set<String> words = new HashSet<>();

    /**
     * Creates a new dictionary with the given file representing the dictionary.
     *
     * @param file the given file
     * @throws FileNotFoundException if the file is not found
     */
    public Dictionary(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            words.add(sc.next());
        }
        if (sc != null) sc.close();
    }

    /**
     * Checks if the dictionary contains all the given words.
     *
     * @param words a list of words
     * @return true if the dictionary contains all the given words, false if any of the words is not in the dictionary
     */
    public boolean contains(List<String> words) {
        return this.words.containsAll(words);
    }



//    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("words.txt");
//        Dictionary dic = new Dictionary(file);
//        List<String> words = new ArrayList<>();
//        words.add("apples");
//        words.add("abacus");
//        System.out.println(dic.contains(words));
//    }
}
