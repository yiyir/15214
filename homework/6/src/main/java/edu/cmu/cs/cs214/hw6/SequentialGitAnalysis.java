package edu.cmu.cs.cs214.hw6;

import org.eclipse.jgit.api.DiffCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * This is a sequential program to compute the churn rate for each revision in a Git repository.
 */
public class SequentialGitAnalysis extends GitAnalysis {

    /**
     * Constructor method.
     *
     * @param repoDir the path name of the git repo
     * @throws IOException the repository could not be accessed to configure the rest of the builder's parameters
     */
    public SequentialGitAnalysis(File repoDir) throws IOException {
        super(repoDir);
    }


    @Override
    public void getDevelopmentHistory() throws InterruptedException, GitAPIException, IOException {
        Git git = new Git(this.getRepository());
        Iterable<RevCommit> log = git.log().call();
        Iterator<RevCommit> itr = log.iterator();
        ObjectReader reader = this.getRepository().newObjectReader();
        DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
        df.setRepository(this.getRepository());
        df.setDiffComparator(RawTextComparator.DEFAULT);
        df.setDetectRenames(true);
        while (itr.hasNext()) {
            RevCommit child = itr.next();
            ObjectId head = child.getTree();
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            newTreeIter.reset(reader, head);
            for (RevCommit parent : child.getParents()) {
                int totalLines = 0;
                this.getChildCommits().add(child.getName());
                this.getParentCommits().add(parent.getName());
                ObjectId oldHead = parent.getTree();
                CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
                oldTreeIter.reset(reader, oldHead);
                DiffCommand diffCommand = git.diff();
                List<DiffEntry> diffs = diffCommand.setNewTree(newTreeIter)
                        .setOldTree(oldTreeIter)
                        .call();
                this.getNumOfLines().add(this.getTotalLines(df, diffs));
            }

        }
    }


    /**
     * Takes the first command line argument as the git repository path name, creates a new sequential git analysis,
     * computes and prints out the churn rate for each child-parent revision in a chronological order.
     *
     * @param args the command line arguments denoting the path name of the local git repository to be analyzed
     * @throws IOException          the repository could not be accessed to configure the rest of the builder's parameters;
     *                              a loose object or pack file could not be read;
     *                              the stream threw an exception while writing to it,
     *                              or one of the blobs referenced by the DiffEntry could not be read
     * @throws GitAPIException      or subclass thereof when an error occurs
     * @throws InterruptedException a thread is waiting or sleeping and another thread interrupts it
     */
    public static void main(String[] args) throws IOException, GitAPIException, InterruptedException {
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
