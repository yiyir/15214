package edu.cmu.cs.cs214.hw3;

import org.junit.Test;

import static org.junit.Assert.*;

public class CryptarithmTest {
    private String[] valid1 = {"IJOP", "+", "GH", "=", "JOPH"};
    private String[] valid2 = {"IP", "+", "GH", "*", "I", "=", "JOPH"};
    private String[] invalid1 = {"IJKLMNOP", "+", "ABCDEFGH", "=", "WYFHEUOFDH"};
    private String[] invalid2 = {"IJKP", "/", "AH", "=", "WH"};
    private String[] invalid3 = {"AM", "+", "is", "=", "She"};
    private String[] invalid4 = {"IJKP"};
    private String[] invalid5 = {"IJKP", "+", "+", "="};
    private String[] invalid6 = {"IJKP", "="};
    private String[] invalid7 = null;
    private String[] invalid8 = new String[0];

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor() {
        Cryptarithm crp1 = new Cryptarithm(invalid1);
        Cryptarithm crp2 = new Cryptarithm(invalid2);
        Cryptarithm crp3 = new Cryptarithm(invalid3);
        Cryptarithm crp4 = new Cryptarithm(invalid4);
        Cryptarithm crp5 = new Cryptarithm(invalid5);
        Cryptarithm crp6 = new Cryptarithm(invalid6);
        Cryptarithm crp7 = new Cryptarithm(invalid7);
        Cryptarithm crp8 = new Cryptarithm(invalid8);
        Cryptarithm crp9 = new Cryptarithm(valid1);
        Cryptarithm crp10 = new Cryptarithm(valid2);
        assertTrue(crp9.isValid());
        assertTrue(crp10.isValid());
    }


}