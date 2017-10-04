package edu.cmu.cs.cs214.hw3;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PermutationCommandTest {
    private List<Integer> set1 = new ArrayList<>();
    private List<Integer> set2 = new ArrayList<>();
    private List<Integer> set3 = null;
    @Before
    public void setUp() {
        set1.add(1);
        set1.add(2);
        set1.add(3);

    }

    @Test
    public void generate() throws Exception {
        CommandInterface<Integer> command1 = new PermutationCommand<Integer>(set1);
        List<List<Integer>> result1 = command1.generate();
        assertEquals(result1.size(),6);
        System.out.println(result1);
        CommandInterface<Integer> command2 = new PermutationCommand<Integer>(set2);
        List<List<Integer>> result2 = command2.generate();
        assertNull(result2);
        CommandInterface<Integer> command3 = new PermutationCommand<Integer>(set3);
        List<List<Integer>> result3 = command3.generate();
        assertNull(result3);

    }

}