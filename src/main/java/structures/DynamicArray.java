package structures;

import metrics.Metrics;

public class DynamicArray {

    private int[] data;
    private int size;
    private final Metrics metrics;

    public DynamicArray() {
        data = new int[10];
        size = 0;
        metrics = new Metrics();
    }

    public void add(int value) {
        ensureCapacity();

        data[size] = value;
        metrics.move();
        size++;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];

            metrics.step();
            metrics.move();
        }

        data[index] = value;
        metrics.move();
        size++;
    }

    public int remove(int index) {
        checkIndex(index);

        int removedValue = data[index];
        metrics.step();

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];

            metrics.step();
            metrics.move();
        }

        size--;

        return removedValue;
    }

    public int get(int index) {
        checkIndex(index);

        metrics.step();

        return data[index];
    }

    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            metrics.step();
            metrics.comparison();

            if (data[i] == value) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void resetMetrics() {
        metrics.reset();
    }

    private void ensureCapacity() {
        if (size == data.length) {
            int[] newData = new int[data.length * 2];

            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];

                metrics.step();
                metrics.move();
            }

            data = newData;
        }
    }

    private void checkIndex(int index