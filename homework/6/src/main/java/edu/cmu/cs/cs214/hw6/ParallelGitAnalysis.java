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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * This is a parallel program to compute the churn rate for each revision in a Git repository.
 */
public class ParallelGitAnalysis {
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
    public ParallelGitAnalysis(File repoDir) throws IOException {
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
     */
    public void getDevelopmentHistory() throws InterruptedException, GitAPIException {
        List<Callable<List<String>>> calls = new ArrayList<>();
        Git git = new Git(repository);
        ExecutorService threadPool = Executors.newFixedThreadPool(16);
        BlockingQueue<Future<List<String>>> queue = new LinkedBlockingQueue<>();
        Iterable<RevCommit> log = git.log().call();
        Iterator<RevCommit> itr = log.iterator();

        threadPool.submit(() -> {
            while (true) {
                List<String> result = queue.take().get();
//                System.out.println(result.size());
                int numOfParent = result.size() / 3;
                for (int i = 0; i < numOfParent; i++) {
                    childCommits.add(result.get(3 * i));
                    parentCommits.add(result.get(3 * i + 1));
                    numOfLines.add(Integer.valueOf(result.get(3 * i + 2)));
                }
//                System.out.println(childCommits.size());
            }
        });
        while (itr.hasNext()) {
//            System.out.println("enter itr while loop");
            RevCommit child = itr.next();

            Callable<List<String>> newThread = new Callable<List<String>>() {
                @Override
                public List<String> call() throws Exception {
                    final RevCommit childCopy = child;
                    List<String> result = new ArrayList<>();
//                System.out.println("enter thread");
                    DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
                    df.setRepository(repository);
                    df.setDiffComparator(RawTextComparator.DEFAULT);
                    df.setDetectRenames(true);
                    ObjectReader reader = repository.newObjectReader();
                    ObjectId head = childCopy.getTree();
                    CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
                    try {
                        newTreeIter.reset(reader, head);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (RevCommit parent : childCopy.getParents()) {
//                    System.out.println("enter analyze parent loop");
//                    threadPool.submit(() -> {
//                    final RevCommit finalParent = parent;
                        int totalLines = 0;
                        ObjectId oldHead = parent.getTree();
                        CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
                        try {
                            oldTreeIter.reset(reader, oldHead);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        DiffCommand diffCommand = git.diff();
                        List<DiffEntry> diffs = null;
                        try {
                            diffs = diffCommand.setNewTree(newTreeIter)
                                    .setOldTree(oldTreeIter)
                                    .call();
                        } catch (GitAPIException e) {
                            e.printStackTrace();
                        }
                        for (DiffEntry diff : diffs) {
//                        System.out.println("enter diff loop");
                            int linesAdded = 0;
                            int linesDeleted = 0;
                            try {
                                for (Edit edit : df.toFileHeader(diff).toEditList()) {
                                    linesDeleted += edit.getEndA() - edit.getBeginA();
                                    linesAdded += edit.getEndB() - edit.getBeginB();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            totalLines += linesAdded + linesDeleted;
                        }
//                        synchronized (this) {

                        result.add(child.getName());
                        result.add(parent.getName());
//                    System.out.println(String.valueOf(totalLines));
                        result.add(String.valueOf(totalLines));
//                        }

                    }
//                System.out.println(result.size());

                    return result;
                }
            };
            queue.put(threadPool.submit(newThread));
            calls.add(newThread);
            List<Future<List<String>>> futures = threadPool.invokeAll(calls);
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
     * @throws IOException          the repository could not be accessed to configure the rest of the builder's parameters;
     *                              a loose object or pack file could not be read;
     *                              the stream threw an exception while writing to it,
     *                              or one of the blobs referenced by the DiffEntry could not be read
     * @throws GitAPIException      or subclass thereof when an error occurs
     * @throws InterruptedException a thread is waiting or sleeping and another thread interrupts it
     */
    public static void main(String[] args) throws IOException, GitAPIException, InterruptedException {
        long startTime = System.nanoTime();
        File repoDir = new File(args[0]);
        ParallelGitAnalysis analysis = new ParallelGitAnalysis(repoDir);
        analysis.getDevelopmentHistory();
        System.out.println("Having repository: " + repoDir.getPath());
        // After finishing the previous tasks, print out the time used.
        for (int i = 0; i < analysis.getChildCommits().size(); i++) {
            System.out.println(analysis.getChildCommits().get(i) + ", " + analysis.getParentCommits().get(i) + ": " + analysis.getNumOfLines().get(i));
        }
        double totalTime = (System.nanoTime() - startTime) / 1_000_000_000.0;
        System.out.printf("It took %.2f seconds to finish the analysis.\n", totalTime);
        System.exit(0); // Forces background threads to quit, close up all threads

    }
}
