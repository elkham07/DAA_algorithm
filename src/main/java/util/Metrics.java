package util;

public class Metrics {
    public int comparisons = 0;
    public int swaps = 0;
    public int depth = 0;

    private int currentDepth = 0;

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > depth) depth = currentDepth;
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        depth = 0;
        currentDepth = 0;
    }
}
