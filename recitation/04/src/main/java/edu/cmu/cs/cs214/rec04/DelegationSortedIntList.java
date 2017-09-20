package edu.cmu.cs.cs214.rec04;

/**
 * DelegationSortedIntList -- a variant of a SortedIntList that keeps count of
 * the number of attempted element insertions (not to be confused with the
 * current size, which goes down when an element is removed) and exports an
 * accessor (totalAdded) for this count.
 * 
 * @author Nora Shoemaker
 *
 */

// HINT: Take a look at the UML diagram to see what DelegationSortedIntList
// should implement.
public class DelegationSortedIntList implements IntegerList {
    // the number of attempted element insertions
    private int totalAdded;
    private SortedIntList list;

    public DelegationSortedIntList() {
        this.list = new SortedIntList();
    }

    public int getTotalAdded() {
        return this.totalAdded;
    }

    @Override
    public boolean add(int num) {
        boolean success = this.list.add(num);
        if (success) {
            totalAdded++;

        }

        return success;
    }

    @Override
    public boolean addAll(IntegerList list) {
        int oldSize = this.list.size();
        this.list.addAll(list);
        int newSize = this.list.size();
        totalAdded += (newSize - oldSize);
        return false;
    }

    @Override
    public int get(int index) {
        // TODO Auto-generated method stub
        return this.list.get(index);
    }

    @Override
    public boolean remove(int num) {
        // TODO Auto-generated method stub
        return this.list.remove(num);
    }

    @Override
    public boolean removeAll(IntegerList list) {
        // TODO Auto-generated method stub
        return this.list.removeAll(list);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return this.list.size();
    }

}
