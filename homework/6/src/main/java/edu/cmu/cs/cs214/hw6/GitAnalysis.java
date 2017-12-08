package edu.cmu.cs.cs214.hw6;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract class for git analysis.
 */
public abstract class GitAnalysis {
    /**
     * the git repository
     */
    private Repository repository;
    /**
     * the child commits
     */
    private List<String> childCommits;
    /**
     * the parent commits
     */
    private List<String> parentCommits;
    /**
     * the churn rate for the revision
     */
    private List<Integer> numOfLines;

    /**
     * Constructor method.
     *
     * @param repoDir the path name of the git repo
     * @throws IOException the repository could not be accessed to configure the rest of the builder's parameters
     */
    public GitAnalysis(File repoDir) throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(repoDir)
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
        this.repository = repository;
        childCommits = new ArrayList<>();
        parentCommits = new ArrayList<>();
        numOfLines = new ArrayList<>();
    }

    /**
     * Analyzes the git repository, compute the churn rate for each child-parent revision.
     *
     * @throws InterruptedException a loose object or pack file could not be read;
     *                              the stream threw an exception while writing to it,
     *                              or one of the blobs referenced by the DiffEntry could not be read
     * @throws GitAPIException      or subclass thereof when an error occurs
     * @throws IOException          the repository could not be accessed to configure the rest of the builder's parameters;
     *                              a loose object or pack file could not be read;
     *                              the stream threw an exception while writing to it,
     *                              or one of the blobs referenced by the DiffEntry could not be read
     */
    public abstract void getDevelopmentHistory() throws InterruptedException, GitAPIException, IOException;

    /**
     * Gets the child commits.
     *
     * @return the child commits
     */
    public List<String> getChildCommits() {
        return childCommits;
    }

    /**
     * Gets the parent commits.
     *
     * @return the parent commits
     */
    public List<String> getParentCommits() {
        return parentCommits;
    }

    /**
     * Gets the churn rate.
     *
     * @return the churn rate
     */
    public List<Integer> getNumOfLines() {
        return numOfLines;
    }

    /**
     * Gets the repository.
     *
     * @return the repository
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * Gets the total number of changed lines within one single revision.
     *
     * @param df    the formatter
     * @param diffs a list of DiffEntry for the revision
     * @return the total number of changed lines
     * @throws IOException the stream threw an exception while writing to it, or one of the blobs referenced by the DiffEntry could not be read
     */
    protected int getTotalLines(DiffFormatter df, List<DiffEntry> diffs) throws IOException {
        int totalLines = 0;
        for (DiffEntry diff : diffs) {
            int linesAdded = 0;
            int linesDeleted = 0;
            for (Edit edit : df.toFileHeader(diff).toEditList()) {
                linesDeleted += edit.getEndA() - edit.getBeginA();
                linesAdded += edit.getEndB() - edit.getBeginB();
            }
            totalLines += linesAdded + linesDeleted;
        }
        return totalLines;
    }

}
