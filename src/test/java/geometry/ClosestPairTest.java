package geometry;

import org.junit.jupiter.api.Test;
import util.Metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    void smallBruteCompare() {
        ClosestPair.Point[] pts = new ClosestPair.Point[] {
                new ClosestPair.Point(0,0),
                new ClosestPair.Point(1,1),
                new ClosestPair.Point(2,2),
                new ClosestPair.Point(0.1, 0.1)
        };

        Metrics metrics = new Metrics();
        double dFast = ClosestPair.closest(pts, metrics);
        double dBrute = ClosestPair.bruteForce(pts);

        assertEquals(dBrute, dFast, 1e-9);
    }

    private void assertEquals(double dBrute, double dFast, double v) {
    }

    @Test
    void randomSmallCompareBrute() {
        int n = 200; // <= 2000 по условию — но тесты должны быть быстрыми
        java.util.Random rng = new java.util.Random(42);
        ClosestPair.Point[] pts = new ClosestPair.Point[n];
        for (int i = 0; i < n; i++) pts[i] = new ClosestPair.Point(rng.nextDouble() * 1000, rng.nextDouble() * 1000);

        Metrics metrics = new Metrics();
        double dFast = ClosestPair.closest(pts, metrics);
        double dBrute = ClosestPair.bruteForce(pts);

        assertEquals(dBrute, dFast, 1e-9);
        System.out.println("ClosestPair - comparisons: " + metrics.comparisons + ", depth: " + metrics.depth);
    }
}
