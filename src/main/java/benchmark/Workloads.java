package benchmark;

import metrics.Metrics;
import structures.DynamicArray;
import structures.MinHeap;
import structures.MyLinkedList;

import java.util.Random;

public class Workloads {

    private static final int RANDOM_ACCESS_CALLS = 10_000;
    private static final int SEARCH_CALLS = 1_000;
    private static final int W3_OPERATIONS = 1_000;

    public static BenchmarkResult w1Array(
            int[] values,
            int[] indexes) {

        DynamicArray array = createArray(values);

        array.resetMetrics();

        long start = System.nanoTime();

        for (int index : indexes) {
            array.get(index);
        }

        long end = System.nanoTime();

        return createResult(
                start,
                end,
                array.getMetrics()
        );
    }


    public static BenchmarkResult w1List(
            int[] values,
            int[] indexes) {

        MyLinkedList list = createList(values);

        list.resetMetrics();

        long start = System.nanoTime();

        for (int index : indexes) {
            list.get(index);
        }

        long end = System.nanoTime();

        return createResult(
                start,
                end,
                list.getMetrics()
        );
    }

    public static BenchmarkResult w2Array(
            int[] values,
            int[] queries) {

        DynamicArray array = createArray(values);

        array.resetMetrics();

        long start = System.nanoTime();

        for (int query : queries) {
            array.contains(query);
        }

        long end = System.nanoTime();

        return createResult(
                start,
                end,
                array.getMetrics()
        );
    }


    public static BenchmarkResult w2List(
            int[] values,
            int[] queries) {

        MyLinkedList list = createList(values);

        list.resetMetrics();

        long start = System.nanoTime();

        for (int query : queries) {
            list.contains(query);
        }

        long end = System.nanoTime();

        return createResult(
                start,
                end,
                list.getMetrics()
        );
    }

    public static BenchmarkResult w3Array(
            int[] values,
            String variant) {

        DynamicArray array = createArray(values);

        array.resetMetrics();

        long start = System.nanoTime();

        if (variant.equals("head")) {

            for (int i = 0; i < W3_OPERATIONS; i++) {
                array.add(0, i);
            }

            for (int i = 0; i < W3_OPERATIONS; i++) {
                array.remove(0);
            }

        } else {

            for (int i = 0; i < W3_OPERATIONS; i++) {
                int middle = array.size() / 2;
                array.add(middle, i);
            }

            for (int i = 0; i < W3_OPERATIONS; i++) {
                int middle = array.size() / 2;
                array.remove(middle);
            }
        }

        long end = System.nanoTime();

        return createResult(
                start,
                end,
                array.getMetrics()
        );
    }


    public static BenchmarkResult w3List(
            int[] values,
            String variant) {

        MyLinkedList list = createList(values);

        list.resetMetrics();

        long start = System.nanoTime();

        if (variant.equals("head")) {

            for (int i = 0; i < W3_OPERATIONS; i++) {
                list.add(0, i);
            }

            for (int i = 0; i < W3_OPERATIONS; i++) {
                list.remove(0);
            }

        } else {

            for (int i = 0; i < W3_OPERATIONS; i++) {
                int middle = list.size() / 2;
                list.add(middle, i);
            }

            for (int i = 0; i < W3_OPERATIONS; i++) {
                int middle = list.size() / 2;
                list.remove(middle);
            }
        }

        long end = System.nanoTime();

        return createResult(
                start,
                end,list.getMetrics()
        );
    }

    public static BenchmarkResult w4Heap(
            int[] values) {

        MinHeap heap = new MinHeap();

        heap.resetMetrics();

        long start = System.nanoTime();

        for (int value : values) {
            heap.insert(value);
        }

        int previous = Integer.MIN_VALUE;

        for (int i = 0; i < values.length; i++) {

            int current = heap.extractMin();

            if (current < previous) {
                throw new IllegalStateException(
                        "Heap output is not sorted"
                );
            }

            previous = current;
        }

        long end = System.nanoTime();

        return createResult(
                start,
                end,
                heap.getMetrics()
        );
    }


    public static int[] generateValues(int n) {

        Random random = new Random(42);

        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = random.nextInt();
        }

        return values;
    }


    public static int[] generateSearchValues(int n) {

        Random random = new Random(42);

        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = random.nextInt(1_000_000);
        }

        return values;
    }


    public static int[] generateIndexes(int n) {

        Random random = new Random(42);

        int[] indexes =
                new int[RANDOM_ACCESS_CALLS];

        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = random.nextInt(n);
        }

        return indexes;
    }


    public static int[] generateQueries(
            int[] values) {

        Random random = new Random(42);

        int[] queries =
                new int[SEARCH_CALLS];

        for (int i = 0; i < SEARCH_CALLS / 2; i++) {

            queries[i] =
                    values[random.nextInt(values.length)];
        }

        for (int i = SEARCH_CALLS / 2;
             i < SEARCH_CALLS;
             i++) {

            queries[i] = 2_000_000 + i;
        }

        return queries;
    }

    private static DynamicArray createArray(
            int[] values) {

        DynamicArray array = new DynamicArray();

        for (int value : values) {
            array.add(value);
        }

        return array;
    }


    private static MyLinkedList createList(
            int[] values) {

        MyLinkedList list = new MyLinkedList();

        for (int value : values) {
            list.add(value);
        }

        return list;
    }


    private static BenchmarkResult createResult(
            long start,
            long end,
            Metrics metrics) {

        double timeMs =
                (end - start) / 1_000_000.0;

        return new BenchmarkResult(
                timeMs,
                metrics.getSteps(),
                metrics.getMoves(),
                metrics.getComparisons()
        );
    }
}