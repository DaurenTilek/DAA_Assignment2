package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    @Test
    void addAndGetTest() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
        assertEquals(3, array.size());
    }

    @Test
    void addAtIndexTest() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(30);

        array.add(1, 20);

        assertEquals(10, array.get(0));
        assertEquals(20, array.get(1));
        assertEquals(30, array.get(2));
        assertEquals(3, array.size());
    }

    @Test
    void removeTest() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(20);
        array.add(30);

        int removed = array.remove(1);

        assertEquals(20, removed);
        assertEquals(2, array.size());
        assertEquals(10, array.get(0));
        assertEquals(30, array.get(1));
    }

    @Test
    void containsTest() {
        DynamicArray array = new DynamicArray();

        array.add(5);
        array.add(10);
        array.add(15);

        assertTrue(array.contains(10));
        assertFalse(array.contains(100));
    }

    @Test
    void duplicateValuesTest() {
        DynamicArray array = new DynamicArray();

        array.add(7);
        array.add(7);
        array.add(7);

        assertEquals(3, array.size());
        assertTrue(array.contains(7));
    }

    @Test
    void firstAndLastIndexTest() {
        DynamicArray array = new DynamicArray();

        array.add(10);
        array.add(20);
        array.add(30);

        assertEquals(10, array.get(0));
        assertEquals(30, array.get(array.size() - 1));
    }

    @Test
    void invalidGetIndexTest() {
        DynamicArray array = new DynamicArray();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> array.get(0)
        );
    }

    @Test
    void invalidRemoveIndexTest() {
        DynamicArray array = new DynamicArray();

        array.add(10);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> array.remove(1)
        );
    }

    @Test
    void invalidAddIndexTest() {
        DynamicArray array = new DynamicArray();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> array.add(1, 10)
        );
    }

    @Test
    void resizeTest() {
        DynamicArray array = new DynamicArray();

        for (int i = 0; i < 100; i++) {
            array.add(i);
        }

        assertEquals(100, array.size());

        for (int i = 0; i < 100; i++) {
            assertEquals(i, array.get(i));
        }
    }
}