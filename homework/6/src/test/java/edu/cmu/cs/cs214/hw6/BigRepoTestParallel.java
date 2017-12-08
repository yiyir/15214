package edu.cmu.cs.cs214.hw6;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class BigRepoTestParallel {
    private static final String PATH_NAME = "/Users/yiyiren/yiyir/.git";
    private ParallelGitAnalysis bigRepoAnalysis;

    @Before
    public void setUp() throws Exception {
        bigRepoAnalysis = new ParallelGitAnalysis(new File(PATH_NAME));
        bigRepoAnalysis.getDevelopmentHistory();
    }

    @Test
    public void getChildCommits() throws Exception {
        List<String> childCommits = bigRepoAnalysis.getChildCommits();
        assertTrue(childCommits.size() == 69);
        assertTrue(childCommits.get(0).equals("9af5187d0549bdb02f1a20cfab58813e9adadce3"));
        assertTrue(childCommits.get(1).equals("2d1bb99b253b09d5e835083726eb2bfc91467900"));
    }

    @Test
    public void getParentCommits() throws Exception {
        List<String> parentCommits = bigRepoAnalysis.getParentCommits();
        assertTrue(parentCommits.size() == 69);
        assertTrue(parentCommits.get(0).equals("2d1bb99b253b09d5e835083726eb2bfc91467900"));
        assertTrue(parentCommits.get(1).equals("070d87354ceb500104e33a46a20d14fa99c83130"));
    }

    @Test
    public void getNumOfLines() throws Exception {
        List<Integer> numOfLines = bigRepoAnalysis.getNumOfLines();
        assertTrue(numOfLines.size() == 69);
        assertTrue(numOfLines.get(0) == 191);
        assertTrue(numOfLines.get(1) == 196);
    }

}