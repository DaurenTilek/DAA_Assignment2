package structures;

import metrics.Metrics;

public class MinHeap {

    private int[] heap;
    private int size;
    private final Metrics metrics;

    public MinHeap() {
        heap = new int[10];
        size = 0;
        metrics = new Metrics();
    }

    public void insert(int value) {
        ensureCapacity();

        heap[size] = value;
        metrics.move();

        int current = size;
        size++;

        while (current > 0) {
            int parent = (current - 1) / 2;

            metrics.step();
            metrics.step();
            metrics.comparison();

            if (heap[parent] <= heap[current]) {
                break;
            }

            swap(parent, current);
            current = parent;
        }
    }

    public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        metrics.step();
        return heap[0];
    }

    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap[0];
        metrics.step();

        heap[0] = heap[size - 1];
        metrics.step();
        metrics.move();

        size--;

        if (size > 0) {
            bubbleDown(0);
        }

        return min;
    }

    private void bubbleDown(int index) {
        int current = index;

        while (true) {
            int left = 2 * current + 1;
            int right = 2 * current + 2;
            int smallest = current;

            if (left < size) {
                metrics.step();
                metrics.step();
                metrics.comparison();

                if (heap[left] < heap[smallest]) {
                    smallest = left;
                }
            }

            if (right < size) {
                metrics.step();
                metrics.step();
                metrics.comparison();

                if (heap[right] < heap[smallest]) {
                    smallest = right;
                }
            }

            if (smallest == current) {
                break;
            }

            swap(current, smallest);
            current = smallest;
        }
    }

    private void swap(int first, int second) {
        int temp = heap[first];
        metrics.step();

        heap[first] = heap[second];
        metrics.step();
        metrics.move();

        heap[second] = temp;
        metrics.move();
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            int[] newHeap = new int[heap.length * 2];

            for (int i = 0; i < heap.length; i++) {
                newHeap[i] = heap[i];
                metrics.step();
                metrics.move();
            }

            heap = newHeap;
        }
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
}