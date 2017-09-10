package edu.cmu.cs.cs214.rec02;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * TODO: Write detailed unit tests for the {@link LinkedIntQueue} and
 * {@link ArrayIntQueue} classes, as described in the handout. The
 * {@link ArrayIntQueue} class contains a few bugs. Use the tests you wrote for
 * the {@link LinkedIntQueue} class to test the {@link ArrayIntQueue}
 * implementation. Refine your tests to achieve 100% line coverage and use them
 * to determine and correct the bugs!
 * 
 * @author Alex Lockwood
 */
public class IntQueueTest {

    private IntQueue emptyQ = new ArrayIntQueue();
    private IntQueue smallQ = new ArrayIntQueue();
    private IntQueue largeQ = new ArrayIntQueue();
    private IntQueue borderQ = new ArrayIntQueue();

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {

        // mQueue = new LinkedIntQueue();
        smallQ.enqueue(1);
        smallQ.enqueue(2);
        smallQ.enqueue(3);
        for (int i = 0; i < 7; i++) {
            largeQ.enqueue(i);
        }
        for (int i = 0; i < 10; i++) {
            borderQ.enqueue(i);
        }

    }

    @Test
    public void testClear() {
        smallQ.clear();
        emptyQ.clear();
        largeQ.clear();
        borderQ.clear();
        assertTrue(smallQ.isEmpty());
        assertTrue(largeQ.isEmpty());
        assertTrue(emptyQ.isEmpty());
        assertTrue(borderQ.isEmpty());
    }

    @Test
    public void testDequeueEmpty() {
        assertNull(emptyQ.dequeue());
        assertEquals(smallQ.dequeue(), new Integer(1));
        assertEquals(largeQ.dequeue(), new Integer(0));
        assertEquals(borderQ.dequeue(), new Integer(0));

    }
    @Test
    public void testEnqueueBorder() {
        borderQ.enqueue(10);
        assertTrue(borderQ.size()==11);
        
    }
    @Test
    public void testSize() {
        assertEquals(smallQ.size(), 3);
        assertEquals(largeQ.size(), 7);
        assertEquals(borderQ.size(), 10);
        assertEquals(emptyQ.size(), 0);
    }
    @Test
    public void testIsEmpty() {
        assertTrue(smallQ.isEmpty()==false);
        assertTrue(largeQ.isEmpty()==false);
        assertTrue(borderQ.isEmpty()==false);
        assertTrue(emptyQ.isEmpty()==true);
        
    }
    @Test
    public void testEnsureCap() {
        borderQ.dequeue();borderQ.dequeue();
        borderQ.enqueue(10);borderQ.enqueue(11);borderQ.enqueue(12);
        assertEquals(borderQ.dequeue(),new Integer(2));
        assertEquals(borderQ.dequeue(),new Integer(3));
        assertEquals(borderQ.dequeue(),new Integer(4));
        assertEquals(borderQ.dequeue(),new Integer(5));
        assertEquals(borderQ.dequeue(),new Integer(6));
        assertEquals(borderQ.dequeue(),new Integer(7));
        assertEquals(borderQ.dequeue(),new Integer(8));
        assertEquals(borderQ.dequeue(),new Integer(9));
        
        
        
        
        
    }
    @Test
    public void testPeek() {
        assertNull(emptyQ.peek());
        assertEquals(smallQ.peek(), new Integer(1));
        assertEquals(largeQ.peek(), new Integer(0));
        assertEquals(borderQ.peek(), new Integer(0));
        
    }
    @Test
    public void testContent() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");
            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println(input);
                emptyQ.enqueue(input);
            }

            // Used boxed type to pacify assertEquals overload resolution for
            for (Integer result : correctResult) {
                assertEquals(emptyQ.dequeue(), result);
            }
        }
    }

}
