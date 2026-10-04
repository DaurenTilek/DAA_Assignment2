package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    @Test
    void insertAndPeekMinTest() {
        MinHeap heap = new MinHeap();

        heap.insert(20);
        heap.insert(10);
        heap.insert(30);
        heap.insert(5);

        assertEquals(5, heap.peekMin());
        assertEquals(4, heap.size());
    }

    @Test
    void extractMinTest() {
        MinHeap heap = new MinHeap();

        heap.insert(20);
        heap.insert(5);
        heap.insert(10);

        assertEquals(5, heap.extractMin());
        assertEquals(10, heap.peekMin());
        assertEquals(2, heap.size());
    }

    @Test
    void sortedOutputTest() {
        MinHeap heap = new MinHeap();

        int[] values = {40, 10, 30, 5, 20, 15, 50};

        for (int value : values) {
            heap.insert(value);
        }

        int previous = Integer.MIN_VALUE;

        while (heap.size() > 0) {
            int current = heap.extractMin();

            assertTrue(current >= previous);

            previous = current;
        }
    }

    @Test
    void duplicateValuesTest() {
        MinHeap heap = new MinHeap();

        heap.insert(5);
        heap.insert(5);
        heap.insert(5);

        assertEquals(5, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(5, heap.extractMin());
    }

    @Test
    void oneElementTest() {
        MinHeap heap = new MinHeap();

        heap.insert(42);

        assertEquals(42, heap.peekMin());
        assertEquals(42, heap.extractMin());
        assertEquals(0, heap.size());
    }

    @Test
    void emptyPeekTest() {
        MinHeap heap = new MinHeap();

        assertThrows(IllegalStateException.class,
                heap::peekMin);
    }

    @Test
    void emptyExtractTest() {
        MinHeap heap = new MinHeap();

        assertThrows(IllegalStateException.class,
                heap::extractMin);
    }

    @Test
    void resizeTest() {
        MinHeap heap = new MinHeap();

        for (int i = 100; i >= 0; i--) {
            heap.insert(i);
        }

        assertEquals(0, heap.peekMin());
        assertEquals(101, heap.size());
    }
}