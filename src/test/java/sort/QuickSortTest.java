package sort;

import org.junit.jupiter.api.Test;
import util.Metrics;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
        System.out.println("QuickSort - Comparisons: " + metrics.comparisons + ", Depth: " + metrics.depth);
    }

    @Test
    void testLargeArray() {
        int n = 10000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = (int) (Math.random() * n);

        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);

        assertArrayEquals(expected, arr);
        System.out.println("QuickSort Large - Depth: " + metrics.depth);
    }
}
