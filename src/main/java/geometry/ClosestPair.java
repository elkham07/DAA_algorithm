package geometry;

import util.Metrics;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    // Входной API: возвращает минимальную евклидову дистанцию между двумя точками
    public static double closest(Point[] pts, Metrics metrics) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;
        Point[] byX = Arrays.copyOf(pts, pts.length);
        Arrays.sort(byX, Comparator.comparingDouble(p -> p.x));
        return rec(byX, 0, byX.length - 1, metrics);
    }

    // Рекурсивный D&C, использует byX (отсортирован по x). Для strip сортирует по y.
    private static double rec(Point[] byX, int l, int r, Metrics metrics) {
        metrics.enterRecursion();
        int n = r - l + 1;
        if (n <= 3) {
            double ans = brute(byX, l, r, metrics);
            metrics.exitRecursion();
            return ans;
        }

        int mid = l + (r - l) / 2;
        double midx = byX[mid].x;

        double dl = rec(byX, l, mid, metrics);
        double dr = rec(byX, mid + 1, r, metrics);
        double d = Math.min(dl, dr);

        // build strip: точки, у которых |x - midx| < d
        int cnt = 0;
        Point[] strip = new Point[n];
        for (int i = l; i <= r; i++) {
            if (Math.abs(byX[i].x - midx) < d) {
                strip[cnt++] = byX[i];
            }
        }

        // сортируем полосу по y и проверяем соседей (до O(7-8) для каждой точки)
        Arrays.sort(strip, 0, cnt, Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < cnt; i++) {
            for (int j = i + 1; j < cnt && (strip[j].y - strip[i].y) < d; j++) {
                metrics.comparisons++;
                double dist = dist(strip[i], strip[j]);
                if (dist < d) d = dist;
            }
        }

        metrics.exitRecursion();
        return d;
    }

    // Брутфорс для малых n или для проверки
    private static double brute(Point[] a, int l, int r, Metrics metrics) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                metrics.comparisons++;
                double d = dist(a[i], a[j]);
                if (d < best) best = d;
            }
        }
        return best;
    }

    // Публичный вспомогательный метод для тестов (brute-force по всему массиву)
    public static double bruteForce(Point[] pts) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                double d = dist(pts[i], pts[j]);
                if (d < best) best = d;
            }
        }
        return best;
    }

    private static double dist(Point p, Point q) {
        double dx = p.x - q.x;
        double dy = p.y - q.y;
        return Math.hypot(dx, dy);
    }
}
