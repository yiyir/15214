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

public class SequentialGitAnalysis {
    private Repository repository;
    private List<String> childCommits;
    private List<String> parentCommits;
    private List<Integer> numOfLines;

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
                    totalLines += linesAdded+linesDeleted;
                }
                numOfLines.add(totalLines);
            }

        }
    }

    public List<String> getChildCommits() {
        return childCommits;
    }

    public List<String> getParentCommits() {
        return parentCommits;
    }

    public List<Integer> getNumOfLines() {
        return numOfLines;
    }

    public static void main(String[] args) throws IOException, GitAPIException {
        File repoDir = new File(args[0]);
        SequentialGitAnalysis analysis = new SequentialGitAnalysis(repoDir);
        analysis.getDevelopmentHistory();
        System.out.println("Having repository: "+repoDir.getPath());
        for(int i =0;i<analysis.getChildCommits().size();i++){
            System.out.println(analysis.getChildCommits().get(i)+", "+analysis.getParentCommits().get(i)+": "+analysis.getNumOfLines().get(i));
        }
    }
}
