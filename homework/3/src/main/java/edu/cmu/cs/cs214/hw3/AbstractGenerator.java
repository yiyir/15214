/**
 * 
 */
package edu.cmu.cs.cs214.hw3;

import java.util.List;

/**
 * @author apple
 *
 */
public abstract class AbstractGenerator {
    /**
     * 
     */
    protected List<Object> set;

    /**
     * 
     * @param set
     */
    protected AbstractGenerator(List<Object> set) {
        this.set = set;
    }

    /**
     * 
     * @return
     */
    abstract protected List<Object> generate();
}
