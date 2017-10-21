package edu.cmu.cs.cs214.hw4.core.gameelements;


import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class DictionaryTest {

    private Dictionary dictionary;
    @Test
    public void contains() throws FileNotFoundException {
            dictionary = new Dictionary();
            List<String> words = new ArrayList<>();
            words.add("apples");
            words.add("abacus");
            assertTrue(dictionary.contains(words));
        }
    }

