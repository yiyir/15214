package edu.cmu.cs.cs214.hw6;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class SmallRepoTestSequential {
    private static final String PATH_NAME = "/Users/yiyiren/churn-rate-example/.git";
    private SequentialGitAnalysis smallRepoAnalysis;


    @Before
    public void setUp() throws Exception {
        smallRepoAnalysis = new SequentialGitAnalysis(new File(PATH_NAME));
        smallRepoAnalysis.getDevelopmentHistory();

    }

    @Test
    public void getChildCommits() throws Exception {
        List<String> childCommits = smallRepoAnalysis.getChildCommits();
        assertTrue(childCommits.size() == 2);
        assertTrue(childCommits.get(0).equals("b0eebd2c20ee60221cb5411108237d76706abf72"));
        assertTrue(childCommits.get(1).equals("34db5b16e46ec74f9da293d8e4298c08393e1283"));
    }

    @Test
    public void getParentCommits() throws Exception {
        List<String> parentCommits = smallRepoAnalysis.getParentCommits();
        assertTrue(parentCommits.size() == 2);
        assertTrue(parentCommits.get(0).equals("34db5b16e46ec74f9da293d8e4298c08393e1283"));
        assertTrue(parentCommits.get(1).equals("504cd6a9abaaa0a7fb527570fb743fd315465e15"));
    }

    @Test
    public void getNumOfLines() throws Exception {
        List<Integer> numOfLines = smallRepoAnalysis.getNumOfLines();
        assertTrue(numOfLines.size() == 2);
        assertTrue(numOfLines.get(0) == 3);
        assertTrue(numOfLines.get(1) == 9);
    }
}