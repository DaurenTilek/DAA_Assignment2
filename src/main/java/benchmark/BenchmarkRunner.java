package benchmark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;

public class BenchmarkRunner {

    private static final int[] SIZES = {
            100,
            1_000,
            10_000,
            100_000
    };

    private static final int WARMUP_RUNS = 1;
    private static final int MEASURED_RUNS = 5;


    public static void main(String[] args)
            throws IOException {

        Locale.setDefault(Locale.US);

        File directory = new File("results");

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(
                                     "results/results.csv"))) {

            writer.println(
                    "workload,variant,structure,n," +
                            "time_ms,steps,moves,comparisons"
            );

            System.out.println(
                    "DAA Assignment 2 Benchmark"
            );

            System.out.println(
                    "=========================="
            );


            for (int n : SIZES) {
                benchmarkW1(n, writer);
            }

            for (int n : SIZES) {
                benchmarkW2(n, writer);
            }

            for (int n : SIZES) {
                benchmarkW3(
                        n,
                        "head",
                        writer
                );
            }

            for (int n : SIZES) {
                benchmarkW3(
                        n,
                        "middle",
                        writer
                );
            }

            for (int n : SIZES) {
                benchmarkW4(n, writer);
            }
        }


        System.out.println();
        System.out.println(
                "=========================="
        );

        System.out.println(
                "Benchmark completed."
        );

        System.out.println(
                "CSV saved to: results/results.csv"
        );
    }

    private static void benchmarkW1(
            int n,
            PrintWriter writer) {

        int[] values =
                Workloads.generateValues(n);

        int[] indexes =
                Workloads.generateIndexes(n);


        BenchmarkResult array =
                measure(
                        () -> Workloads.w1Array(
                                values,
                                indexes
                        )
                );


        BenchmarkResult list =
                measure(
                        () -> Workloads.w1List(
                                values,
                                indexes
                        )
                );


        save(
                writer,
                "W1",
                "-",
                "DynamicArray",
                n,
                array
        );

        save(
                writer,
                "W1",
                "-",
                "MyLinkedList",
                n,
                list
        );
    }


    private static void benchmarkW2(
            int n,
            PrintWriter writer) {

        int[] values =
                Workloads.generateSearchValues(n);

        int[] queries =
                Workloads.generateQueries(values);


        BenchmarkResult array =
                measure(
                        () -> Workloads.w2Array(
                                values,
                                queries
                        )
                );


        BenchmarkResult list =
                measure(
                        () -> Workloads.w2List(
                                values,
                                queries
                        )
                );


        save(
                writer,
                "W2",
                "-",
                "DynamicArray",
                n,
                array
        );

        save(writer,
                "W2",
                "-",
                "MyLinkedList",
                n,
                list
        );
    }


    private static void benchmarkW3(
            int n,
            String variant,
            PrintWriter writer) {

        int[] values =
                Workloads.generateValues(n);


        BenchmarkResult array =
                measure(
                        () -> Workloads.w3Array(
                                values,
                                variant
                        )
                );


        BenchmarkResult list =
                measure(
                        () -> Workloads.w3List(
                                values,
                                variant
                        )
                );


        save(
                writer,
                "W3",
                variant,
                "DynamicArray",
                n,
                array
        );

        save(
                writer,
                "W3",
                variant,
                "MyLinkedList",
                n,
                list
        );
    }

    private static void benchmarkW4(
            int n,
            PrintWriter writer) {

        int[] values =
                Workloads.generateValues(n);


        BenchmarkResult heap =
                measure(
                        () -> Workloads.w4Heap(values)
                );


        save(
                writer,
                "W4",
                "-",
                "MinHeap",
                n,
                heap
        );
    }


    private static BenchmarkResult measure(
            BenchmarkTask task) {

        for (int i = 0;
             i < WARMUP_RUNS;
             i++) {

            task.run();
        }


        BenchmarkResult[] results =
                new BenchmarkResult[MEASURED_RUNS];


        for (int i = 0;
             i < MEASURED_RUNS;
             i++) {

            results[i] = task.run();
        }


        Arrays.sort(
                results,
                (a, b) ->
                        Double.compare(
                                a.getTimeMs(),
                                b.getTimeMs()
                        )
        );


        return results[
                results.length / 2
                ];
    }


    private static void save(
            PrintWriter writer,
            String workload,
            String variant,
            String structure,
            int n,
            BenchmarkResult result) {

        writer.printf(
                Locale.US,
                "%s,%s,%s,%d,%.6f,%d,%d,%d%n",
                workload,
                variant,
                structure,
                n,
                result.getTimeMs(),
                result.getSteps(),
                result.getMoves(),
                result.getComparisons()
        );


        System.out.printf(
                Locale.US,
                "%s | variant=%s | %s | n=%d | " +
                        "median=%.3f ms | steps=%d | " +
                        "moves=%d | comparisons=%d%n",

                workload,
                variant,
                structure,
                n,
                result.getTimeMs(),
                result.getSteps(),
                result.getMoves(),
                result.getComparisons()
        );
    }

    @FunctionalInterface
    private interface BenchmarkTask {

        BenchmarkResult run();
    }
}