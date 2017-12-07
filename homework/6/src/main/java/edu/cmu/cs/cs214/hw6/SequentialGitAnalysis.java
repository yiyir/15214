package edu.cmu.cs.cs214.hw6;

import org.eclipse.jgit.api.DiffCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a sequential program to compute the churn rate for each revision in a Git repository.
 */
public class SequentialGitAnalysis {
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
    public SequentialGitAnalysis(File repoDir) throws IOException {
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
     * @throws IOException     a loose object or pack file could not be read;
     *                         the stream threw an exception while writing to it,
     *                         or one of the blobs referenced by the DiffEntry could not be read
     * @throws GitAPIException or subclass thereof when an error occurs
     */
    public void getDevelopmentHistory() throws IOException, GitAPIException {
        Git git = new Git(repository);
        Iterable<RevCommit> log = git.log().call();
        Iterator<RevCommit> itr = log.iterator();
        ObjectReader reader = repository.newObjectReader();
        DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
        df.setRepository(repository);
        df.setDiffComparator(RawTextComparator.DEFAULT);
        df.setDetectRenames(true);
        while (itr.hasNext()) {
            RevCommit child = itr.next();
            ObjectId head = child.getTree();
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            newTreeIter.reset(reader, head);
            for (RevCommit parent : child.getParents()) {
                int totalLines = 0;
                childCommits.add(child.getName());
                parentCommits.add(parent.getName());
                ObjectId oldHead = parent.getTree();
                CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
                oldTreeIter.reset(reader, oldHead);
                DiffCommand diffCommand = git.diff();
                List<DiffEntry> diffs = diffCommand.setNewTree(newTreeIter)
                        .setOldTree(oldTreeIter)
                        .call();
                for (DiffEntry diff : diffs) {
                    int linesAdded = 0;
                    int linesDeleted = 0;
                    for (Edit edit : df.toFileHeader(diff).toEditList()) {
                        linesDeleted += edit.getEndA() - edit.getBeginA();
                        linesAdded += edit.getEndB() - edit.getBeginB();
                    }
                    totalLines += linesAdded + linesDeleted;
                }
                numOfLines.add(totalLines);
            }

        }
    }

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
     * Takes the first command line argument as the git repository path name, creates a new sequential git analysis,
     * computes and prints out the churn rate for each child-parent revision in a chronological order.
     *
     * @param args the command line arguments denoting the path name of the local git repository to be analyzed
     * @throws IOException     the repository could not be accessed to configure the rest of the builder's parameters;
     *                         a loose object or pack file could not be read;
     *                         the stream threw an exception while writing to it,
     *                         or one of the blobs referenced by the DiffEntry could not be read
     * @throws GitAPIException or subclass thereof when an error occurs
     */
    public static void main(String[] args) throws IOException, GitAPIException {
        long startTime = System.nanoTime(); // Record the start time for simple benchmarking
        File repoDir = new File(args[0]);
        SequentialGitAnalysis analysis = new SequentialGitAnalysis(repoDir);
        analysis.getDevelopmentHistory();
        System.out.println("Having repository: " + repoDir.getPath());
        for (int i = 0; i < analysis.getChildCommits().size(); i++) {
            System.out.println(analysis.getChildCommits().get(i) + ", " + analysis.getParentCommits().get(i) + ": " + analysis.getNumOfLines().get(i));
        }
        // After finishing the previous tasks, print out the time used.
        double totalTime = (System.nanoTime() - startTime) / 1_000_000_000.0;
        System.out.printf("It took %.2f seconds to finish the analysis.\n", totalTime);
        System.exit(0); // Forces background threads to quit, close up all threads
    }
}
