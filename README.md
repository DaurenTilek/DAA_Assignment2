# DAA Assignment 2 — Data Structures

## Description

This project is Assignment 2 for the Design and Analysis of Algorithms course.

The project implements three data structures from scratch:

- DynamicArray
- MyLinkedList
- MinHeap

The goal is to compare theoretical complexity, physical operation counts, and actual execution time under different workloads.

## Project Structure

```text
DAA_Assignment2/
├── src/
│   ├── main/java/
│   │   ├── structures/
│   │   │   ├── DynamicArray.java
│   │   │   ├── MyLinkedList.java
│   │   │   └── MinHeap.java
│   │   ├── metrics/
│   │   │   └── Metrics.java
│   │   └── benchmark/
│   │       ├── BenchmarkRunner.java
│   │       ├── BenchmarkResult.java
│   │       └── Workloads.java
│   │
│   └── test/java/structures/
│       ├── DynamicArrayTest.java
│       ├── MyLinkedListTest.java
│       └── MinHeapTest.java
│
├── results/
│   ├── results.csv
│   └── plots/
│
├── plots.py
├── pom.xml
└── README.md
```

## Implemented Data Structures

### DynamicArray

Supported operations:

- `add(x)`
- `add(index, x)`
- `remove(index)`
- `get(index)`
- `contains(x)`

The internal array doubles its capacity when it becomes full.

### MyLinkedList

A singly linked list with `head` and `tail` references.

Supported operations:

- `add(x)`
- `add(index, x)`
- `remove(index)`
- `get(index)`
- `contains(x)`

### MinHeap

Array-based binary min-heap.

Supported operations:

- `insert(x)`
- `peekMin()`
- `extractMin()`

Insertion uses bubble-up and extraction uses bubble-down.

## Metrics

The benchmark records:

- `steps` — array-cell reads or moves to the next linked-list node
- `moves` — array-element shifts or pointer updates
- `comparisons` — comparisons between element values
- `time_ms` — median execution time in milliseconds

## Benchmark Workloads

The benchmark uses:

```text
n = 100
n = 1,000
n = 10,000
n = 100,000
```

All random data is generated using:

```java
new Random(42)
```

### W1 — Random Access

Performs 10,000 random `get(index)` operations on DynamicArray and MyLinkedList.

### W2 — Search

Performs 1,000 `contains(x)` queries.

- 500 values are present
- 500 values are absent

### W3 — Insert & Remove

Two variants are tested:

- `head` — 1,000 insertions and 1,000 removals at index 0
- `middle` — 1,000 insertions and 1,000 removals near the middle

### W4 — Priority Processing

Inserts `n` values into MinHeap and performs `n` `extractMin()` operations.

The extracted values are checked to ensure non-decreasing order.

## Benchmark Method

Each benchmark case uses:

1. One warm-up run
2. Five measured runs
3. Median execution time

Results are saved to:

```text
results/results.csv
```

CSV columns:

```text
workload,variant,structure,n,time_ms,steps,moves,comparisons
```

## Build

This project uses Maven.

If Maven is installed:

```bash
mvn clean compile
```

The project can also be opened and built directly in IntelliJ IDEA.

## Run Tests

JUnit 5 is used for testing.

With Maven:

```bash
mvn test
```

Tests cover:

- normal operations
- empty structures
- one element
- duplicate values
- first and last indexes
- invalid indexes
- heap extraction order

## Run Benchmark

Run:

```text
src/main/java/benchmark/BenchmarkRunner.java
```

In IntelliJ IDEA:

```text
BenchmarkRunner.java
→ Run BenchmarkRunner.main()
```

The benchmark automatically creates or updates:

```text
results/results.csv
```

## Generate Plots

The plotting script requires Python with pandas and matplotlib.

Run:

```bash
python plots.py
```

Generated charts are stored in:

```text
results/plots/
```

## Git Workflow

The project uses the following branches:

- `main`
- `feature/array`
- `feature/list`
- `feature/heap`
- `feature/metrics`

The final release is tagged:

```text
v1.0
```

## Technologies

- Java
- Maven
- JUnit 5
- Python
- pandas
- matplotlib
- Git
- GitHub
