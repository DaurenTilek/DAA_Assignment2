package benchmark;

public class BenchmarkResult {

    private final double timeMs;
    private final long steps;
    private final long moves;
    private final long comparisons;

    public BenchmarkResult(
            double timeMs,
            long steps,
            long moves,
            long comparisons) {

        this.timeMs = timeMs;
        this.steps = steps;
        this.moves = moves;
        this.comparisons = comparisons;
    }

    public double getTimeMs() {
        return timeMs;
    }

    public long getSteps() {
        return steps;
    }

    public long getMoves() {
        return moves;
    }

    public long getComparisons() {
        return comparisons;
    }
}