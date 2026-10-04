package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    @Test
    void addAndGetTest() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    void addAtIndexTest() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(30);

        list.add(1, 20);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void addAtHeadTest() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);

        list.add(0, 5);

        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
        assertEquals(20, list.get(2));
    }

    @Test
    void removeMiddleTest() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        int removed = list.remove(1);

        assertEquals(20, removed);
        assertEquals(2, list.size());
        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
    }

    @Test
    void removeHeadTest() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);

        int removed = list.remove(0);

        assertEquals(10, removed);
        assertEquals(1, list.size());
        assertEquals(20, list.get(0));
    }

    @Test
    void containsTest() {
        MyLinkedList list = new MyLinkedList();

        list.add(5);
        list.add(10);
        list.add(15);

        assertTrue(list.contains(10));
        assertFalse(list.contains(100));
    }

    @Test
    void duplicateValuesTest() {
        MyLinkedList list = new MyLinkedList();

        list.add(7);
        list.add(7);
        list.add(7);

        assertEquals(3, list.size());
        assertTrue(list.contains(7));
    }

    @Test
    void firstAndLastIndexTest() {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(30, list.get(list.size() - 1));
    }

    @Test
    void oneElementTest() {
        MyLinkedList list = new MyLinkedList();

        list.add(99);

        assertEquals(1, list.size());
        assertEquals(99, list.get(0));

        assertEquals(99, list.remove(0));
        assertEquals(0, list.size());
    }

    @Test
    void invalidIndexTest() {
        MyLinkedList list = new MyLinkedList();

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(0));

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(0));

        assertThrows(IndexOutOfBoundsException.class,
                () -> list.add(1, 10));
    }
}