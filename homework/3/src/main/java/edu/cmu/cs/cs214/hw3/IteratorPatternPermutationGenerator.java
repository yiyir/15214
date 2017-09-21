/**
 * 
 */
package edu.cmu.cs.cs214.hw3;

import java.util.Iterator;

/**
 * @author apple
 *
 */
class IteratorPatternPermutationGenerator implements Iterable<Object> {
    //private List<List<Object>> result;
    //public IteratorPatternPermutationGenerator(List<Object> set) {
        
    
    @Override
    public Iterator<Object> iterator() {
        // TODO Auto-generated method stub
        return new PermutationIterator();
    }

    private class PermutationIterator implements Iterator<Object> {
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public Object next() {
            // TODO Auto-generated method stub
            return null;
        }

    }
}
