package edu.cmu.cs.cs214.hw3;

import java.util.List;

/**
 * CommandInterface -- Using command design pattern.
 * 
 * @author yiyir
 *
 */
public interface CommandInterface<E> {
    /**
     * The 'generate()' command.
     * 
     * @return a list of all the results that are generated
     */
    List<List<E>> generate();
}
